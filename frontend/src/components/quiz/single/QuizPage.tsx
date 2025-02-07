import {useEffect, useState} from "react";
import { useParams } from "react-router-dom";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleError} from "../../../global/error/error.ts";
import QuizIntroductionPage from "./QuizIntroductionPage.tsx";
import QuestionPage from "./QuestionPage.tsx";
import QuestionResultPage from "./QuestionResultPage.tsx";
import QuizResultPage from "./QuizResultPage.tsx";

enum QuizPageType {
  INTRODUCTION = "introduction",
  QUESTION = "question",
  QUESTION_RESULT = "question_result",
  RESULT = "result",
}

const QuizPage = () => {

  const [pageType, setPageType] = useState<QuizPageType>(QuizPageType.INTRODUCTION);

  const {quizId} = useParams();

  useEffect(() => {
    // console.log(`/api/quiz/${quizId}`);
    axiosJwtInstance.get(`/api/quiz/${quizId}`)
      .then((response) => {
        console.log(response.data)
      })
      .catch((error) => {
        console.log(error);
        handleError(error);
      })
  }, []);

  const selectComponent = () => {
    switch (pageType) {
      case QuizPageType.INTRODUCTION:
        return <QuizIntroductionPage/>;
      case QuizPageType.QUESTION:
        return <QuestionPage/>;
      case QuizPageType.QUESTION_RESULT:
        return <QuestionResultPage/>;
      case QuizPageType.RESULT:
        return <QuizResultPage/>;
    }
  }

  return (
    <>
      { selectComponent() }
    </>
  )
}

export default QuizPage;