import {FC} from "react";
import classes from './QuestionImage.module.css'
import {ImageUploadStatus} from "../../../types/question.ts";

type QuestionImageProps = {
  status: ImageUploadStatus;
  imageUrl: string | null;
}

const QuestionImage: FC<QuestionImageProps> = ({
  status,
  imageUrl
}) => {

  const getImage = () => {
    switch(status) {
      case ImageUploadStatus.NOT_UPLOADED:
        return <img className={classes.imageAddIcon} src={"/img/icon/add.svg"} alt={"default add image"} ></img>;
      case ImageUploadStatus.PENDING:
        return <img className={classes.imageAddIcon} src={"/img/icon/loading.gif"} alt={"pending animation"}></img>;
      case ImageUploadStatus.UPLOADED:
        if (imageUrl === null) return;
        return <img className={"link-shadow-image"} src={imageUrl} alt={"uploaded image"}></img>;
    }
  }
  return (
    <>
      { getImage() }
    </>
  )
}

export default QuestionImage;

