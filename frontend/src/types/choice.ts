import {AudioUploadStatus, ImageUploadStatus} from "./question.ts";

export interface NewImageMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface NewVideoMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface NewImageBinaryChoice {
  description: string;
  imageUrl: string;
  imageStatus: ImageUploadStatus;
  isAnswer: boolean;
}

export interface NewAudioBinaryChoice {
  description: string;
  audioUrl: string;
  audioStatus: AudioUploadStatus;
  isAnswer: boolean;
}

// ----------------------- permanent choice type ------------------------

export interface ImageMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface AudioMcqChoice {
  content: string;
  audioUrl: string;
  isAnswer: boolean;
}

export interface ImageBinaryChoice {
  description: string;
  imageUrl: string;
  isAnswer: boolean;
}

export interface AudioBinaryChoice {
  description: string;
  audioUrl: string;
  isAnswer: boolean;
}
