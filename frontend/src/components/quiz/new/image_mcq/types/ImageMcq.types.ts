export type ImageMcqMetadata = {
  title: string | null;
  description: string | null;
}

export const imageMcqMetadataInit: ImageMcqMetadata = {title: '', description: ''};

export type ImageMcqQuestion = {
  imageUrl: string | null;
  choices: ImageMcqChoice[];
}

export type ImageMcqChoice = {
  content: string;
  isAnswer: boolean;
}

