import {AudioUploadStatus, ImageUploadStatus} from "./question.ts";

export interface ImageMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface VideoMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface ImageBinaryChoice {
  description: string;
  imageUrl: string;
  imageStatus: ImageUploadStatus;
  isAnswer: boolean;
}

export interface AudioBinaryChoice {
  description: string;
  audioUrl: string;
  audioStatus: AudioUploadStatus;
  isAnswer: boolean;
}

