export interface ImageMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface VideoMcqChoice {
  content: string;
  isAnswer: boolean;
}

export interface ImageBinaryChoice {
  description: string;
  imageUrl: string;
  isAnswer: boolean;
}

export interface VideoBinaryChoice {
  description: string;
  videoUrl: string;
  isAnswer: boolean;
}

