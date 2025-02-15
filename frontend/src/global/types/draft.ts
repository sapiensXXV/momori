import {QuizTypes} from "../../components/quiz/types/Quiz.types.ts";


// 임시저장 문제끼리 공통으로 가지는 프로퍼티가 없기 때문에 Union Type 으로 선언
export type BaseDraftQuestion =
  | ImageMcqDraftQuestion
  | ImageSubDraftQuestion
  | AudioMcqDraftQuestion
  | AudioSubDraftQuestion
  | ImageBinaryDraftQuestion;

export interface ImageMcqDraftQuestion {
  imageUrl: string;
  choices: {
    content: string;
    answer: boolean;
  }[]
}
export interface ImageSubDraftQuestion {
  imageUrl: string;
  answers: string[];
}

export interface AudioMcqDraftQuestion {
  audioUrl: string;
  choices: {
    content: string;
    answer: boolean;
  }[]
}

export interface AudioSubDraftQuestion {
  audioUrl: string;
  answers: string[];
}

export interface ImageBinaryDraftQuestion {
  first: {
    imageUrl: string;
    description: string;
    answer: boolean;
  };
  second: {
    imageUrl: string;
    description: string;
    answer: boolean;
  };
}

export interface BaseDraftRequest {
  title: string;
  description: string;
  thumbnailUrl: string;
  type: QuizTypes;
  formerDraftId: string | null;
  questions: BaseDraftQuestion[];
}