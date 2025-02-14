import classes from "./ImageMcqForm.module.css";
import React from "react";
import {NewImageMcqQuestion, ImageUploadStatus} from "../../../../types/question.ts";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {ImageUrlResponse} from "../../types/ImageUrlResponse.ts";
import {handleError} from "../../../../global/error/error.ts";
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {NewImageMcqChoice} from "../../../../types/choice.ts";
import AddImageMcqQuestionButton from "./AddImageMcqQuestionButton.tsx";
import QuestionImage from "../common/QuestionImage.tsx";
import {compressImage} from "../../../../global/util/image/ImageCompress.tsx";

const ImageMcqQuestionForm = () => {

  const { questions, setQuestions } = useQuizContext<NewImageMcqQuestion>();

  const imageUploader = async (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    e.preventDefault();
    if (!e.target.value) return;

    // 1. 파일 존재 여부 체크 (안전한 접근)
    if (!e.target.files || e.target.files.length === 0) {
      console.log("No file selected");
      return;
    }

    changeImageUploadStatus(ImageUploadStatus.PENDING, index);
    try {
      const compressedFile = await compressImage(e.target.files[0]);
      const formData = new FormData();
      formData.append("image", compressedFile);
      formData.append("prevImageUrl", questions[index].imageUrl)

      //서버측에 이미지 전달
      const response = await axiosJwtInstance.post<{ imageUrl: string; }>(
        `/api/quiz/draft/image`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          }
        }
      );
      const data: ImageUrlResponse = response.data;
      const imageUrl = data.imageUrl;
      const copy: NewImageMcqQuestion[] = [...questions];
      copy[index].imageUrl = imageUrl;
      setQuestions(copy);

      changeImageUploadStatus(ImageUploadStatus.UPLOADED, index);
    } catch (error) {
      handleError(error);
      changeImageUploadStatus(ImageUploadStatus.NOT_UPLOADED, index);
      alert("이미지 업로드에 실패하였습니다.")
    }
  };

  const changeImageUploadStatus = (status: ImageUploadStatus, qi: number) => {
    setQuestions(prev => {
      return prev.map((question, index) =>
        qi === index ? {...question, imageStatus: status} : question
      );
    });
  };

  const deleteQuestion = (qi: number) => {
    setQuestions(prev =>
      prev.filter((_, index) => index !== qi)
    );
  }

  const addChoice = (index: number) => {
    setQuestions(prev =>
      prev.map((question, qi) => {
        if (index !== qi) return question;
        if (question.choices.length >= 7) {
          alert("선택지는 최대 7개까지 만들 수 있습니다");
          return question;
        }
        return {
          ...question,
          choices: [...question.choices, {content: "", answer: false}]
        };
      })
    );
  };

  const choiceInputChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    qi: number,
    ci: number
  ) => {
    // e.preventDefault()
    setQuestions(prev =>
      prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          choices: question.choices.map((choice, cIdx) =>
            cIdx !== ci ? choice : {content: e.target.value, answer: choice.answer}
          ),
        }
      })
    );
  }

  const choiceAnswerCheck = (
    qi: number,
    ci: number
  ) => {
    setQuestions(prev =>
      prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          choices: question.choices.map((choice, cIdx) =>
            cIdx !== ci ? choice : {...choice, answer: !choice.answer}
          )
        };
      })
    );
  }

  const deleteChoice = (qi: number, ci: number) => {
    if (questions[qi].choices.length <= 1) {
      alert('선택지는 최소 하나 이상 있어야합니다')
      return;
    }
    setQuestions(prev => {
      return prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          choices: question.choices.filter((_, cIdx) => cIdx !== ci)
        }
      })
    });
  }

  return (
    <>
      <section className={`${classes.questionContainer} common-flex-column`}>
        {questions?.map((question, qi) => (
          <React.Fragment key={`question_${qi}`}>
            <hr className="common-hr"/>
            <div className={classes.question}>
              <div className={classes.deleteButtonContainer}>
                <button
                  className={classes.quizDeleteButton}
                  onClick={() => deleteQuestion(qi)}
                >
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
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
                <button className={classes.choiceAddButton} onClick={() => addChoice(qi)}>
                  선택지 추가
                </button>
              </div>

              {/* 선택지 */}
              <div className={classes.choiceContainer}>
                {question.choices.map((choice: NewImageMcqChoice, ci: number) => (
                  <div className={classes.choice} key={`${qi}_${ci}_choice`}>
                    {/* 체크박스 레이블 */}
                    <label className={classes.checkboxContainer}>
                      <input
                        type="checkbox"
                        checked={choice.answer}
                        onChange={() => choiceAnswerCheck(qi, ci)}
                        className={classes.hiddenCheckbox}
                      />
                      <span className={classes.customCheckbox}></span>
                    </label>
                    <span className={classes.choiceNumber}>{ci + 1}. </span>
                    <input
                      className={`${classes.choiceInput} common-input-sm`}
                      type="text"
                      placeholder="선택지를 입력하세요"
                      value={choice.content}
                      onChange={(e) => choiceInputChange(e, qi, ci)}
                    />
                    <button className={`${classes.choiceDeleteButton} common-button`} onClick={() => deleteChoice(qi, ci)}>
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
        <AddImageMcqQuestionButton/>
      </section>
    </>
  )
}

export default ImageMcqQuestionForm;