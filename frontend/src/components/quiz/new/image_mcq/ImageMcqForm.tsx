import React, {useState} from "react";
import {ImageMcqQuestion} from "../../types/ImageMcqQuestion.types.ts";
import axios from "axios";
import {BASE_URI} from "../../../../uri.ts";
import {ImageUrlResponse} from "../../types/ImageUrlResponse.ts";
import {compressImage} from "../../../../util/image/ImageCompress.ts";
import styles from "./ImageMcqForm.module.css"

export default function ImageMcqForm() {
  const [questions, setQuestions] = useState<ImageMcqQuestion[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const imageUploader = async (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    e.preventDefault();
    if (!e.target.value) return;

    // 1. 파일 존재 여부 체크 (안전한 접근)
    if (!e.target.files || e.target.files.length === 0) {
      console.log("No file selected");
      return;
    }

    try {

      const compressedFile = await compressImage(e.target.files[0]);

      setIsLoading(true);
      const formData = new FormData();
      formData.append("image", compressedFile);

      //서버측에 이미지 전달
      const response = await axios.post<{ imageUrl: string; }>(
        `${BASE_URI}/api/quiz/draft/image`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          }
        }
      );
      const data: ImageUrlResponse = response.data;
      const imageUrl = data.imageUrl;
      const copy: ImageMcqQuestion[] = [...questions];
      copy[index].imageUrl = imageUrl;
      setQuestions(copy);

      console.log('image upload success');
    } catch (error) {
      console.error("Upload failed: ", error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleAddQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    const newQuestion: ImageMcqQuestion = {
      imageUrl: null,
      choices: [{content: '', isAnswer: false}],
      answers: [],
    }
    setQuestions([...questions, newQuestion]);
  }

  const addChoice = (
    index: number
  ) => {
    setQuestions(prev =>
      prev.map((question, qi) => {
        return index !== qi ? question : {
          ...question,
          choices: [...question.choices, { content: "", isAnswer: false }]
        }
      })
    )
  }

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
            cIdx !== ci ? choice : { content: e.target.value, isAnswer: choice.isAnswer }
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
            cIdx !== ci ? choice : {...choice, isAnswer: !choice.isAnswer}
          )
        };
      })
    );
  }

  return (
    <section className={styles.main}>

      {questions.map((question, qi) => (
        <div className={styles.question} key={`question_${qi}`}>
          <div className={styles.fileInputContainer}>
            <input
              type="file"
              accept="image/*"
              onChange={(e) => imageUploader(e, qi)}
              id="file-upload"
              className={styles.hiddenInput}
            />
            <label htmlFor="file-upload" className={styles.customUploadBox}>
              <img
                src="/img/icon/add.svg"
                alt="파일 추가"
                className={styles.uploadIcon}
              />
            </label>
            <button
              className={styles.choiceAddButton}
              onClick={() => addChoice(qi)}>
              선택지 추가
            </button>
          </div>

          <div className={styles.choiceContainer}>
            {question.choices.map((choice, ci) => (
              <div className={styles.choice} key={`${qi}_${ci}_choice`}>
                <input
                  type="checkbox"
                  checked={question.choices[ci].isAnswer}
                  onChange={() => choiceAnswerCheck(qi, ci)}
                  className={styles.answerCheckbox}
                />
                <span>{ci + 1}. </span>
                <input
                  type="text"
                  placeholder="선택지를 입력하세요"
                  value={choice.content}
                  onChange={(e) => choiceInputChange(e, qi, ci)}
                />
              </div>
            ))}
          </div>
        </div>
      ))}
      <button
        className={styles.questionAddButton}
        onClick={(e) => handleAddQuestion(e)}
      >
        <img src={"/img/icon/add.svg"} alt="Add image"/>
        <span>문제 추가</span>
      </button>
    </section>
  )
}