import styles from "./QuizGrid.module.css"
import QuizItem from "./QuizItem.tsx";

export default function QuizGrid() {
  return (
    <section className={styles.gridContainer}>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
      <QuizItem/>
    </section>
  )
}