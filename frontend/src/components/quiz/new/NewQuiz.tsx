import styles from "./NewQuiz.module.css"
import React, { useState } from "react";
import {QuizTypes} from "../types/Quiz.types.ts";
import ImageMcqForm from "./image_mcq/ImageMcqForm.tsx";
import ImageSubjectiveForm from "./image_subjective/ImageSubjectiveForm.tsx";
import AudioMcqForm from "./audio_mcq/AudioMcqForm.tsx";
import AudioSubjectiveForm from "./audio_subjective/AudioSubjectiveForm.tsx";
import BinaryChoiceForm from "./binary_choice/BinaryChoiceForm.tsx";

export default function NewQuiz() {

  const [quizType, setQuizType] = useState<QuizTypes>(QuizTypes.IMAGE_MCQ);

  const getQuizForm = (type: QuizTypes) => {
    switch (type) {
      case QuizTypes.IMAGE_MCQ:
        return <ImageMcqForm/>;
      case QuizTypes.IMAGE_SUBJECTIVE:
        return <ImageSubjectiveForm/>;
      case QuizTypes.AUDIO_MCQ:
        return <AudioMcqForm/>
      case QuizTypes.AUDIO_SUBJECTIVE:
        return <AudioSubjectiveForm/>;
      case QuizTypes.BINARY_CHOICE:
        return <BinaryChoiceForm/>;
    }
  }

  const changeQuizType = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setQuizType(e.target.value as QuizTypes);
  }

  console.log(quizType);
  return (
    <section className={styles.main}>
      <select className={styles.quizSelect} onChange={(e) => changeQuizType(e)}>
        <option value={QuizTypes.IMAGE_MCQ.valueOf()} >퀴즈 유형: 이미지 객관식</option>
        <option value={QuizTypes.IMAGE_SUBJECTIVE.valueOf()} >퀴즈 유형: 이미지 주관식</option>
        <option value={QuizTypes.AUDIO_MCQ.valueOf()} >퀴즈 유형: 오디오 객관식</option>
        <option value={QuizTypes.AUDIO_SUBJECTIVE.valueOf()} >퀴즈 유형: 오디오 주관식</option>
        <option value={QuizTypes.BINARY_CHOICE.valueOf()} >퀴즈 유형: 갈드컵</option>
      </select>
      { getQuizForm(quizType) }
    </section>
  )
}