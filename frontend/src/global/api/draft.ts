// API 엔드포인트 매핑 (예시)
import {QuizTypes} from "../../components/quiz/types/Quiz.types.ts";

const draftApiMap: Record<QuizTypes, string> = {
  [QuizTypes.IMAGE_MCQ]: "/api/quizzes/draft/image-mcq",
  [QuizTypes.IMAGE_SUBJECTIVE]: "/api/quizzes/draft/image-sub",
  [QuizTypes.AUDIO_MCQ]: "/api/quizzes/draft/audio-mcq",
  [QuizTypes.AUDIO_SUBJECTIVE]: "/api/quizzes/draft/audio-sub",
  [QuizTypes.BINARY_CHOICE]: "/api/quizzes/draft/image-binary",
};

export default draftApiMap;