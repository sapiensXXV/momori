import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleError} from "../../../global/error/error.ts";
import QuizIntroductionPage from "./QuizIntroductionPage.tsx";
import QuestionResultPage from "./QuestionResultPage.tsx";
import QuizResultPage from "./QuizResultPage.tsx";
import {initQuizDetail, QuizDetail} from "../../../types/quiz.ts";
import {DetailQuestion, ImageMcqDetailQuestion} from "../../../types/question.ts";
import {getRandomElements} from "../../../global/util/random.ts";
import {QuizTypes} from "../types/Quiz.types.ts";
import ImageMcqQuestionPage from "./question/ImageMcqQuestionPage.tsx";
import ImageSubjectiveQuestionPage from "./question/ImageSubjectiveQuestionPage.tsx";
import AudioMcqQuestionPage from "./question/AudioMcqQuestionPage.tsx";
import AudioSubjectiveQuestionPage from "./question/AudioSubjectiveQuestionPage.tsx";
import ImageBinaryQuestionPage from "./question/ImageBinaryQuestionPage.tsx";

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
  const [current, setCurrent] = useState(0); // 현재 퀴즈 번호

  const {quizId} = useParams();

  useEffect(() => {
    axiosJwtInstance.get(`/api/quiz/${quizId}`)
      .then((response) => {
        // console.log(response.data);
        setQuiz(response.data);
      })
      .catch((error) => {
        // console.log(error);
        handleError(error);
      })
  }, []);

  const setQuestionCount = (value: number) => {
    // TODO: 문제 중 랜덤하게 value 개를 뽑아 사용자에게 제공할 문제로 선정
    console.log(`set question count: ${value}`)

    // 1. value개의 문제를 랜덤하게 선정 (chosenQuestions)
    const randomQuestions = getRandomElements(quiz.questions, value);
    // console.log(randomQuestions)
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
        return selectQuestionComponent();
      case QuizPageType.QUESTION_RESULT:
        return <QuestionResultPage/>;
      case QuizPageType.RESULT:
        return <QuizResultPage/>;
    }
  }

  // 선택지 선택시 호출되는 메서드
  const selectQuestionComponent = () => {
    // 현재 퀴즈의 타입에 따라 다른 문제 페이지 반환
    switch (quiz.type) {
      case QuizTypes.IMAGE_MCQ:
        return <ImageMcqQuestionPage
          question={chosenQuestions[current] as ImageMcqDetailQuestion}
        />
      case QuizTypes.IMAGE_SUBJECTIVE:
        return <ImageSubjectiveQuestionPage/>
      case QuizTypes.AUDIO_MCQ:
        return <AudioMcqQuestionPage/>
      case QuizTypes.AUDIO_SUBJECTIVE:
        return <AudioSubjectiveQuestionPage/>
      case QuizTypes.BINARY_CHOICE:
        return <ImageBinaryQuestionPage/>
    }
  }

  return (
    <>
      { selectComponent() }
    </>
  )
}

export default QuizPage;