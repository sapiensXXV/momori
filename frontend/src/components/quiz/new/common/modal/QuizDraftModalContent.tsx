import classes from './QuizDraftModal.module.css'
import {DraftSimpleInfo} from "../../../../../types/draft.ts";
import {FC} from "react";
import QuizDraftModalContentItem from "./QuizDraftModalContentItem.tsx";

type QuizDraftModalContentProps = {
  drafts: DraftSimpleInfo[];
}

const QuizDraftModalContent: FC<QuizDraftModalContentProps> = ({ drafts }) => {

  console.log(drafts);

  return (
    <>
      <div className={classes.contentLayer}>
        <div className={classes.contentDescription}>
          <span>임시 등록 퀴즈는 30일동안 최대 100개만 저장됩니다.</span>
          <button className={`common-button`}>전체 삭제</button>
        </div>
        <div className={classes.contentList}>
          {
            drafts.map((draft, index) => {
              return <QuizDraftModalContentItem key={`draft_list_${index}`} draft={draft}/>
            })
          }
        </div>
      </div>
    </>
  )
}

export default QuizDraftModalContent;