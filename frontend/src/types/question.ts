import {ImageBinaryChoice, ImageMcqChoice, AudioBinaryChoice, VideoMcqChoice} from "./choice.ts";

export enum ImageUploadStatus {
  NOT_UPLOADED = "not_uploaded",
  UPLOADED = "uploaded",
  PENDING = "pending"
}

export enum AudioUploadStatus {
  NOT_UPLOADED = "not_uploaded",
  UPLOADED = "uploaded",
  PENDING = "pending"
}

interface BaseQuestion {
}

export interface NewImageMcqQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  choices: ImageMcqChoice[];
}

export interface NewImageSubjectiveQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  answers: string[];
}

export interface NewAudioMcqQuestion extends BaseQuestion {
  audioStatus: AudioUploadStatus;
  audioUrl: string;
  choices: VideoMcqChoice[];
}

export interface NewAudioSubjectiveQuestion extends BaseQuestion {
  audioStatus: AudioUploadStatus;
  audioUrl: string;
  answers: string[];
}

export interface NewImageBinaryQuestion extends BaseQuestion {
  first: ImageBinaryChoice;
  second: ImageBinaryChoice;
}

export interface NewAudioBinaryQuestion extends BaseQuestion {
  first: AudioBinaryChoice;
  second: AudioBinaryChoice;
}

export type QuestionTypes =
  | NewImageMcqQuestion
  | NewImageSubjectiveQuestion
  | NewAudioMcqQuestion
  | NewAudioSubjectiveQuestion
  | NewImageBinaryQuestion
  | NewAudioBinaryQuestion


