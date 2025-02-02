export enum QuizTypes {
  IMAGE_MCQ = "IMAGE_MCQ",
  IMAGE_SUBJECTIVE = "IMAGE_SUBJECTIVE",
  AUDIO_MCQ = "AUDIO_MCQ",
  AUDIO_SUBJECTIVE = "AUDIO_SUBJECTIVE",
  BINARY_CHOICE = "BINARY_CHOICE",
}

export const getQuizTypeFrom = (name: string) => {
  switch (name.toUpperCase()) {
    case "IMAGE_MCQ":
      return QuizTypes.IMAGE_MCQ;
    case "IMAGE_SUBJECTIVE":
      return QuizTypes.IMAGE_SUBJECTIVE;
    case "AUDIO_MCQ":
      return QuizTypes.AUDIO_MCQ;
    case "AUDIO_SUBJECTIVE":
      return QuizTypes.AUDIO_SUBJECTIVE;
    case "BINARY_CHOICE":
      return QuizTypes.BINARY_CHOICE;
  }
}