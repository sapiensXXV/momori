export type ImageMcqQuestion = {
  imageUrl: string | null;
  choices: ImageMcqChoice[];
}

export type ImageMcqChoice = {
  content: string;
  isAnswer: boolean;
}