import {NewImageMcqQuestion, ImageUploadStatus} from "./question.ts";
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
  questions: NewImageMcqQuestion[];
}