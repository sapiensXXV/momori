import classes from './Comments.module.css';
import {FC, useEffect, useState} from "react";
import {useInView} from "react-intersection-observer";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {handleErrorWithCustomAlert} from "../../../../global/error/error.ts";
import CommentForm from "./CommentForm.tsx";
import SingleComment from "./SingleComment.tsx";
import CommentDeleteModal from "./modal/CommentDeleteModal.tsx";
import CommentReportModal from "./modal/CommentReportModal.tsx";
import {useAlertManager} from "../../../alert/useAlertManager.hook.tsx";

type CommentsProps = {
  quizId: string | undefined;
}

export type CommentDetail = {
  id: number;
  name: string;
  createdAt: string;
  content: string;
  maker: boolean;
  type: string;
}

type CommentSearchCondition = {
  lastId: number
  size: number;
  isLastPage: boolean;
}

const initCommentDetail = {
  id: 0,
  name: "writer_name",
  createdAt: "created_at",
  content: "content",
  maker: false,
  type: "ANONYMOUS",
}

const initSearchCondition = {lastId: 9007199254740991, size: 20, isLastPage: false} // 자바스크립트의 number 최댓값

const Comments: FC<CommentsProps> = ({quizId}) => {

  const [comments, setComments] = useState<CommentDetail[]>([]);
  const [searchCondition, setSearchCondition] = useState<CommentSearchCondition>(initSearchCondition);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [observeTrigger, inView] = useInView()
  const [showDeleteModal, setShowDeleteModal] = useState<boolean>(false);
  const [selectComment, setSelectComment] = useState<CommentDetail>(initCommentDetail);
  const [showReportModal, setShowReportModal] = useState<boolean>(false);
  const {showAlert, AlertContainer} = useAlertManager();

  useEffect(() => {
    if (isLoading || searchCondition.isLastPage) return; // 로딩 중이거나 마지막 페이지라면 로직을 수행하지 않는다.
    requestComments(); // 댓글 데이터 요청
  }, [inView]);

  const requestComments = () => {
    setIsLoading(true);
    axiosJwtInstance
      .get(`/api/comment/${quizId}?lastId=${searchCondition.lastId}&size=${searchCondition.size}`)
      .then((response) => {
        const newComments = [...comments, ...response.data];
        setComments(newComments); // 댓글 데이터 세팅
        if (response.data.length < searchCondition.size) {
          // 가져온 데이터의 갯수가 요청한 수보다 적다면 마지막 페이지 임을 나타낸다.
          setSearchCondition((prev) => ({
            ...prev,
            isLastPage: true,
            lastId: newComments.length > 0 ? newComments.at(-1).id : 9007199254740991
          }));
        } else {
          setSearchCondition(prev => ({
            ...prev,
            lastId: newComments.length > 0 ? newComments.at(-1).id : 9007199254740991
          }))
        }
      })
      .catch((error) => {
        handleErrorWithCustomAlert(error, showAlert);
      })
      .finally(() => {
        setIsLoading(false); // 로딩 상태 해제
      })
  }

  const triggerDeleteComment = (commentId: number) => {
    // console.log(`Trigger Delete Comment Modal, comment_id=${commentId}`);
    const selected = comments.find(comment => (comment.id === commentId));
    if (selected == null) {
      showAlert('댓글을 삭제하는데 문제가 발생하였습니다.');
      return;
    }
    setSelectComment(selected);
    setShowDeleteModal(true);
  }

  const triggerReportComment = (commentId: number) => {
    console.log(`Trigger Report Comment Modal, comment_id=${commentId}`);
  }

  const removeCommentFromList = (commentId: number) => {
    // id가 일치하지 않는 댓글을 리스트에서 제거
    setComments(prev => prev.filter(comment => comment.id !== commentId));
  }

  const deleteComment = (commentId: number, password: string, type: string) => {
    axiosJwtInstance.delete('/api/comment', { data: { id: commentId, password: password, type: type }})
      .then(() => {
        setShowDeleteModal(false);
        removeCommentFromList(commentId);
      })
      .catch((error) => {
        handleErrorWithCustomAlert(error, showAlert);
      })

  }

  // const reportComment = (commentId: number) => {
  //
  // }

  return (
    <>
      <AlertContainer/>
      { showDeleteModal ? <CommentDeleteModal comment={selectComment} setShowModal={setShowDeleteModal} deleteComment={deleteComment} /> : null }
      { showReportModal ? <CommentReportModal comment={selectComment} setShowModal={setShowReportModal}  /> : null }
      <main className={classes.commentContainer}>
        <CommentForm setComments={setComments} quizId={quizId === undefined ? "" : quizId}/>
        <div className={classes.divider}></div>
        {
          comments.map((comment) => (
            <SingleComment
              key={`comment_${comment.id}`}
              comment={comment}
              deleteComment={triggerDeleteComment}
              reportComment={triggerReportComment}
            />
          ))
        }

        {
          searchCondition.isLastPage ? null :
            <div ref={observeTrigger} className={classes.commentObserver}>
              {isLoading ? <img src="/img/icon/loading.gif" alt="loading"/> : null}
            </div>
        }
      </main>

    </>
  );
}

export default Comments;
