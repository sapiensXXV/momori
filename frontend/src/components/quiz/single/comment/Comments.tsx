import classes from './Comments.module.css';
import {FC, useEffect, useState} from "react";
import {useInView} from "react-intersection-observer";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {handleError} from "../../../../global/error/error.ts";
import CommentForm from "./CommentForm.tsx";
import SingleComment from "./SingleComment.tsx";

type CommentsProps = {
  quizId: string;
}

export type CommentDetail = {
  id: number;
  name: string;
  createdAt: string;
  content: string;
  isMaker: boolean;
}

type CommentSearchCondition = {
  page: number;
  size: number;
  isLastPage: boolean;
}

const initSearchCondition = {page: 0, size: 20, isLastPage: false}

const Comments: FC<CommentsProps> = ({quizId}) => {

  const [comments, setComments] = useState<CommentDetail[]>([]);
  const [searchCondition, setSearchCondition] = useState<CommentSearchCondition>(initSearchCondition);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [observeTrigger, inView] = useInView()

  useEffect(() => {
    if (isLoading || searchCondition.isLastPage) return; // 로딩 중이거나 마지막 페이지라면 로직을 수행하지 않는다.
    requestComments(); // 댓글 데이터 요청
  }, [inView]);

  const requestComments = () => {
    setIsLoading(true);
    axiosJwtInstance
      .get(`/api/comment/${quizId}`)
      .then((response) => {
        setComments([...comments, ...response.data]) // 댓글 데이터 세팅
        console.log(response);
        if (response.data.length < searchCondition.size) {
          // 가져온 데이터의 갯수가 요청한 수보다 적다면 마지막 페이지 임을 나타낸다.
          searchCondition.isLastPage = true;
        } else {
          setSearchCondition(prev => ({...prev, page: prev.page + 1}))
        }
      })
      .catch((error) => {
        handleError(error);
      })
      .finally(() => {
        setIsLoading(false); // 로딩 상태 해제
      })
  }

  return (
    <>
      <main className={classes.commentContainer}>
        <CommentForm quizId={quizId}/>
        {
          comments.map((comment) => (
            <SingleComment key={`comment_${comment.id}`} comment={comment}/>
          ))
        }
        <div className={classes.divider}></div>
        {
          searchCondition.isLastPage ? null :
            <div ref={observeTrigger} className={classes.commentObserver}>
              {isLoading ? <img src="/img/icon/loading.gif" alt="loading"/> : null}
            </div>
        }
      </main>

    </>
  )
}

export default Comments;
