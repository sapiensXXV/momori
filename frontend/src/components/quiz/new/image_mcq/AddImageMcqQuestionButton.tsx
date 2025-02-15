import classes from '../common/AddQuestionButton.module.css'
import React from "react";
import {
  NewImageMcqQuestion,
  ImageUploadStatus,
  NewImageSubjectiveQuestion,
  NewAudioMcqQuestion, NewAudioSubjectiveQuestion, NewImageBinaryQuestion
} from "../../../../types/question.ts";
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {QuizTypes} from "../../types/Quiz.types.ts";

interface AddImageMcqQuestionButtonProps<T extends QuizTypes> {
  quizType: T;
}

interface QuizContextMapping {
  [QuizTypes.IMAGE_MCQ]: NewImageMcqQuestion;
  [QuizTypes.IMAGE_SUBJECTIVE]: NewImageSubjectiveQuestion;
  [QuizTypes.AUDIO_MCQ]: NewAudioMcqQuestion;
  [QuizTypes.AUDIO_SUBJECTIVE]: NewAudioSubjectiveQuestion;
  [QuizTypes.BINARY_CHOICE]: NewImageBinaryQuestion;
}

const AddImageMcqQuestionButton = <T extends QuizTypes>({ quizType }: AddImageMcqQuestionButtonProps<T>) => {

  const {setQuestions} = useQuizContext<NewImageMcqQuestion>()

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