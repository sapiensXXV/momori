import classes from './AddImageMcqQuestionButton.module.css'
import React from "react";
import {ImageMcqQuestion, ImageUploadStatus} from "../../../../types/question.ts";
import {useQuizContext} from "../../../../context/QuizContext.tsx";

const AddImageMcqQuestionButton = () => {

  const {setQuestions} = useQuizContext<ImageMcqQuestion>()

  const addQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    setQuestions(prev => {
      return [...prev, {
        imageStatus: ImageUploadStatus.NOT_UPLOADED,
        imageUrl: "",
        choices: [{content: "", isAnswer: false}]
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

export default AddImageMcqQuestionButton;