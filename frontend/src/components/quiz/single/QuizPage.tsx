import {useEffect, useId} from "react";
import {useParams} from "react-router-dom";

const QuizPage = () => {

  const {quizId} = useParams();

  useEffect(() => {

  }, []);

  return (
    <>
      { `퀴즈 아이디=${quizId}` }
    </>
  )
}

export default QuizPage;