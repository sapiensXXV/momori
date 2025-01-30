import React from "react";
import {ImageMcqQuestion} from "./ImageMcq.types.ts";

export type ImageMcqQuestionFormProps = {
  question: ImageMcqQuestion;
  imageUploader: (e: React.ChangeEvent<HTMLInputElement>, index: number) => Promise<void>;
  deleteQuestion: (qi: number) => void;
  addChoice: (index: number) => void;
  choiceAnswerCheck: (qi: number, ci: number) => void;
  choiceInputChange: (e: React.ChangeEvent<HTMLInputElement>, qi: number, ei: number) => void;
  qi: number;
}

// export type ImageMcqMetadataFormProps = {
//   editTitle: (e: React.ChangeEvent<HTMLInputElement>) => void;
//   editDescription: (e: React.ChangeEvent<HTMLTextAreaElement>) => void;
//   clickDraftButton: () => void;
//   draftCount: number;
// }