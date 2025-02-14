import classes from "./DraftButton.module.css"
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {handleError} from "../../../../global/error/error.ts";
import {QuizTypes} from "../../types/Quiz.types.ts";
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {
  NewAudioMcqQuestion,
  NewAudioSubjectiveQuestion,
  NewImageBinaryQuestion,
  NewImageMcqQuestion,
  NewImageSubjectiveQuestion,
  NewQuestionTypes
} from "../../../../types/question.ts";
import {PushDraftResponse} from "../../../../types/draft.ts";
import draftApiMap from "../../../../global/api/draft.ts";


interface DraftButtonProps<T extends QuizTypes> {
  quizType: T;
}

interface QuizContextMapping {
  [QuizTypes.IMAGE_MCQ]: NewImageMcqQuestion;
  [QuizTypes.IMAGE_SUBJECTIVE]: NewImageSubjectiveQuestion;
  [QuizTypes.AUDIO_MCQ]: NewAudioMcqQuestion;
  [QuizTypes.AUDIO_SUBJECTIVE]: NewAudioSubjectiveQuestion;
  [QuizTypes.BINARY_CHOICE]: NewImageBinaryQuestion;
}

// 임시저장 문제끼리 공통으로 가지는 프로퍼티가 없기 때문에 Union Type 으로 선언
type BaseDraftQuestion = ImageMcqDraftQuestion | ImageSubDraftQuestion;

interface ImageMcqDraftQuestion {
  imageUrl: string;
  choices: {
    content: string;
    answer: boolean;
  }[]
}
interface ImageSubDraftQuestion {
  imageUrl: string;
  answers: string[];
}

interface BaseDraftRequest {
  title: string;
  description: string;
  thumbnailUrl: string;
  type: QuizTypes;
  formerDraftId: string | null;
  questions: BaseDraftQuestion[];
}

interface ImageMcqDraftRequest extends BaseDraftRequest {
  questions: ImageMcqDraftQuestion[];
}

interface ImageSubDraftRequest extends BaseDraftRequest {
  questions: ImageSubDraftQuestion[];
}

const DraftButton = <T extends QuizTypes>({ quizType }: DraftButtonProps<T>) => {

  const { quizType, questions, metadata, setMetadata, draftCount, setDraftModal } = useQuizContext<QuizContextMapping[T]>();
  const pushDraft = async () => {
    console.log('draft quiz button clicked')
    const request = makeDraftRequest();
    console.log(request);
    console.log(draftApiMap[quizType]);
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
      questions: makeDraftQuestionRequest(),
    };
  }

  // 현재 퀴즈 타입에 따라 다른 API 주소를 가져오는 메서드

  // const convertQuestionType = (question: any) => {
  //   switch (quizType) {
  //     case QuizTypes.IMAGE_MCQ:
  //       return question as NewImageMcqQuestion;
  //     case QuizTypes.IMAGE_SUBJECTIVE:
  //       return question as NewImageSubjectiveQuestion;
  //     case QuizTypes.AUDIO_MCQ:
  //       return question as NewAudioMcqQuestion
  //     case QuizTypes.AUDIO_SUBJECTIVE:
  //       return question as NewAudioSubjectiveQuestion
  //     case QuizTypes.BINARY_CHOICE:
  //       return question as NewImageBinaryQuestion
  //   }
  // }

  const makeDraftQuestionRequest = () => {
    // TODO: 퀴즈 타입에 따라서 다른 타입의 question을 가지는 요청 객체를 반환해야함. (BaseDraftRequest 의 서브 타입)

    if (quizType === QuizTypes.IMAGE_MCQ) {
      return questions
        .map((question) => question as NewImageMcqQuestion)
        .map((question) => ({
          imageUrl: question.imageUrl,
          choices: question.choices.map((choice) => ({
            content: choice.content,
            answer: choice.answer
          }))
        }));
    } else if (quizType === QuizTypes.IMAGE_SUBJECTIVE) {
      return questions
        .map((question) => question as NewImageSubjectiveQuestion)
        .map((question) => ({
          imageUrl: question.imageUrl,
          answers: question.answers
        }));
    } else if (quizType === QuizTypes.AUDIO_MCQ) {
      return questions
        .map((question) => question as NewAudioMcqQuestion)
        .map((question) => ({
          audioUrl: question.audioUrl,
          choices: question.choices.map((choice) => ({
            content: choice.content,
            answer: choice.answer
          }))
        }));
    } else if (quizType === QuizTypes.AUDIO_SUBJECTIVE) {
      // TODO: 오디오-주관식 타입 임시저장 요청 데이터 생성
      return null;
    } else if (quizType === QuizTypes.BINARY_CHOICE) {
      // TODO: 이미지-이지선다 타입 임시저장 요청 데이터 생성
      return null;
    }
  }

  const makeDraftChoiceRequest = (question: NewImageMcqQuestion) => {
    return question.choices.map((choice) => {
      return {content: choice.content, answer: choice.answer}
    });
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