export interface NewQuizMetadata {
  title: string;
  description: string;
  formerDraftId: string | null;
}

export const initNewQuizMetadata: NewQuizMetadata = {
  title: "",
  description: "",
  formerDraftId: null,
};
