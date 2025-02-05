import {FunctionComponent} from "react";
import classes from "./QuizDraftModal.module.css";
import {useQuizContext} from "../../../../../context/QuizContext.tsx";

const QuizDraftModalCloseButton = () => {

  const {setDraftModal} = useQuizContext();

  return (
    <>
      <div className={classes.closeLayer} onClick={() => setDraftModal(false)}>
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" className="size-6">
          <path strokeLinecap="round" strokeLinejoin="round" d="M6 18 18 6M6 6l12 12"/>
        </svg>
      </div>
    </>
  )
}

export default QuizDraftModalCloseButton;