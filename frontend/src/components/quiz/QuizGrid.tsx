import styles from "./QuizGrid.module.css"
import QuizItem from "./QuizItem.tsx";
import {useEffect} from "react";
import {SimpleQuizItem} from "../../types/quiz.ts";

export default function QuizGrid() {

  useEffect(() => {

  }, [])

  const sampleItem: SimpleQuizItem = {
    id: "123",
    thumbnailUrl: "",
    title: "퀴즈 제목",
    description: "퀴즈 설명, 퀴즈 설명, 퀴즈 설명, 퀴즈 설명, 퀴즈 설명, 퀴즈 설명",
  }

  return (
    <section className={styles.gridContainer}>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
      <QuizItem item={sampleItem}/>
    </section>
  )
}