import classes from './AudioSubForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {NewAudioSubjectiveQuestion} from "../../../../types/question.ts";
import React, {useEffect, useState} from "react";
import {DraftSimpleInfo} from "../../../../types/draft.ts";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import QuizDraftModal from "../common/modal/QuizDraftModal.tsx";
import NewQuizThumbnail from "../common/thumbnail/NewQuizThumbnail.tsx";
import QuizCreateButton from "../common/QuizCreateButton.tsx";
import DraftButton from "../common/DraftButton.tsx";

const AudioSubMetadataForm = () => {

  const {quizType, metadata, setMetadata, setDraftCount} = useQuizContext<NewAudioSubjectiveQuestion>();
  const [drafts, setDrafts] = useState<DraftSimpleInfo[]>([]);

  useEffect(() => {
    fetchDraftList();
  }, []);

  const fetchDraftList = async () => {
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
      <QuizDraftModal drafts={drafts}/>
      <div className={classes.metaDataContainer}>
        <NewQuizThumbnail/>
        <div className={classes.titleAndDraftButtonContainer}>
          <input
            className={`${classes.quizTitleInput} common-input-md`}
            type={"text"}
            placeholder={"제목을 입력하세요"}
            value={metadata?.title}
            onChange={(e) => editTitle(e)}
          />
          <QuizCreateButton/>
          <DraftButton quizType={quizType}/>
        </div>
        <textarea
          className={`common-textarea`}
          placeholder={"설명을 입력하세요"}
          value={metadata?.description}
          onChange={(e) => editDescription(e)}
        />
      </div>
    </>
  );
}

export default AudioSubMetadataForm;