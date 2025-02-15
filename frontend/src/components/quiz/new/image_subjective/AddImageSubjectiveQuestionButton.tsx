import classes from '../common/AddQuestionButton.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {ImageUploadStatus, NewImageSubjectiveQuestion} from "../../../../types/question.ts";
import React from "react";

const AddImageSubjectiveQuestionButton = () => {
  const { setQuestions } = useQuizContext<NewImageSubjectiveQuestion>()

  const addQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    setQuestions(prev => {
      return [...prev, {
        imageStatus: ImageUploadStatus.NOT_UPLOADED,
        imageUrl: "",
        answers: [""]
      }]
    })
  };

  return (
    <button
      className={classes.questionAddButton}
      onClick={(e) => addQuestion(e)}
    >
      <img src={"/img/icon/add.svg"} alt="Add image"/>
      <span>문제 추가</span>
    </button>
  )
}

export default AddImageSubjectiveQuestionButton;