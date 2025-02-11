import classes from './SingleComment.module.css'
import {CommentDetail} from "./Comments.tsx";
import {FC} from "react";
import {formatDateTime} from "../../../../global/util/date/date.ts";

type SingleCommentProps = {
  comment: CommentDetail;
}
const SingleComment: FC<SingleCommentProps> = ({ comment }) => {

  const deleteComment = () => {
    console.log('delete comment');
  }

  const reportComment = () => {
    console.log('report comment');
  }

  return (
    <>
      <main className={classes.commentContainer}>
        <div className={classes.commentContentContainer}>
          <div className={classes.commentNameAndDate}>
            <span className={classes.commentName}>{comment.name}</span>
            <span className={classes.commentDate}>{formatDateTime(comment.createdAt)}</span>
          </div>
          <span className={classes.commentContent}>{comment.content}</span>
        </div>
        <div className={classes.commentButtonContainer}>
          <div className={`common-button ${classes.commentDeleteButton}`} onClick={deleteComment}>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
            </svg>
          </div>
          <div className={`common-button ${classes.commentReportButton}`} onClick={reportComment}>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" className="size-6">
              <path stroke-linecap="round" stroke-linejoin="round"
                    d="M3 3v1.5M3 21v-6m0 0 2.77-.693a9 9 0 0 1 6.208.682l.108.054a9 9 0 0 0 6.086.71l3.114-.732a48.524 48.524 0 0 1-.005-10.499l-3.11.732a9 9 0 0 1-6.085-.711l-.108-.054a9 9 0 0 0-6.208-.682L3 4.5M3 15V4.5"/>
            </svg>
          </div>
        </div>
      </main>
    </>
  )
}

export default SingleComment;