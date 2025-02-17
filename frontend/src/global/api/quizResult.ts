import {QuizTypes} from "../../components/quiz/types/Quiz.types.ts";

const quizResultApiMap: Record<QuizTypes, string> = {
  [QuizTypes.IMAGE_MCQ]: "/api/quiz/result/img-mcq",
  [QuizTypes.IMAGE_SUBJECTIVE]: "/api/quiz/result/img-sub",
  [QuizTypes.AUDIO_MCQ]: "/api/quiz/result/audio-mcq",
  [QuizTypes.AUDIO_SUBJECTIVE]: "/api/quiz/result/audio-sub",
  [QuizTypes.BINARY_CHOICE]: "/api/quiz/result/img-bin",
};

export default quizResultApiMap;