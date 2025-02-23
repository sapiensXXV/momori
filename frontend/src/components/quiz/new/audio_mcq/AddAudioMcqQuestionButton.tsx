import classes from './AudioMcqForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {AudioUploadStatus, NewAudioMcqQuestion} from "../../../../types/question.ts";
import React from "react";

const AddAudioMcqQuestionButton = () => {

  const { setQuestions } = useQuizContext<NewAudioMcqQuestion>();

  const addQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    setQuestions(prev => {
      return [...prev, {
        audioStatus: AudioUploadStatus.NOT_UPLOADED,
        audioId: "",
        startTime: 0,
        playDuration: 10,
        choices: [{ content: "", answer: false }]
      }]
    })
  };

  return (
    <>
      <button className={classes.questionAddButton} onClick={addQuestion}>
        <img src={"/img/icon/add.svg"} alt="Add image"/>
        <span>문제 추가</span>
      </button>
    </>
  )
}

export default AddAudioMcqQuestionButton;