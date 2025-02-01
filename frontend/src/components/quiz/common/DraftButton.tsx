import classes from "./DraftButton.module.css"
import { FC } from "react";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {PushDraftResponse} from "../../../types/draft.ts";
import {handleError} from "../../../global/error/error.ts";
import {QuizTypes} from "../types/Quiz.types.ts";
import {ImageMcqQuestion} from "../../../types/question.ts";
import {useQuizContext} from "../../../context/QuizContext.tsx";

interface DraftButtonProps {
  count: number;
}

interface ImageMcqDraftRequest {
  title: string;
  description: string;
  type: QuizTypes;
  formerDraftId: string | null;
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

const DraftButton: FC<DraftButtonProps> = ({
  count
}) => {

  const { questions, metadata, setMetadata } = useQuizContext<ImageMcqQuestion>()

  const pushDraft = async () => {
    console.log('draft quiz button clicked')
    const request = makeDraftRequest();
    console.log(request);
    try {
      // 이미지 임시 저장 요청
      const response = await axiosJwtInstance.post<PushDraftResponse>(
        `/api/quizzes/draft/image-mcq`,
        request
      );

      setMetadata(prev => ({ ...prev, formerDraftId: response.data.draftId }));
      console.log(response);
      alert('임시저장 성공');
    } catch (error) {
      handleError(error);
    }
  }

  const pullDraft = async () => {
    console.log('pull draft quiz');
  }

  const makeDraftRequest = () => {
    const request: ImageMcqDraftRequest = {
      title: metadata.title ?? "제목 없음",
      description: metadata.description ?? "설명 없음",
      formerDraftId: metadata.formerDraftId,
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

  return (
    <>
      <div className={classes.draftButtonContainer}>
        <div className={classes.draftPushButton} onClick={() => pushDraft()}>임시저장</div>
        <div className={classes.draftButtonDivider}></div>
        <div className={classes.draftPullButton} onClick={() => pullDraft()}>{count}</div>
      </div>

    </>
  )
}

export default DraftButton;