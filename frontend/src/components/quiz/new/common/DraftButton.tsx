import classes from "./DraftButton.module.css"
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {handleError} from "../../../../global/error/error.ts";
import {QuizTypes} from "../../types/Quiz.types.ts";
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {
  AudioUploadStatus,
  NewAudioMcqQuestion,
  NewAudioSubjectiveQuestion,
  NewImageBinaryQuestion,
  NewImageMcqQuestion,
  NewImageSubjectiveQuestion,
} from "../../../../types/question.ts";
import {PushDraftResponse} from "../../../../types/draft.ts";
import draftApiMap from "../../../../global/api/draft.ts";
import {
  AudioMcqDraftQuestion, AudioSubDraftQuestion,
  BaseDraftQuestion,
  BaseDraftRequest, ImageBinaryDraftQuestion,
  ImageMcqDraftQuestion,
  ImageSubDraftQuestion
} from "../../../../global/types/draft.ts";
import {NewQuestionContextMapping} from "../../../../global/types/quizContextMapping.ts";
import {NewAudioMcqChoice} from "../../../../types/choice.ts";


interface DraftButtonProps<T extends QuizTypes> {
  quizType: T;
}

const DraftButton = <T extends QuizTypes>({ quizType }: DraftButtonProps<T>) => {

  const { questions, metadata, setMetadata, draftCount, setDraftModal } = useQuizContext<NewQuestionContextMapping[T]>();
  const pushDraft = async () => {
    const request = makeDraftRequest();
    try {
      // 이미지 임시 저장 요청
      const response = await axiosJwtInstance.post<PushDraftResponse>(
        draftApiMap[quizType],
        request
      );
      setMetadata(prev => ({ ...prev, formerDraftId: response.data.draftId }));
      alert('임시저장 성공');
    } catch (error) {
      handleError(error);
    }
  }

  const showDraftList = async () => {
    setDraftModal(true);
  }

  const makeDraftRequest = (): BaseDraftRequest => {
    return {
      title: metadata.title ?? "제목 없음",
      thumbnailUrl: metadata.thumbnailUrl,
      description: metadata.description ?? "설명 없음",
      formerDraftId: metadata.formerDraftId,
      type: quizType,
      questions: makeDraftQuestionRequest(), // 오류 없애는 방법? 코딩만 잘했다면 없애지 안아도 되긴 함.
    };
  }

  const makeDraftQuestionRequest = (): BaseDraftQuestion[] => {
    if (quizType === QuizTypes.IMAGE_MCQ) {
      return questions
        .map((question) => question as NewImageMcqQuestion)
        .map((question): ImageMcqDraftQuestion => ({
          imageUrl: question.imageUrl,
          choices: question.choices.map((choice) => ({
            content: choice.content,
            answer: choice.answer
          }))
        }))
    } else if (quizType === QuizTypes.IMAGE_SUBJECTIVE) {
      return questions
        .map((question) => question as NewImageSubjectiveQuestion)
        .map((question): ImageSubDraftQuestion => ({
          imageUrl: question.imageUrl,
          answers: question.answers
        }));
    } else if (quizType === QuizTypes.AUDIO_MCQ) {
      return questions
        .map((question) => question as NewAudioMcqQuestion)
        .map((question): AudioMcqDraftQuestion => ({
          audioId: question.audioId,
          startTime: question.startTime,
          playDuration: question.playDuration,
          choices: question.choices.map((choice) => ({
            content: choice.content,
            answer: choice.answer
          }))
        }));
    } else if (quizType === QuizTypes.AUDIO_SUBJECTIVE) {
      return questions
        .map((question) => question as NewAudioSubjectiveQuestion)
        .map((question): AudioSubDraftQuestion => ({
          audioId: question.audioId,
          startTime: question.startTime,
          playDuration: question.playDuration,
          answers: question.answers
        }))
    } else if (quizType === QuizTypes.BINARY_CHOICE) {
      return questions
        .map((question) => question as NewImageBinaryQuestion)
        .map((question): ImageBinaryDraftQuestion => ({
          first: {
            imageUrl: question.first.imageUrl,
            description: question.first.description,
            answer: question.first.answer
          },
          second: {
            imageUrl: question.second.imageUrl,
            description: question.second.description,
            answer: question.second.answer
          }
        }))
    } else {
      console.log(quizType);
      throw new Error("지원되지 않는 퀴즈 타입입니다");
    }
  }

  return (
    <>
      <div className={classes.draftButtonContainer}>
        <div className={classes.draftPushButton} onClick={() => pushDraft()}>임시저장</div>
        <div className={classes.draftButtonDivider}></div>
        <div className={classes.draftPullButton} onClick={() => showDraftList()}>{draftCount}</div>
      </div>
    </>
  )
}

export default DraftButton;