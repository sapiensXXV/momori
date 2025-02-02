import {DraftSimpleInfo} from "../../../../../types/draft.ts";
import classes from './QuizDraftModal.module.css'
import {FC} from "react";
import {useQuizContext} from "../../../../../context/QuizContext.tsx";
import {formatDateTime} from "../../../../../global/util/date/date.ts";
import {axiosJwtInstance} from "../../../../../global/configuration/axios.ts";

type QuizDraftModalContentItem = {
  draft: DraftSimpleInfo;
}

const QuizDraftModalContentItem: FC<QuizDraftModalContentItem> = ({ draft }) => {

  const { quizType, questions, setQuestions } = useQuizContext();

  const loadDraftItem = () => {

    axiosJwtInstance.get('')
  }
  return (
    <>
      <div className={classes.contentItem} onClick={loadDraftItem}>
        <span className={classes.itemTitle}>{draft.title}</span>
        <div className={classes.dateAndDeleteButton}>
          <span className={classes.dateInfo}>{formatDateTime(draft.createdAt)}</span>
          <div className={`${classes.deleteButton} common-button`}>
            <img src={"/img/icon/trashcan.svg"} alt={"item delete button"}/>
          </div>
        </div>
      </div>
      <div className={classes.contentItemDivider}></div>
    </>
  );
}

export default QuizDraftModalContentItem;