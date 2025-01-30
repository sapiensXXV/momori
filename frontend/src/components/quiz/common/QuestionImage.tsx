import {FC} from "react";
import classes from './QuestionImage.module.css'

type QuestionImageProps = {
  status: "not_uploaded" | "pending" | "uploaded";
  imageUrl: string | null;
}

const QuestionImage: FC<QuestionImageProps> = ({
  status,
  imageUrl
}) => {

  const getImage = () => {
    switch(status) {
      case "not_uploaded":
        return <img className={classes.imageAddIcon} src={"/img/icon/add.svg"} alt={"default add image"} ></img>;
      case "pending":
        return <img className={classes.imageAddIcon} src={"/img/icon/loading.gif"} alt={"pending animation"}></img>;
      case "uploaded":
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

