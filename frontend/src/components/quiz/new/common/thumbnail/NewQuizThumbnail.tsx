import classes from './NewQuizThumbnail.module.css'
import {ImageUploadStatus} from "../../../../../types/question.ts";
import React from "react";
import {useQuizContext} from "../../../../../context/QuizContext.tsx";
import {compressImage} from "../../../../../global/util/image/ImageCompress.tsx";
import {axiosJwtInstance} from "../../../../../global/configuration/axios.ts";
import {ImageUrlResponse} from "../../../types/ImageUrlResponse.ts";
import {handleError} from "../../../../../global/error/error.ts";
import {useAlertManager} from "../../../../alert/useAlertManager.hook.tsx";

const NewQuizThumbnail = () => {

  const {metadata, setMetadata} = useQuizContext()
  const {showAlert, AlertContainer} = useAlertManager();

  const thumbnailUploader = async (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.value) return;
    if (!e.target.files || e.target.files.length === 0) {
      showAlert('썸네일이 선택되지 않았습니다.');
      return;
    }

    setMetadata(prev => { return {...prev, thumbnailImageUploadStatus: ImageUploadStatus.PENDING} })
    try {
      const compressedImage = await compressImage(e.target.files[0]);
      const formData = new FormData();
      formData.append("image", compressedImage);
      formData.append("prevImageUrl", metadata.thumbnailUrl);

      const response = await axiosJwtInstance.post<{ imageUrl: string; }>(
        '/api/quiz/draft/image',
        formData,
        {headers: {'Content-Type': 'multipart/form-data'}}
      );


      const data: ImageUrlResponse = response.data;
      setMetadata(prev => {
        return {...prev, thumbnailUrl: data.imageUrl, thumbnailImageUploadStatus: ImageUploadStatus.UPLOADED }
      })
    } catch (error) {
      handleError(error);
      setMetadata(prev => { return {...prev, thumbnailImageUploadStatus: ImageUploadStatus.NOT_UPLOADED} })
    }
  }

  const getImage = () => {
    if (metadata.thumbnailUrl === null) return;
    switch (metadata.thumbnailImageUploadStatus) {
      case ImageUploadStatus.NOT_UPLOADED: {

        return (
          <div className={classes.thumbnailAddContainer}>
            <img src={"/img/icon/add.svg"} className={classes.imageAddIcon}></img>
            <span className={classes.addDescription}>썸네일 추가</span>
          </div>
        )
      }
      case ImageUploadStatus.PENDING:
        return <img className={classes.imageAddIcon} src={"/img/icon/loading.gif"} alt={"pending animation"}></img>;
      case ImageUploadStatus.UPLOADED:
        return <img className={"link-shadow-image"} src={metadata.thumbnailUrl} alt={"uploaded image"}></img>;
    }
  }

  return (
    <>
      <AlertContainer/>
      <div className={classes.thumbnailContainer}>
        <input
          type={"file"}
          accept={"image/*"}
          onChange={(e) => thumbnailUploader(e)}
          id={`thumbnail_img`}
          className={classes.hiddenInput}
        />
        <label htmlFor={'thumbnail_img'} className={classes.thumbnailUploadBox}>
          { getImage() }
        </label>
      </div>
    </>
  )
}

export default NewQuizThumbnail;