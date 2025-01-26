export type ImageMcqQuestion = {
  imageUrl: string | null;
  choices: ImageMcqChoice[];
  answers: number[];
}

export type ImageMcqChoice = {
  content: string;
  isAnswer: boolean;
}