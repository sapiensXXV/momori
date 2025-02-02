import React, {useEffect, useState} from "react";
import styles from "./ImageMcqForm.module.css";
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {ImageMcqQuestion} from "../../../../types/question.ts";
import DraftButton from "../common/DraftButton.tsx";
import QuizCreateButton from "../common/QuizCreateButton.tsx";
import {DraftSimpleInfo} from "../../../../types/draft.ts";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import QuizDraftModal from "../common/modal/QuizDraftModal.tsx";


const ImageMcqMetadataForm = () => {

  const { metadata, setMetadata, setDraftCount } = useQuizContext<ImageMcqQuestion>()
  const [drafts, setDrafts] = useState<DraftSimpleInfo[]>([]);

  useEffect(() => {
    getDraftList();
  }, [])

  const getDraftList = async () => {
    const response = await axiosJwtInstance.get('/api/quizzes/draft');
    setDraftCount(response.data.length);
    setDrafts(response.data);
  }

  const editTitle = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMetadata(prev => ({...prev, title: e.target.value}));
  }

  const editDescription = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMetadata(prev => ({...prev, description: e.target.value}));
  }

  return (
    <>
      <QuizDraftModal  drafts={drafts}/>
      <div className={styles.metaDataContainer}>
        <div className={styles.titleAndDraftButtonContainer}>
          <input
            className={`${styles.quizTitleInput} common-input-md`}
            type={"text"}
            placeholder={"제목을 입력하세요"}
            value={metadata?.title}
            onChange={(e) => editTitle(e)}
          />
          <QuizCreateButton/>
          <DraftButton/>
        </div>
        <textarea
          className={`common-textarea`}
          placeholder={"설명을 입력하세요"}
          value={metadata?.description}
          onChange={(e) => editDescription(e)}
        />
      </div>
    </>

  )
}

export default ImageMcqMetadataForm;