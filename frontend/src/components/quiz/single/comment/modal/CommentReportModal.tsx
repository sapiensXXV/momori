import classes from './CommentModal.module.css'
import {CommentDetail} from "../Comments.tsx";
import {FC} from "react";

type CommentReportModalProps = {
  comment: CommentDetail;
  setShowModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const CommentReportModal: FC<CommentReportModalProps> = ({ comment,  setShowModal }) => {


  const closeModal = () => {
    setShowModal(false);
  }

  return (
    <>
      <main className={classes.modalLayer}>
        <div className={classes.modalContainer}>
          <header className={classes.headerLayer}>
            <span>댓글 신고</span>
          </header>
        </div>
      </main>
    </>
  )
}

export default CommentReportModal;