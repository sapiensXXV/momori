import React, {useState} from "react";
import { ImageMcqQuestion } from "./types/ImageMcq.types.ts";
import axios from "axios";
import {BASE_URI} from "../../../../uri.ts";
import {ImageUrlResponse} from "../../types/ImageUrlResponse.ts";
import {compressImage} from "../../../../util/image/ImageCompress.ts";
import styles from "./ImageMcqForm.module.css"
import ImageMcqQuestionForm from "./ImageMcqQuestionForm.tsx";
import {ImageMcqMetadata, imageMcqMetadataInit} from "./types/ImageMcq.types.ts";
import ImageMcqMetadataForm from "./ImageMcqMetadataForm.tsx";

export default function ImageMcqForm() {
  const [questions, setQuestions] = useState<ImageMcqQuestion[]>([]);
  const [metadata, setMetadata] = useState<ImageMcqMetadata>(imageMcqMetadataInit);
  const [draftCount, setDraftCount] = useState<number>(0);

  const imageUploader = async (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    e.preventDefault();
    console.log(`image uploader run with index=${index}`)
    if (!e.target.value) return;

    // 1. 파일 존재 여부 체크 (안전한 접근)
    if (!e.target.files || e.target.files.length === 0) {
      console.log("No file selected");
      return;
    }
    
    changeImageUploadStatus("pending", index);
    try {
      const compressedFile = await compressImage(e.target.files[0]);
      const formData = new FormData();
      formData.append("image", compressedFile);
      formData.append("prevImageUrl", questions[index].imageUrl)

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
      changeImageUploadStatus("uploaded", index);
    }
  };

  const changeImageUploadStatus = (status: string, qi: number) => {
    console.log(`changeImage Status: status=${status}, question_index=${qi}`);
    setQuestions(prev => 
      prev.map((question, index) => 
        qi !== index ? question : { ...question, imageStatus: status }
      )
    );
  };

  const deleteQuestion = (qi: number) => {
    setQuestions(prev =>
      prev.filter((_, index) => index !== qi)
    );
  }

  const addQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    setQuestions(prev => [
      ...prev,
      { imageStatus: "not_uploaded", imageUrl: null, choices: [{ content: "", isAnswer: false }] }
    ])
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
          choices: [...question.choices, { content: "", isAnswer: false }]
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

  const editTitle = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMetadata(prev => ({ ...prev, title: e.target.value }));
  }

  const editDescription = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMetadata(prev => ({ ...prev, description: e.target.value }));
  }

  const clickDraftButton = () => {
    console.log('draft button clicked!')
  }


  return (
    <>
      <ImageMcqMetadataForm
        editTitle={editTitle}
        editDescription={editDescription}
        clickDraftButton={clickDraftButton}
        draftCount={draftCount}
      />
      <section className={`${styles.questionContainer} common-flex-column`}>
        {questions.map((question, qi) => (
          <ImageMcqQuestionForm
            key={`question_${qi}`}
            question={question}
            imageUploader={imageUploader}
            deleteQuestion={deleteQuestion}
            addChoice={addChoice}
            choiceAnswerCheck={choiceAnswerCheck}
            choiceInputChange={choiceInputChange}
            qi={qi}
          />
        ))}
        <button
          className={styles.questionAddButton}
          onClick={(e) => addQuestion(e)}
        >
          <img src={"/img/icon/add.svg"} alt="Add image"/>
          <span>문제 추가</span>
        </button>
      </section>
    </>

  )
}