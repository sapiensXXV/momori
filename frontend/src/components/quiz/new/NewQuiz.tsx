import styles from "./NewQuiz.module.css"
import {useState} from "react";
import {QuizTypes} from "../types/Quiz.types.ts";
import ImageMcqForm from "./image_mcq/ImageMcqForm.tsx";

export default function NewQuiz() {

  const [quizType, setQuizType] = useState<QuizTypes>(QuizTypes.IMAGE_MCQ);
  console.log(quizType);
  return (
    <section className={styles.main}>
      <ImageMcqForm />
    </section>
  )
}