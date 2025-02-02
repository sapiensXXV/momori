import {
  AudioMcqDraftData,
  AudioSubjectiveDraftData,
  BaseDraft,
  DraftSimpleInfo,
  ImageBinaryDraftData,
  ImageMcqDraftData,
  ImageSubjectiveDraftData
} from "../../../../../types/draft.ts";
import classes from './QuizDraftModal.module.css'
import {FC} from "react";
import {useQuizContext} from "../../../../../context/QuizContext.tsx";
import {formatDateTime} from "../../../../../global/util/date/date.ts";
import {axiosJwtInstance} from "../../../../../global/configuration/axios.ts";
import {getQuizTypeFrom, QuizTypes} from "../../../types/Quiz.types.ts";
import {AudioUploadStatus, ImageUploadStatus} from "../../../../../types/question.ts";

type QuizDraftModalContentItem = {
  draft: DraftSimpleInfo;
}

const QuizDraftModalContentItem: FC<QuizDraftModalContentItem> = ({ draft }) => {

  const { setMetadata, setQuizType, setQuestions, setDraftModal } = useQuizContext();

  const loadDraftApi = (draftType: string) => {
    switch (draftType) {
      case QuizTypes.IMAGE_MCQ.toString():
        return "/api/quizzes/draft/image-mcq";
      case QuizTypes.IMAGE_SUBJECTIVE.toString():
        return "/api/quizzes/draft/image-sub";
      case QuizTypes.AUDIO_MCQ.toString():
        return "/api/quizzes/draft/audio-mcq";
      case QuizTypes.AUDIO_SUBJECTIVE.toString():
        return "/api/quizzes/draft/audio-sub";
      case QuizTypes.BINARY_CHOICE.toString():
        return "/api/quizzes/draft/binary";
    }
  }

  const loadDraftItem = async () => {
    const response = await axiosJwtInstance.get(
      `${loadDraftApi(draft.quizType)}?draftId=${draft.draftId}`
      );
    const data: BaseDraft = response.data as BaseDraft;

    switch (getQuizTypeFrom(data.quizType)) {
      case QuizTypes.IMAGE_MCQ: {
        const questions = (data as ImageMcqDraftData).questions;
        const result = questions.map(prev => ( {...prev, imageStatus: ImageUploadStatus.UPLOADED} ));
        setQuestions(result);
        break;
      }
      case QuizTypes.IMAGE_SUBJECTIVE: {
        const questions = (data as ImageSubjectiveDraftData).questions;
        const result = questions.map(prev => ( {...prev, imageStatus: ImageUploadStatus.UPLOADED} ));
        setQuestions(result);
        break;
      }
      case QuizTypes.AUDIO_MCQ: {
        const questions = (data as AudioMcqDraftData).questions;
        const result = questions.map(prev => ( {...prev, audioStatus: AudioUploadStatus.UPLOADED} ));
        setQuestions(result);
        break;
      }
      case QuizTypes.AUDIO_SUBJECTIVE: {
        const questions = (data as AudioSubjectiveDraftData).questions;
        const result = questions.map(prev => ( {...prev, audioStatus: AudioUploadStatus.UPLOADED} ));
        setQuestions(result);
        break;
      }
      case QuizTypes.BINARY_CHOICE:
        setQuestions((data as ImageBinaryDraftData).questions);
        break;
    }

    const draftId = data.draftId;
    const title = data.title;
    const description = data.description
    const quizType = getQuizTypeFrom(data.quizType);

    setMetadata({title: title, description: description, formerDraftId: draftId});
    setQuizType(quizType);
    setDraftModal(false);
  }
  return (
    <>
      <div className={classes.contentItem} onClick={loadDraftItem}>
        <span className={classes.itemTitle}>{draft.title}</span>
        <div className={classes.dateAndDeleteButton}>
          <span className={classes.dateInfo}>{formatDateTime(draft.createdAt)}</span>
          <div className={`${classes.deleteButton} common-button`}>
            <img src={"/img/icon/trashcan.svg"} alt={"item delete button"}/>
          </div>
        </div>
      </div>
      <div className={classes.contentItemDivider}></div>
    </>
  );
}

export default QuizDraftModalContentItem;