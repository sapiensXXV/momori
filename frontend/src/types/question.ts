import {ImageBinaryChoice, ImageMcqChoice, VideoBinaryChoice, VideoMcqChoice} from "./choice.ts";

export enum ImageUploadStatus {
  NOT_UPLOADED = "not_uploaded",
  UPLOADED = "uploaded",
  PENDING = "pending"
}

export enum VideoUploadStatus {
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

export interface VideoMcqQuestion extends BaseQuestion {
  videoStatus: VideoUploadStatus;
  videoUrl: string;
  choices: VideoMcqChoice[];
}

export interface VideoSubjectiveQuestion extends BaseQuestion {
  videoStatus: VideoUploadStatus;
  videoUrl: string;
  answers: string[];
}

export interface ImageBinaryQuestion extends BaseQuestion {
  first: ImageBinaryChoice;
  second: ImageBinaryChoice;
}

export interface VideoBinaryQuestion extends BaseQuestion {
  first: VideoBinaryChoice;
  second: VideoBinaryChoice;
}

export type QuestionTypes =
  | ImageMcqQuestion
  | ImageSubjectiveQuestion
  | VideoMcqQuestion
  | VideoSubjectiveQuestion
  | ImageBinaryQuestion
  | VideoBinaryQuestion


