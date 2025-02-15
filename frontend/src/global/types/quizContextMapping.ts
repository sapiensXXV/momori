import {QuizTypes} from "../../components/quiz/types/Quiz.types.ts";
import {
  NewAudioMcqQuestion,
  NewAudioSubjectiveQuestion, NewImageBinaryQuestion,
  NewImageMcqQuestion,
  NewImageSubjectiveQuestion
} from "../../types/question.ts";

export interface NewQuestionContextMapping {
  [QuizTypes.IMAGE_MCQ]: NewImageMcqQuestion;
  [QuizTypes.IMAGE_SUBJECTIVE]: NewImageSubjectiveQuestion;
  [QuizTypes.AUDIO_MCQ]: NewAudioMcqQuestion;
  [QuizTypes.AUDIO_SUBJECTIVE]: NewAudioSubjectiveQuestion;
  [QuizTypes.BINARY_CHOICE]: NewImageBinaryQuestion;
}
