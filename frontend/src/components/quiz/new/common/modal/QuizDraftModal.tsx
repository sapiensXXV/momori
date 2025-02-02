import React, {FC} from "react";
import {DraftSimpleInfo} from "../../../../../types/draft.ts";
import {useQuizContext} from "../../../../../context/QuizContext.tsx";
import classes from "./QuizDraftModal.module.css";
import QuizDraftModalCloseButton from "./QuizDraftModalCloseButton.tsx";
import QuizDraftModalHeader from "./QuizDraftModalHeader.tsx";
import QuizDraftModalContent from "./QuizDraftModalContent.tsx";

type QuizDraftModalProps = {
  drafts: DraftSimpleInfo[]
}

const QuizDraftModal: FC<QuizDraftModalProps> = ({drafts}) => {

  const {draftModal, setDraftModal} = useQuizContext();

  return (
    draftModal ?
      <>
        <section className={classes.modalLayer}>
          {/*  임시 저장 UI 작성*/}
          {/*  TODO: Draft 출력*/}
          <div className={classes.modalContainer}>
            <QuizDraftModalCloseButton />
            <QuizDraftModalHeader />
            <div className={classes.divider}></div>
            <QuizDraftModalContent drafts={drafts}/>
          </div>
        </section>
      </> : null
  )
}

export default QuizDraftModal;