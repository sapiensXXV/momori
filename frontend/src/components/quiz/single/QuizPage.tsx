import { useEffect } from "react";
import { useParams } from "react-router-dom";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleError} from "../../../global/error/error.ts";
import axios from "axios";

const QuizPage = () => {

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

  return (
    <>
      { `퀴즈 아이디=${quizId}` }
    </>
  )
}

export default QuizPage;