import {ImageBinaryChoice, ImageMcqChoice, VideoBinaryChoice, VideoMcqChoice} from "./choice.ts";

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

export interface ImageMcqQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  choices: ImageMcqChoice[];
}

export interface ImageSubjectiveQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  answers: string[];
}

export interface AudioMcqQuestion extends BaseQuestion {
  videoStatus: AudioUploadStatus;
  videoUrl: string;
  choices: VideoMcqChoice[];
}

export interface AudioSubjectiveQuestion extends BaseQuestion {
  videoStatus: AudioUploadStatus;
  videoUrl: string;
  answers: string[];
}

export interface ImageBinaryQuestion extends BaseQuestion {
  first: ImageBinaryChoice;
  second: ImageBinaryChoice;
}

export interface AudioBinaryQuestion extends BaseQuestion {
  first: VideoBinaryChoice;
  second: VideoBinaryChoice;
}

export type QuestionTypes =
  | ImageMcqQuestion
  | ImageSubjectiveQuestion
  | AudioMcqQuestion
  | AudioSubjectiveQuestion
  | ImageBinaryQuestion
  | AudioBinaryQuestion


