import classes from './ImageSubjectiveForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import React from "react";
import {ImageUploadStatus, NewImageSubjectiveQuestion} from "../../../../types/question.ts";
import {uploadQuizImage} from "../../../../global/image/imageUploadService.ts";
import {handleError} from "../../../../global/error/error.ts";
import QuestionImage from "../common/QuestionImage.tsx";
import AddImageSubjectiveQuestionButton from "./AddImageSubjectiveQuestionButton.tsx";
import ImageSubAnswerController from "./ImageSubAnswerController.tsx";
import {
  ANSWER_MAX_LIMIT_MSG,
  ANSWER_MIN_LIMIT_MSG,
} from "../../../../global/message/quiz_message.ts";
import {MAX_SUB_ANSWER_COUNT} from "../../../../global/constant/question.ts";
import {useAlertManager} from "../../../alert/useAlertManager.hook.tsx";
import QuestionDeleteButton from "../common/QuestionDeleteButton.tsx";

const ImageSubjectiveQuestionForm = () => {

  const {questions, setQuestions} = useQuizContext<NewImageSubjectiveQuestion>();
  const {showAlert, AlertContainer} = useAlertManager();


  const imageUploader = async (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    e.preventDefault();
    if (!e.target.value) return;
    if (!e.target.files || e.target.files.length === 0) {
      console.log('No file selected');
      return;
    }

    // 이미지 시작 상태 업데이트
    changeImageUploadStatus(ImageUploadStatus.PENDING, index);

    try {
      const newImageUrl = await uploadQuizImage(e.target.files[0], questions[index].imageUrl);
      const updatedQuestions = [...questions];
      updatedQuestions[index].imageUrl = newImageUrl;
      setQuestions(updatedQuestions);

      // 업로드 완료 상태 업데이트
      changeImageUploadStatus(ImageUploadStatus.UPLOADED, index);
    } catch (error) {
      handleError(error);
      changeImageUploadStatus(ImageUploadStatus.NOT_UPLOADED, index);
      showAlert('이미지 업로드에 실패하였습니다.');
    }
  }

  const changeImageUploadStatus = (status: ImageUploadStatus, qi: number) => {
    setQuestions(prev => {
      return prev.map((question, index) =>
        qi === index ? {...question, imageStatus: status} : question
      );
    });
  };

  const deleteQuestion = (qi: number) => {
    setQuestions(prev => (
      prev.filter((_, index) => index !== qi)
    ));
  }

  const addAnswer = (index: number, value: string) => {
    setQuestions(prev => {
      return prev.map((question, qi) => {
        if (index !== qi) return question;
        if (question.answers.length >= MAX_SUB_ANSWER_COUNT) {
          // TODO: 기본 alert 함수가 아닌 커스텀 alert 만들기
          showAlert(ANSWER_MAX_LIMIT_MSG);
          return question;
        }
        question.answers.push(value);
        return question;
      })
    })
  }

  const deleteAnswer = (qi: number, ai: number) => {
    if (questions[qi].answers.length <= 1) {
      showAlert(ANSWER_MIN_LIMIT_MSG);
      return;
    }

    setQuestions(prev => {
      return prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          answers: question.answers.filter((_, aIdx) => aIdx !== ai)
        }
      });
    });
  }

  return (
    <>
      <AlertContainer/>
      <main className={`${classes.questionContainer} common-flex-column`}>
        {
          questions?.map((question: NewImageSubjectiveQuestion, qi: number) => (
            <React.Fragment key={`question_${qi}`}>
              <hr className="common-hr"/>
              <div className={classes.question}>
                <div className={classes.deleteButtonContainer}>
                  <QuestionDeleteButton questionIndex={qi} deleteQuestion={deleteQuestion} />
                </div>
                <div className={classes.fileInputContainer}>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => imageUploader(e, qi)}
                    id={`file-upload-${qi}`}
                    className={classes.hiddenInput}
                  />
                  <label htmlFor={`file-upload-${qi}`} className={classes.customUploadBox}>
                    <QuestionImage status={question.imageStatus} imageUrl={question.imageUrl}/>
                  </label>
                </div>

                {/*  선택지 */}
                <ImageSubAnswerController
                  questionIndex={qi}
                  contents={question.answers}
                  addAnswer={addAnswer}
                  deleteAnswer={deleteAnswer}
                />
              </div>
            </React.Fragment>
          ))}
        <AddImageSubjectiveQuestionButton/>
      </main>
    </>
  )
}

export default ImageSubjectiveQuestionForm;