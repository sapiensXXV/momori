import React, {FC} from "react";
import {ImageMcqMetadataFormProps} from "./types/ImageMcqProps.types.ts";
import styles from "./ImageMcqForm.module.css";

const ImageMcqMetadataForm: FC<ImageMcqMetadataFormProps> = (
  {
    editTitle,
    editDescription,
    clickDraftButton,
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
        <button
          className={`${styles.draftButton} common-button`}
          onClick={() => clickDraftButton()}
        >임시저장 {draftCount}</button>
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