import React, {useState} from "react";
import {ImageMcqMetadata, imageMcqMetadataInit, ImageMcqQuestion} from "./types/ImageMcq.types.ts";
import {ImageUrlResponse} from "../../types/ImageUrlResponse.ts";
import {compressImage} from "../../../../util/image/ImageCompress.ts";
import styles from "./ImageMcqForm.module.css"
import ImageMcqQuestionForm from "./ImageMcqQuestionForm.tsx";
import ImageMcqMetadataForm from "./ImageMcqMetadataForm.tsx";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {QuizTypes} from "../../types/Quiz.types.ts";
import {handleError} from "../../../../global/error/error.ts";

interface ImageMcqDraftRequest {
  title: string;
  description: string;
  type: QuizTypes;
  questions: ImageMcqDraftQuestionRequest[];
}

interface ImageMcqDraftQuestionRequest {
  imageUrl: string;
  choices: ImageMcqDraftChoiceRequest[];
}

interface ImageMcqDraftChoiceRequest {
  content: string;
  isAnswer: boolean;
}

export default function ImageMcqForm() {
  const [questions, setQuestions] = useState<ImageMcqQuestion[]>([]);
  const [metadata, setMetadata] = useState<ImageMcqMetadata>(imageMcqMetadataInit);
  const [draftCount, setDraftCount] = useState<number>(0);

  const pushDraftQuiz = async () => {
    console.log('draft quiz button clicked')
    const request = makeDraftRequest();
    console.log(request);
    try {
      // 이미지 임시 저장 요청
      await axiosJwtInstance.post<{ imageUrl: string; }>(
        `/api/quizzes/draft/image-mcq`,
        request
      );
      alert('임시저장 성공');
    } catch (error) {
      handleError(error);
    }
  }

  const pullDraftQuiz = async () => {
    console.log('pull draft quiz');
  }

  const makeDraftRequest = () => {
    const request: ImageMcqDraftRequest = {
      title: metadata.title ?? "제목 없음",
      description: metadata.description ?? "설명 없음",
      type: QuizTypes.IMAGE_MCQ,
      questions: makeDraftQuestionRequest(),
    }
    return request;
  }

  const makeDraftQuestionRequest = () => {
    return questions.map((question) => {
      return {
        imageUrl: question.imageUrl,
        choices: makeDraftChoiceRequest(question),
      }
    })
  }

  const makeDraftChoiceRequest = (question: ImageMcqQuestion) => {
    return question.choices.map((choice) => {
      return {content: choice.content, isAnswer: choice.isAnswer}
    });
  }

  const imageUploader = async (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    e.preventDefault();
    console.log('imageUploader call')
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
      const copy: ImageMcqQuestion[] = [...questions];
      copy[index].imageUrl = imageUrl;
      setQuestions(copy);

      console.log('image upload success');
      changeImageUploadStatus("uploaded", index);
    } catch (error) {
      changeImageUploadStatus("not_uploaded", index);
      alert("이미지 업로드에 실패하였습니다.")
    }
  };

  const changeImageUploadStatus = (status: string, qi: number) => {
    setQuestions(prev =>
      prev.map((question, index) =>
        qi === index ? { ...question, imageStatus: status } : question
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
      {imageStatus: "not_uploaded", imageUrl: null, choices: [{content: "", isAnswer: false}]}
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
          choices: [...question.choices, {content: "", isAnswer: false}]
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
            cIdx !== ci ? choice : {content: e.target.value, isAnswer: choice.isAnswer}
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
    setMetadata(prev => ({...prev, title: e.target.value}));
  }

  const editDescription = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMetadata(prev => ({...prev, description: e.target.value}));
  }

  return (
    <>
      <ImageMcqMetadataForm
        editTitle={editTitle}
        editDescription={editDescription}
        pushDraft={pushDraftQuiz}
        pullDraft={pullDraftQuiz}
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