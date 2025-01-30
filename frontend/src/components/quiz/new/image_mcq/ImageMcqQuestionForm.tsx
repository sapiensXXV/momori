import styles from "./ImageMcqForm.module.css";
import React, {FC} from "react";
import {ImageMcqQuestionFormProps} from "./types/ImageMcqProps.types.ts";
import QuestionImage from "../../common/QuestionImage.tsx";

const ImageMcqQuestionForm: FC<ImageMcqQuestionFormProps> = (
  {
    question,
    imageUploader,
    deleteQuestion,
    addChoice,
    choiceAnswerCheck,
    choiceInputChange,
    qi
  }) => {
  console.log(qi);
  return (
    <>
      <hr className={`common-hr`}/>
      <div className={styles.question} key={`question_${qi}`}>
        <div className={styles.deleteButtonContainer}>
          <button className={styles.quizDeleteButton} onClick={() => deleteQuestion(qi)}>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" className="size-6">
              <path stroke-linecap="round" stroke-linejoin="round"
                    d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
            </svg>
          </button>
        </div>
        <div className={styles.fileInputContainer}>
          <input
            type="file"
            accept="image/*"
            onChange={(e) => imageUploader(e, qi)}
            id={`file-upload-${qi}`}
            className={styles.hiddenInput}
          />
          <label htmlFor={`file-upload-${qi}`} className={styles.customUploadBox}>
            <QuestionImage
              status={question.imageStatus}
              imageUrl={question.imageUrl}
            />
          </label>
          <button
            className={styles.choiceAddButton}
            onClick={() => addChoice(qi)}>
            선택지 추가
          </button>
        </div>

        {/*선택지*/}

        <div className={styles.choiceContainer}>
          {question.choices.map((choice, ci) => (
            <div className={styles.choice} key={`${qi}_${ci}_choice`}>
              {/*체크박스 레이블*/}
              <label className={styles.checkboxContainer}>
                <input
                  type="checkbox"
                  checked={question.choices[ci].isAnswer}
                  onChange={() => choiceAnswerCheck(qi, ci)}
                  className={styles.hiddenCheckbox}
                />
                <span className={styles.customCheckbox}></span>
              </label>

              <span className={styles.choiceNumber}>{ci + 1}. </span>
              <input
                className={`${styles.choiceInput} common-input-sm`}
                type="text"
                placeholder="선택지를 입력하세요"
                value={choice.content}
                onChange={(e) => choiceInputChange(e, qi, ci)}
              />
            </div>
          ))}
        </div>
      </div>
    </>
  )
}

export default ImageMcqQuestionForm;