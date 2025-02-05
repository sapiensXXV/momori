import classes from "./QuizItem.module.css"
import {SimpleQuizItem} from "../../types/quiz.ts";
import {FC} from "react";
import {useNavigate} from "react-router-dom";

type QuizItemProps = {
  item: SimpleQuizItem;
}

const QuizItem: FC<QuizItemProps> = ({ item }) => {

  const navigate = useNavigate();

  const quizItemClicked = () => {
    navigate(`/quiz/${item.id}`); // 퀴즈 페이지로 이동
  }
  
  const getThumbnailImage = () => {
    const thumbnail = item.thumbnailUrl;
    if (thumbnail !== null || thumbnail !== undefined || thumbnail !== "") {
      return <img className={classes.defaultThumbnailImage} src={"/img/icon/image.svg"}></img>
    } else  {
      return <img className={classes.thumbnailImage} src={item.thumbnailUrl}/>
    }
  }

  return (
    <section className={classes.item} onClick={() => quizItemClicked()}>
      <div className={classes.thumbnailContainer}>
        {/*<img className={classes.thumbnailImage} src={item.thumbnailUrl}/>*/}
        { getThumbnailImage() }
        <div className={classes.overlay}></div>
      </div>
      <div className={classes.textContainer}>
        <div className={classes.title}>{item.title}</div>
        <div className={classes.description}>{item.description}</div>
      </div>
    </section>
  )
}

export default QuizItem;