import {ImageBinaryChoice, ImageMcqChoice, VideoBinaryChoice, VideoMcqChoice} from "./choice.ts";

enum ImageUploadStatus {
  NOT_UPLOADED = "not_uploaded",
  UPLOADED = "uploaded",
  PENDING = "pending"
}

enum VideoUploadStatus {
  NOT_UPLOADED = "not_uploaded",
  UPLOADED = "uploaded",
  PENDING = "pending"
}

interface BaseQuestion {
  title: string;
  description: string;
}

interface ImageMcqQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  choices: ImageMcqChoice[];
}

interface ImageSubjectiveQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  answers: string[];
}

interface VideoMcqQuestion extends BaseQuestion {
  videoStatus: VideoUploadStatus;
  videoUrl: string;
  choices: VideoMcqChoice[];
}

interface VideoSubjectiveQudstion extends BaseQuestion {
  videoStatus: VideoUploadStatus;
  videoUrl: string;
  answers: string[];
}

interface ImageBinaryQuestion extends BaseQuestion {
  first: ImageBinaryChoice;
  second: ImageBinaryChoice;
}

interface VideoBinaryQuestion extends BaseQuestion {
  first: VideoBinaryChoice;
  second: VideoBinaryChoice;
}

export type QuestionTypes =
  | ImageMcqQuestion
  | ImageSubjectiveQuestion
  | VideoMcqQuestion
  | VideoSubjectiveQudstion
  | ImageBinaryQuestion
  | VideoBinaryQuestion


