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
import ImageMcqQuestionResultPage from "./question_result/ImageMcqQuestionResultPage.tsx";
import ImageSubjectiveQuestionResultPage from "./question_result/ImageSubjectiveQuestionResultPage.tsx";
import AudioMcqQuestionResultPage from "./question_result/AudioMcqQuestionResultPage.tsx";
import AudioSubjectiveQuestionResultPage from "./question_result/AudioSubjectiveQuestionResultPage.tsx";
import ImageBinaryQuestionResultPage from "./question_result/ImageBinaryQuestionResultPage.tsx";

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
        return selectQuestionResultComponent();
      case QuizPageType.RESULT:
        return <QuizResultPage/>;
    }
  }

  const afterSubmit = (isSelectAnswer: boolean) => {
    console.log(isSelectAnswer);
    // 어떠한 상태이든 문제 결과 화면으로 넘어가야함.
    setPageType(QuizPageType.QUESTION_RESULT);
    // 객관식, 주관식, 이미지, 오디오 각각 결결과화면도 다르게 보여주어야하기 때문에 다른 컴포넌트의 정의가 필요하다.

    if (isSelectAnswer) {
      // TODO: 정답 선택 시 로직
    } else {
      // TODO: 오답 선택 시 로직
    }
  }

  // 선택지 선택시 호출되는 메서드
  const selectQuestionComponent = () => {
    // 현재 퀴즈의 타입에 따라 다른 문제 페이지 반환
    switch (quiz.type) {
      case QuizTypes.IMAGE_MCQ:
        return <ImageMcqQuestionPage
          question={chosenQuestions[current] as ImageMcqDetailQuestion}
          afterSubmit={afterSubmit}
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

  // 문제 결과 컴포넌트를 선택하는 함수
  const selectQuestionResultComponent = () => {
    switch (quiz.type) {
      case QuizTypes.IMAGE_MCQ:
        return <ImageMcqQuestionResultPage question={chosenQuestions[current] as ImageMcqDetailQuestion} />
      case QuizTypes.IMAGE_SUBJECTIVE:
        return <ImageSubjectiveQuestionResultPage/>
      case QuizTypes.AUDIO_MCQ:
        return <AudioMcqQuestionResultPage/>
      case QuizTypes.AUDIO_SUBJECTIVE:
        return <AudioSubjectiveQuestionResultPage/>
      case QuizTypes.BINARY_CHOICE:
        return <ImageBinaryQuestionResultPage/>
    }
  }

  return (
    <>
      { selectComponent() }
    </>
  )
}

export default QuizPage;