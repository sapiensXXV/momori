import {
  NewImageBinaryChoice,
  NewImageMcqChoice,
  NewAudioBinaryChoice,
  NewVideoMcqChoice,
  ImageMcqChoice, AudioBinaryChoice, ImageBinaryChoice, AudioMcqChoice, ImageMcqChoiceDetail, ImageMcqDetailChoice
} from "./choice.ts";

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
  choices: NewImageMcqChoice[];
}

export interface NewImageSubjectiveQuestion extends BaseQuestion {
  imageStatus: ImageUploadStatus;
  imageUrl: string;
  answers: string[];
}

export interface NewAudioMcqQuestion extends BaseQuestion {
  audioStatus: AudioUploadStatus;
  audioUrl: string;
  choices: NewVideoMcqChoice[];
}

export interface NewAudioSubjectiveQuestion extends BaseQuestion {
  audioStatus: AudioUploadStatus;
  audioUrl: string;
  answers: string[];
}

export interface NewImageBinaryQuestion extends BaseQuestion {
  first: NewImageBinaryChoice;
  second: NewImageBinaryChoice;
}

export interface NewAudioBinaryQuestion extends BaseQuestion {
  first: NewAudioBinaryChoice;
  second: NewAudioBinaryChoice;
}

export type NewQuestionTypes =
  | NewImageMcqQuestion
  | NewImageSubjectiveQuestion
  | NewAudioMcqQuestion
  | NewAudioSubjectiveQuestion
  | NewImageBinaryQuestion
  | NewAudioBinaryQuestion


// ----------------------------------------- permanent quiz type -----------------------------------------

export interface ImageMcqQuestion extends BaseQuestion {
  imageUrl: string;
  choices: ImageMcqChoice[];
}

export interface ImageSubjectiveQuestion extends BaseQuestion {
  imageUrl: string;
  answers: string[];
}

export interface AudioMcqQuestion extends BaseQuestion {
  audioUrl: string;
  choices: AudioMcqChoice[];
}

export interface AudioSubjectiveQuestion extends BaseQuestion {
  audioUrl: string;
  answers: string[];
}

export interface ImageBinaryQuestion extends BaseQuestion {
  first:ImageBinaryChoice;
  second: ImageBinaryChoice;
}

export interface AudioBinaryQuestion extends BaseQuestion {
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


// ---------------------------------------- quiz detail info -------------------------------------------
export interface DetailQuestion {
  questionId: string;
  correctCount: number;
  tryCount: number;
}

export interface ImageMcqDetailQuestion extends DetailQuestion {
  imageUrl: string;
  choices: ImageMcqDetailChoice[];
}

export interface ImageSubjectiveDetailQuestion extends DetailQuestion {
  imageUrl: string;
  answers: string[];
}

//TODO: 다른 퀴즈 타입의 문제 타입 정의