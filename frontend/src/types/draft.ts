import {
  AudioBinaryQuestion,
  AudioMcqQuestion,
  AudioSubjectiveQuestion,
  ImageBinaryQuestion,
  ImageMcqQuestion,
  ImageSubjectiveQuestion
} from "./question.ts";
import {QuizTypes} from "../components/quiz/types/Quiz.types.ts";

export interface DraftSimpleInfo {
  draftId: string;
  quizType: string;
  title: string;
  createdAt: string;
}

export interface PushDraftResponse {
  imageUrl?: string;
  videoUrl?: string;
  draftId: string;
}

export interface pullDraftResponse {
  imageUrl?: string;
  videoUrl?: string;
  draftId: string;
}

export interface BaseDraft {
  draftId: string;
  quizType: string;
  title: string;
  description: string;
}

export interface ImageMcqDraftData extends BaseDraft {
  questions: ImageMcqQuestion[]
}

export interface ImageSubjectiveDraftData extends BaseDraft {
  questions: ImageSubjectiveQuestion[];
}

export interface AudioMcqDraftData extends BaseDraft {
  questions: AudioMcqQuestion[];
}

export interface AudioSubjectiveDraftData extends BaseDraft {
  questions: AudioSubjectiveQuestion[];
}

export interface ImageBinaryDraftData extends BaseDraft {
  questions: ImageBinaryQuestion[];
}

export interface AudioBinaryDraftData extends BaseDraft {
  questions: AudioBinaryQuestion[];
}
