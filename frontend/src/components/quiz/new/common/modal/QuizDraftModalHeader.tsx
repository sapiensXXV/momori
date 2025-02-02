import {FunctionComponent} from "react";
import classes from './QuizDraftModal.module.css'

const QuizDraftModalHeader = () => {
  return (
    <>
      <div className={classes.headerLayer}>
        <span>임시저장 퀴즈</span>
      </div>

    </>
  )
}

export default QuizDraftModalHeader;