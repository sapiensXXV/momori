import React, {FC} from "react";
import styles from "./ImageMcqForm.module.css";
import DraftButton from "../../common/DraftButton.tsx";

type ImageMcqMetadataFormProps = {
  editTitle: (e: React.ChangeEvent<HTMLInputElement>) => void;
  editDescription: (e: React.ChangeEvent<HTMLTextAreaElement>) => void;
  pullDraft: () => void;
  pushDraft: () => void;
  draftCount: number;
}

const ImageMcqMetadataForm: FC<ImageMcqMetadataFormProps> = (
  {
    editTitle,
    editDescription,
    pullDraft,
    pushDraft,
    draftCount
  }) => {
  return (
    <div className={styles.metaDataContainer}>
      <div className={styles.titleAndDraftButtonContainer}>
        <input
          className={`${styles.quizTitleInput} common-input-md`}
          type={"text"}
          placeholder={"제목을 입력하세요"}
          onChange={(e) => editTitle(e)}
        />
        <DraftButton pushDraft={pushDraft} pullDraft={pullDraft} count={draftCount}/>
      </div>
      <textarea
        className={`common-textarea`}
        placeholder={"설명을 입력하세요"}
        onChange={(e) => editDescription(e)}
      />
    </div>
  )
}

export default ImageMcqMetadataForm;