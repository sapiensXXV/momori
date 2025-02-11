import {AudioUploadStatus, ImageUploadStatus} from "./question.ts";

export interface NewImageMcqChoice {
  content: string;
  answer: boolean;
}

export interface NewVideoMcqChoice {
  content: string;
  answer: boolean;
}

export interface NewImageBinaryChoice {
  description: string;
  imageUrl: string;
  imageStatus: ImageUploadStatus;
  answer: boolean;
}

export interface NewAudioBinaryChoice {
  description: string;
  audioUrl: string;
  audioStatus: AudioUploadStatus;
  answer: boolean;
}

// ----------------------- permanent choice type ------------------------

export interface ImageMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface AudioMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface ImageBinaryChoice {
  description: string;
  isAnswer: boolean;
}

export interface AudioBinaryChoice {
  description: string;
  isAnswer: boolean;
}

// ----------------------- quiz detail info -------------------------
// 단일 퀴즈 조회 시 사용

export interface ImageMcqDetailChoice {
  content: string;
  isAnswer: boolean;
  selectedCount: number;
}

//TODO: 다른 퀴즈 타입의 선택지 타입 정의 (주관식 문제의 경우에는 선택지가 없음)