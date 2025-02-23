import styles from "./NewQuiz.module.css"
import React, {useEffect} from "react";
import {QuizTypes} from "../types/Quiz.types.ts";
import ImageMcqForm from "./image_mcq/ImageMcqForm.tsx";
import ImageSubjectiveForm from "./image_subjective/ImageSubjectiveForm.tsx";
import AudioMcqForm from "./audio_mcq/AudioMcqForm.tsx";
import AudioSubjectiveForm from "./audio_subjective/AudioSubjectiveForm.tsx";
import BinaryChoiceForm from "./binary_choice/BinaryChoiceForm.tsx";
import {useQuizContext} from "../../../context/QuizContext.tsx";
import {AudioUploadStatus, ImageUploadStatus} from "../../../types/question.ts";

export default function NewQuiz() {

  const { isDraftLoading, setQuestions, quizType, setQuizType } = useQuizContext();

  // 퀴즈 타입이 변할 때마다 Question을 초기화한다.
  useEffect(() => {
    // 임시 퀴즈를 로딩 중일때는 퀴즈 목록을 초기화해서는 안된다.
    if (!isDraftLoading) {
      initQuestions();
    }
  }, [quizType]);

  const getQuizForm = () => {
    switch (quizType) {
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
  };

  const changeQuizType = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setQuizType(e.target.value as QuizTypes);
  }

  const initQuestions = () => {
    console.log('initQuestions')
    switch (quizType) {
      case QuizTypes.IMAGE_MCQ:
        setQuestions([{ imageStatus: ImageUploadStatus.NOT_UPLOADED, imageUrl: "", choices: [{ content: "", answer: false }] }]);
        break;
      case QuizTypes.IMAGE_SUBJECTIVE:
        setQuestions([{ imageStatus: ImageUploadStatus.NOT_UPLOADED, imageUrl: "", answers: [ "" ] }]);
        break;
      case QuizTypes.AUDIO_MCQ:
        setQuestions([{ audioStatus: AudioUploadStatus.NOT_UPLOADED, audioId: "", startTime: 0, playDuration: undefined, choices: [ { content: "", answer: false } ] }])
        break;
      case QuizTypes.AUDIO_SUBJECTIVE:
        setQuestions([{  audioStatus: AudioUploadStatus.NOT_UPLOADED, audioId: "", startTime: 0, playDuration: undefined, answers: [ "" ] }])
        break;
      case QuizTypes.BINARY_CHOICE:
        setQuestions([{
          first: { description: "", imageUrl: "", imageStatus: ImageUploadStatus.NOT_UPLOADED, answer: false },
          second: { description: "", imageUrl: "", imageStatus: ImageUploadStatus.NOT_UPLOADED, answer: false },
        }])
        break;
    }
  }

  return (
    <section className={styles.main}>
      <select className={styles.quizSelect} onChange={(e) => changeQuizType(e)} value={quizType}>
        <option value={QuizTypes.IMAGE_MCQ.valueOf()} >퀴즈 유형: 이미지 객관식</option>
        <option value={QuizTypes.IMAGE_SUBJECTIVE.valueOf()} >퀴즈 유형: 이미지 주관식</option>
        <option value={QuizTypes.AUDIO_MCQ.valueOf()} >퀴즈 유형: 오디오 객관식</option>
        <option value={QuizTypes.AUDIO_SUBJECTIVE.valueOf()} >퀴즈 유형: 오디오 주관식</option>
        <option value={QuizTypes.BINARY_CHOICE.valueOf()} >퀴즈 유형: 갈드컵</option>
      </select>
      { getQuizForm() }
    </section>
  )
}