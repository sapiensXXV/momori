import classes from './QuizResultPage.module.css'
import {QuizAttemptRecord} from "./QuizPage.tsx";
import {FC} from "react";

type QuizResultPageProps = {
  record: QuizAttemptRecord;
}

const QuizResultPage: FC<QuizResultPageProps> = ({record}) => {

  const calculateScore = () => {
    const correct: number = record.questions
      .filter((question) => question.isCorrect).length;
    return Math.floor(correct / record.questions.length * 100);
  }

  return (
    <>
      <main className={classes.resultContainer}>
        <div className={classes.resultContentContainer}>
          <span className={classes.resultTitle}>퀴즈 결과</span>
          <div className={classes.resultScore}>{calculateScore()}점</div>
        </div>
      </main>
    </>
  )
}

export default QuizResultPage;