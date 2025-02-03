import {
  NewAudioBinaryQuestion,
  NewAudioMcqQuestion,
  NewAudioSubjectiveQuestion,
  NewImageBinaryQuestion,
  NewImageMcqQuestion,
  NewImageSubjectiveQuestion
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
  thumbnailUrl: string;
  title: string;
  description: string;
}

export interface ImageMcqDraftData extends BaseDraft {
  questions: NewImageMcqQuestion[]
}

export interface ImageSubjectiveDraftData extends BaseDraft {
  questions: NewImageSubjectiveQuestion[];
}

export interface AudioMcqDraftData extends BaseDraft {
  questions: NewAudioMcqQuestion[];
}

export interface AudioSubjectiveDraftData extends BaseDraft {
  questions: NewAudioSubjectiveQuestion[];
}

export interface ImageBinaryDraftData extends BaseDraft {
  questions: NewImageBinaryQuestion[];
}

export interface AudioBinaryDraftData extends BaseDraft {
  questions: NewAudioBinaryQuestion[];
}
