import classes from './QuizCreateButton.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {handleError} from "../../../../global/error/error.ts";
import {QuizTypes} from "../../types/Quiz.types.ts";
import {useNavigate} from "react-router-dom";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";

const QuizCreateButton = () => {

  const { quizType, metadata, questions } = useQuizContext();
  const navigate = useNavigate();

  const createQuiz = async () => {
    const request = makeQuizCreateRequest();
    try {
      const response = await axiosJwtInstance.post(
        getQuizCreateApi(),
        request
      )
      console.log(`퀴즈 생성 완료! location:${response.headers['Location']}`)
      navigate('/') // 성공 시 홈화면으로 이동
    } catch (error) {
      handleError(error);
    }
  }

  const getQuizCreateApi = () => {
    switch (quizType) {
      case QuizTypes.IMAGE_MCQ:
        return "/api/quiz/mcq-img"
      case QuizTypes.IMAGE_SUBJECTIVE:
        return "/api/quiz/sub-img"
      case QuizTypes.AUDIO_MCQ:
        return "/api/quiz/mcq-audio"
      case QuizTypes.AUDIO_SUBJECTIVE:
        return "/api/quiz/sub-audio"
      case QuizTypes.BINARY_CHOICE:
        return "/api/quiz/bin-img"
    }
  }

  const makeQuizCreateRequest = () => {
    return {
      title: metadata.title,
      description: metadata.description,
      draftId: metadata.formerDraftId,
      type: quizType,
      thumbnailUrl: metadata.thumbnailUrl,
      questions: questions
    }
  }

  return (
    <>
      <button className={`common-button ${classes.quizCreateButton}`} onClick={createQuiz}>
        등록
      </button>
    </>
  );
}

export default QuizCreateButton;