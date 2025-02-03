import {ImageUploadStatus} from "./question.ts";

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
