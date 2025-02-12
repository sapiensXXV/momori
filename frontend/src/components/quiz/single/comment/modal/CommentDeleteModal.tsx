import classes from './CommentModal.module.css'
import {CommentDetail} from "../Comments.tsx";
import {FC, useState} from "react";

type CommentDeleteModalProps = {
  comment: CommentDetail;
  setShowModal:  React.Dispatch<React.SetStateAction<boolean>>;
  deleteComment: (commentId: number, password: string, type: string) => void;
}

const CommentDeleteModal: FC<CommentDeleteModalProps> = ({ comment, setShowModal, deleteComment}) => {

  const [password, setPassword] = useState<string>("");

  const closeModal = () => {
    setShowModal(false);
  }

  return (
    <>
      <main className={classes.modalLayer}>
        <div className={classes.modalContainer}>
          <header className={classes.headerLayer}>
            <span>댓글 삭제</span>
            <div className={classes.modalCloseButton} onClick={closeModal}>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                   stroke="currentColor" className="size-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12"/>
              </svg>
            </div>
          </header>
          <div className={classes.contentLayer}>
            {
              comment.type === "ANONYMOUS"
                ? <input
                  className={`common-input-sm ${classes.passwordInput}`}
                  type={"password"} placeholder={"비밀번호를 입력하세요"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
                : null
            }
            <div className={classes.buttonContainer}>
              <button className={`common-button`} onClick={() => deleteComment(comment.id, password, comment.type)}>예</button>
              <button className={`common-button`} onClick={closeModal}>아니오</button>
            </div>
          </div>
        </div>
      </main>
    </>
  );
}

export default CommentDeleteModal;