import classes from './ImageSubjectiveForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import React from "react";
import {ImageUploadStatus, NewImageSubjectiveQuestion} from "../../../../types/question.ts";
import {uploadQuizImage} from "../../../../global/image/imageUploadService.ts";
import {handleError} from "../../../../global/error/error.ts";
import QuestionImage from "../common/QuestionImage.tsx";
import AddImageSubjectiveQuestionButton from "./AddImageSubjectiveQuestionButton.tsx";

const ImageSubjectiveQuestionForm = () => {

  const {questions, setQuestions} = useQuizContext<NewImageSubjectiveQuestion>();
  console.log(questions);
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
      alert('이미지 업로드에 실패하였습니다.');
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

  const addAnswer = (index: number) => {
    setQuestions(prev => {
      return prev.map((question, qi) => {
        if (index !== qi) return question;
        return {
          ...question,
          answers: [...question.answers, ""], // 새로운 정답 추가
        }
      })
    })
  }

  const answerInputChange = (e: React.ChangeEvent<HTMLInputElement>, qi: number, ai: number) => {
    setQuestions(prev => (
      prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          answers: question.answers.map((answer, aIdx) => (
            aIdx !== ai ? answer : e.target.value
          ))
        }
      })
    ))
  }

  const deleteAnswer = (qi: number, ai: number) => {
    if (questions[qi].answers.length <= 1) {
      alert('정답은 최소 하나 이상 있어야합니다.')
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
      <main className={`${classes.questionContainer} common-flex-column`}>
        {
          questions?.map((question: NewImageSubjectiveQuestion, qi: number) => (
            <React.Fragment key={`question_${qi}`}>
              <hr className="common-hr"/>
              <div className={classes.question}>
                <div className={classes.deleteButtonContainer}>
                  <button
                    className={classes.quizDeleteButton}
                    onClick={() => deleteQuestion(qi)}
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" className="size-6">
                      <path strokeLinecap="round" strokeLinejoin="round"
                            d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
                    </svg>
                  </button>
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
                  <button className={classes.answerAddButton} onClick={() => addAnswer(qi)}>
                    복수 정답 추가
                  </button>
                </div>

                {/*  선택지 */}
                <div className={classes.answerContainer}>
                  {
                    question.answers.map((answer: string, ai: number) => (
                      <div className={classes.answer} key={`${qi}_${ai}_answer`}>
                        <input
                          className={`${classes.answerInput} common-input-sm`}
                          type="text"
                          placeholder="정답을 입력하세요"
                          value={answer}
                          onChange={(e) => answerInputChange(e, qi, ai)}
                        />
                        <button className={`${classes.answerDeleteButton} common-button`} onClick={() => deleteAnswer(qi, ai)}>
                          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
                            <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
                          </svg>
                        </button>
                      </div>
                    ))}
                </div>
              </div>
            </React.Fragment>
          ))}
        <AddImageSubjectiveQuestionButton/>
      </main>
    </>
  )
}

export default ImageSubjectiveQuestionForm;