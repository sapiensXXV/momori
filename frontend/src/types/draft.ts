export interface DraftSimpleInfo {
  draftId: string;
  title: string;
  createdAt: string;
}

export interface PushDraftResponse {
  imageUrl?: string;
  videoUrl?: string;
  draftId: string;
}

export interface pullDraftResponse {
  imageUrl?: string;
  videoUrl?: string;
  draftId: string;
}