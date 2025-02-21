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

  const { setQuestions, quizType, setQuizType } = useQuizContext();

  useEffect(() => {
    initQuestions();
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
  }

  const changeQuizType = (e: React.ChangeEvent<HTMLSelectElement>) => {
    // 새로운 퀴즈 유형 추가시 기존의 문제 삭제
    setQuizType(e.target.value as QuizTypes);
  }

  const initQuestions = () => {
    // console.log('initQuestion');
    switch (quizType) {
      case QuizTypes.IMAGE_MCQ:
        // console.log("이미지-객관식 으로 문제를 수정합니다.")
        setQuestions([{ imageStatus: ImageUploadStatus.NOT_UPLOADED, imageUrl: "", choices: [] }]);
        break;
      case QuizTypes.IMAGE_SUBJECTIVE:
        // console.log("이미지-주관식으로 문제를 수정합니다.")
        setQuestions([{ imageStatus: ImageUploadStatus.NOT_UPLOADED, imageUrl: "", answers: [] }]);
        break;
      case QuizTypes.AUDIO_MCQ:
        // console.log("오디오-객관식으로 문제를 수정합니다.")
        setQuestions([{ audioStatus: AudioUploadStatus.NOT_UPLOADED, audioId: "", startTime: 0, playDuration: undefined, choices: []}])
        break;
      case QuizTypes.AUDIO_SUBJECTIVE:
        // console.log("오디오-주관식으로 문제를 수정합니다.")
        setQuestions([{  audioStatus: AudioUploadStatus.NOT_UPLOADED, audioId: "", startTime: 0, playDuration: undefined, answers: [] }])
        break;
      case QuizTypes.BINARY_CHOICE:
        // console.log("이지선다 유형으로 문제를 수정합니다.")
        setQuestions([{
          first: { description: "", imageUrl: "", imageStatus: ImageUploadStatus.NOT_UPLOADED, answer: false },
          second: { description: "", imageUrl: "", imageStatus: ImageUploadStatus.NOT_UPLOADED, answer: false },
        }])
        break;
    }
  }

  return (
    <section className={styles.main}>
      <select className={styles.quizSelect} onChange={(e) => changeQuizType(e)}>
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