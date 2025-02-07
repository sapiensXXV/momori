import {useEffect, useState} from "react";
import { useParams } from "react-router-dom";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleError} from "../../../global/error/error.ts";
import QuizIntroductionPage from "./QuizIntroductionPage.tsx";
import QuestionPage from "./QuestionPage.tsx";
import QuestionResultPage from "./QuestionResultPage.tsx";
import QuizResultPage from "./QuizResultPage.tsx";
import {initQuizDetail, QuizDetail} from "../../../types/quiz.ts";
import {DetailQuestion} from "../../../types/question.ts";
import {getRandomValues} from "node:crypto";
import {getRandomElements} from "../../../global/util/random.ts";

enum QuizPageType {
  INTRODUCTION = "introduction",
  QUESTION = "question",
  QUESTION_RESULT = "question_result",
  RESULT = "result",
}

const QuizPage = () => {

  const [pageType, setPageType] = useState<QuizPageType>(QuizPageType.INTRODUCTION);
  const [quiz, setQuiz] = useState<QuizDetail>(initQuizDetail);
  const [chosenQuestions, setChosenQuestions] = useState<DetailQuestion[]>([]);

  const {quizId} = useParams();

  useEffect(() => {
    // console.log(`/api/quiz/${quizId}`);
    axiosJwtInstance.get(`/api/quiz/${quizId}`)
      .then((response) => {
        console.log(response.data);
        setQuiz(response.data);
      })
      .catch((error) => {
        console.log(error);
        handleError(error);
      })
  }, []);

  const setQuestionCount = (value: number) => {
    // TODO: 문제 중 랜덤하게 value 개를 뽑아 사용자에게 제공할 문제로 선정
    console.log(`set question count: ${value}`)

    // 1. value개의 문제를 랜덤하게 선정 (chosenQuestions)
    const randomQuestions = getRandomElements(quiz.questions, value);
    console.log(randomQuestions)
    setChosenQuestions(randomQuestions);
    // 2. pageType을 QUESTION으로 전환
    setPageType(QuizPageType.QUESTION);

  }

  const selectComponent = () => {
    switch (pageType) {
      case QuizPageType.INTRODUCTION:
        return <QuizIntroductionPage
          thumbnailUrl={quiz.thumbnailUrl}
          title={quiz.title}
          description={quiz.description}
          questionCount={quiz.questions.length}
          setQuestionCount={setQuestionCount}
        />;
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