import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {AudioUploadStatus, NewAudioSubjectiveQuestion} from "../../../../types/question.ts";
import classes from "../audio_mcq/AudioMcqForm.module.css";
import React from "react";

const AddAudioSubQuestionButton = () => {

  const {setQuestions} = useQuizContext<NewAudioSubjectiveQuestion>();

  const addQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    setQuestions(prev => {
      return [
        ...prev, {
          audioStatus: AudioUploadStatus.NOT_UPLOADED,
          audioId: "",
          startTime: 0,
          playDuration: 10,
          answers: []
      }]
    })
  }

  return (
    <>
      <button className={classes.questionAddButton} onClick={addQuestion}>
        <img src={"/img/icon/add.svg"} alt="Add image"/>
        <span>문제 추가</span>
      </button>
    </>
  );
}

export default AddAudioSubQuestionButton;