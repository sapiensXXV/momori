import styles from "./QuizGrid.module.css"
import QuizItem from "./QuizItem.tsx";
import {useEffect, useState} from "react";
import {SimpleQuizItem} from "../../types/quiz.ts";
import {axiosJwtInstance} from "../../global/configuration/axios.ts";
import {handleError} from "../../global/error/error.ts";

enum SearchType {
  LATEST="latest",
  POPULAR="popular"
}

type SearchCondition = {
  page: number;
  size: number;
  type: SearchType;
}

export default function QuizGrid() {

  const [quizList, setQuizList] = useState<SimpleQuizItem[]>([]);
  const [searchCondition, setSearchCondition] = useState<SearchCondition>({ page: 0, size: 20, type: SearchType.POPULAR })

  useEffect(() => {
    requestSimpleQuiz();
  }, [])

  const requestSimpleQuiz = async () => {
    try {
      const response = await axiosJwtInstance.get(`/api/quiz/list?page=${searchCondition.page}&size=${searchCondition.size}&type=${searchCondition.type}`)
      // console.log(response.data);
      setQuizList(response.data)
    } catch (error) {
      handleError(error);
    }
  }

  return (
    <section className={styles.gridContainer}>
      {
        quizList.map((quiz, index) => {
          return <QuizItem key={`quiz_number_${index}`} item={quiz}/>
        })
      }
    </section>
  )
}