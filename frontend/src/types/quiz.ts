import {ImageUploadStatus, ImageMcqQuestion, DetailQuestion} from "./question.ts";
import {QuizTypes} from "../components/quiz/types/Quiz.types.ts";

export interface NewQuizMetadata {
  title: string;
  thumbnailUrl: string;
  thumbnailImageUploadStatus: ImageUploadStatus;
  description: string;
  formerDraftId: string | null;
}

export const initNewQuizMetadata: NewQuizMetadata = {
  title: "",
  thumbnailUrl: "",
  thumbnailImageUploadStatus: ImageUploadStatus.NOT_UPLOADED,
  description: "",
  formerDraftId: null,
};

export interface CreateImageMcqRequest {
  title: string;
  description: string;
  type: QuizTypes;
  thumbnailUrl: string;
  questions: ImageMcqQuestion[];
}

// 퀴즈 카드에서 보여지는 데이터
export interface SimpleQuizItem {
  id: string;
  thumbnailUrl: string;
  title: string;
  description: string;
}

export interface QuizDetail {
  id: string;
  thumbnailUrl: string;
  title: string;
  description: string;
  type: QuizTypes;
  views: number;
  tries: number;
  scoreDistribution: number[];
  questions: DetailQuestion[];
}

export const initQuizDetail = {
  id: "quiz_id",
  thumbnailUrl: "thumbnail_url",
  title: "title",
  description: "description",
  type: QuizTypes.IMAGE_MCQ,
  scoreDistribution: [],
  views: 0,
  tries: 0,
  questions: []
}