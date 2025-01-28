import React, {useState} from "react";
import {ImageMcqQuestion} from "../../types/ImageMcq.types.ts";
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
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [draftCount, setDraftCount] = useState<number>(0);

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

  const deleteQuestion = (qi: number) => {
    setQuestions(prev =>
      prev.filter((_, qIdx) => qIdx !== qi)
    );
  }

  const addQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    const newQuestion: ImageMcqQuestion = {
      imageUrl: null,
      choices: [ {content: '', isAnswer: false} ],
    }
    setQuestions([...questions, newQuestion]);
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
    console.log('checkbox is checked!')
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
    const newTitle = e.target.value;
    setMetadata(prev => {
      return { title: newTitle, description: prev.description }
    })
  }

  const editDescription = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMetadata(prev => {
      return { title: prev.title, description: e.target.value }
    })
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