import styles from "./QuizGrid.module.css"
import QuizItem from "./QuizItem.tsx";
import {useEffect} from "react";

export default function QuizGrid() {

  useEffect(() => {

  }, [])

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