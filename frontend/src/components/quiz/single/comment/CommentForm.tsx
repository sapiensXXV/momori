import classes from './CommentForm.module.css'
import {FC, useState} from "react";
import {useAuth} from "../../../../context/AuthContext.tsx";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {handleError} from "../../../../global/error/error.ts";
import {CommentDetail} from "./Comments.tsx";

type CommentFormProps = {
  quizId: string;
  setComments: React.Dispatch<React.SetStateAction<CommentDetail[]>>;
}

type CommentFormData = {
  name: string;
  password: string;
  content: string;
}

const CommentForm: FC<CommentFormProps> = ({ quizId, setComments}) => {

  const [formData, setFormData] = useState<CommentFormData>({content: "", name: "", password: ""});
  const auth = useAuth();
  // console.log(formData);
  const postComment = () => {
    axiosJwtInstance.post(`/api/comment/${quizId}`, formData)
      .then((response) => {
        const data: CommentDetail = response.data;
        const newComment: CommentDetail = {
          id: data.id,
          name: data.name,
          createdAt: data.createdAt,
          content: data.content,
          maker: data.maker,
          type: data.type
        };
        console.log(response.data);
        setFormData(prev => ({...prev, content: ""})); // 컨텐츠 비우기
        setComments(prev => ([newComment, ...prev])); // 새로운 댓글 끼워넣기.
      })
      .catch((error) => {
        console.log(error);
        handleError(error);
      })
  }

  return (
    <>
      <main className={classes.commentFormContainer}>
        <div className={classes.commentContentContainer}>
          <textarea
            className={`common-textarea ${classes.commentContentArea}`}
            placeholder={"댓글을 입력하세요"}
            value={formData.content}
            onChange={(e) => {
              setFormData(prev => ({...prev, content: e.target.value}))
            }}
          />
          {
            // 로그인 하지 않은 상태일 때만 이름과 비밀번호 입력칸 생성
            auth.isAuthenticated ? null : (
              <div className={classes.commentUserInfoArea}>
                <input
                  className={`common-input-sm ${classes.commentInfoInput}`}
                  type={"text"}
                  placeholder={"이름"}
                  value={formData.name}
                  onChange={(e) => {
                    setFormData(prev => ({...prev, name: e.target.value}))
                  }}
                />
                <input
                  className={`common-input-sm ${classes.commentInfoInput}`}
                  type={"password"}
                  placeholder={"비밀번호"}
                  value={formData.password}
                  onChange={(e) => {
                    setFormData(prev => ({...prev, password: e.target.value}))
                  }}
                />
              </div>
            )
          }

        </div>
        <div className={`${classes.submitButton} common-button`} onClick={postComment}>작성</div>
      </main>
    </>
  )
}

export default CommentForm;