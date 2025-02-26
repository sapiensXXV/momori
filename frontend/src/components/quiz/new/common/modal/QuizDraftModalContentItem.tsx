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

  const { setHasDraft, setMetadata, setQuizType, setQuestions, setDraftModal } = useQuizContext();

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

  const isUrlExists = (url: string) => {
    if (url !== null || url !== undefined || url !== '') {
      return true;
    }
    return false;
  }

  const isEmpty = (id: string) => {
    if (id !== null && id !== undefined && id !== '') {
      return true;
    }
    return false;
  }

  const loadDraftItem = async () => {

    const response = await axiosJwtInstance.get(
      `${loadDraftApi(draft.quizType)}?draftId=${draft.draftId}`
      );

    const data: BaseDraft = response.data as BaseDraft;

    // 타입별 퀴즈 데이터 업데이트
    switch (getQuizTypeFrom(data.quizType)) {
      case QuizTypes.IMAGE_MCQ: {
        const questions = (data as ImageMcqDraftData).questions;
        const result = questions.map(prev => ({
          ...prev,
          imageStatus: isUrlExists(prev.imageUrl) ? ImageUploadStatus.UPLOADED : ImageUploadStatus.NOT_UPLOADED
        }));
        setQuestions(result);
        break;
      }
      case QuizTypes.IMAGE_SUBJECTIVE: {
        const questions = (data as ImageSubjectiveDraftData).questions;
        const result = questions.map(prev => ({
          ...prev,
          imageStatus: isUrlExists(prev.imageUrl) ? ImageUploadStatus.UPLOADED : ImageUploadStatus.NOT_UPLOADED
        }));
        setQuestions(result);
        break;
      }
      case QuizTypes.AUDIO_MCQ: {
        const questions = (data as AudioMcqDraftData).questions;
        const result = questions.map(prev => ({
          ...prev,
          audioStatus: isEmpty(prev.audioId) ? AudioUploadStatus.NOT_UPLOADED : AudioUploadStatus.UPLOADED
        }))
        setQuestions(result);
        break;
      }
      case QuizTypes.AUDIO_SUBJECTIVE: {
        const questions = (data as AudioSubjectiveDraftData).questions;
        const result = questions.map(prev => ({
          ...prev,
          audioStatus: isEmpty(prev.audioId) ? AudioUploadStatus.NOT_UPLOADED : AudioUploadStatus.UPLOADED
        }))
        setQuestions(result);
        break;
      }
      case QuizTypes.BINARY_CHOICE:
        setQuestions((data as ImageBinaryDraftData).questions);
        break;
    }

    // 메타데이터 업데이트
    const draftId = data.draftId;
    const title = data.title;
    const thumbnailUrl = data.thumbnailUrl;
    const description = data.description
    const quizType = getQuizTypeFrom(data.quizType);

    console.log({
      title: title,
      thumbnailUrl: thumbnailUrl,
      thumbnailImageUploadStatus: isUrlExists(data.thumbnailUrl) ? ImageUploadStatus.UPLOADED : ImageUploadStatus.NOT_UPLOADED,
      description: description,
      formerDraftId: draftId
    });

    setMetadata({
      title: title,
      thumbnailUrl: thumbnailUrl,
      thumbnailImageUploadStatus: isUrlExists(data.thumbnailUrl) ? ImageUploadStatus.UPLOADED : ImageUploadStatus.NOT_UPLOADED,
      description: description,
      formerDraftId: draftId
    });

    setQuizType(quizType);
    setDraftModal(false);
    setHasDraft(true); // 임시 데이터 로딩 완료
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