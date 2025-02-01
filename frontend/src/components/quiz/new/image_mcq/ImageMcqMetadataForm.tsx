import React from "react";
import styles from "./ImageMcqForm.module.css";
import DraftButton from "../../common/DraftButton.tsx";
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {ImageMcqQuestion} from "../../../../types/question.ts";


const ImageMcqMetadataForm = () => {

  const { setMetadata, draftCount } = useQuizContext<ImageMcqQuestion>()

  const editTitle = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMetadata(prev => ({...prev, title: e.target.value}));
  }

  const editDescription = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMetadata(prev => ({...prev, description: e.target.value}));
  }

  return (
    <div className={styles.metaDataContainer}>
      <div className={styles.titleAndDraftButtonContainer}>
        <input
          className={`${styles.quizTitleInput} common-input-md`}
          type={"text"}
          placeholder={"제목을 입력하세요"}
          onChange={(e) => editTitle(e)}
        />
        <DraftButton count={draftCount}/>
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