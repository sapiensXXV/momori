export type ImageMcqMetadata = {
  title: string | null;
  description: string | null;
}

export const imageMcqMetadataInit: ImageMcqMetadata = {title: '', description: ''};

export type ImageMcqQuestion = {
  imageStatus: "not_uploaded" | "pending" | "uploaded";
  imageUrl: string;
  choices: ImageMcqChoice[];
}

export type ImageMcqChoice = {
  content: string;
  isAnswer: boolean;
}

