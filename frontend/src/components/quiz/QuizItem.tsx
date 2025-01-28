import styles from "./QuizItem.module.css"

export default function QuizItem() {

  const quizItemClicked = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault();
    console.log("quiz item clicked");
  }

  return (
    <section className={styles.item} onClick={(e) => quizItemClicked(e)}>
      <div className={styles.thumbnailContainer}>
        <img className={styles.thumbnailImage} src="/img/icon/image.svg"/>
        <div className={styles.overlay}></div>
      </div>
      <div className={styles.textContainer}>
        <div className={styles.title}>제목입니다제목입니다제목입니다제목입니다제목입니다</div>
        <div className={styles.description}>설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다설명입니다</div>
      </div>
    </section>
  )
}