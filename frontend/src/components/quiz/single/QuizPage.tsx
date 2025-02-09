import {useEffect, useRef, useState} from "react";
import {useParams} from "react-router-dom";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleError} from "../../../global/error/error.ts";
import QuizIntroductionPage from "./QuizIntroductionPage.tsx";
import QuizResultPage from "./quiz_result/QuizResultPage.tsx";
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
import Comments from "./comment/Comments.tsx";

enum QuizPageType {
  INTRODUCTION = "introduction",
  QUESTION = "question",
  QUESTION_RESULT = "question_result",
  RESULT = "result",
}

export type QuizAttemptRecord = {
  quizId: string;
  questions: {
    questionId: string;
    isCorrect: boolean;
  }[];
}

const initAttemptRecord: QuizAttemptRecord = {
  quizId: "quiz_id",
  questions: []
}

const QuizPage = () => {

  const [pageType, setPageType] = useState<QuizPageType>(QuizPageType.INTRODUCTION);
  const [quiz, setQuiz] = useState<QuizDetail>(initQuizDetail);
  const [chosenQuestions, setChosenQuestions] = useState<DetailQuestion[]>([]);
  const [current, setCurrent] = useState(0); // 현재 퀴즈 번호
  const [correct, setCorrect] = useState<boolean>(false)
  const [userSelect, setUserSelect] = useState<number>(0);
  const record = useRef<QuizAttemptRecord>(initAttemptRecord); // 퀴즈가 끝난후 서버에 전달. 통계용으로 사용

  const {quizId} = useParams();

  useEffect(() => {
    axiosJwtInstance.get(`/api/quiz/${quizId}`)
      .then((response) => {
        setQuiz(response.data);
        record.current.quizId = response.data.id; // 퀴즈 ID 저장
      })
      .catch((error) => {
        // console.log(error);
        handleError(error);
      })
  }, [quizId]);

  const setQuestionCount = (value: number) => {
    // 1. value 개의 문제를 랜덤하게 선정 (chosenQuestions)
    const randomQuestions = getRandomElements(quiz.questions, value);
    // console.log(randomQuestions)
    setChosenQuestions(randomQuestions);
    // 2. pageType 을 QUESTION 으로 전환
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
        // return <QuizResultPage record={record.current} distribution={quiz.scoreDistribution} />;
        return <QuizResultPage record={record.current} distribution={[10, 23, 44, 56, 102, 177, 150, 100, 30, 10]} />;
    }
  }

  const submitQuestion = (isSelectAnswer: boolean, userSelect: number) => {
    // 어떠한 상태이든 문제 결과 화면으로 넘어가야함.
    setPageType(QuizPageType.QUESTION_RESULT);
    setUserSelect(userSelect);
    // 객관식, 주관식, 이미지, 오디오 각각 결결과화면도 다르게 보여주어야하기 때문에 다른 컴포넌트의 정의가 필요하다.
    if (isSelectAnswer) {
      // TODO: 정답 선택 시 로직
      setCorrect(true);
      record.current.questions.push({ questionId: chosenQuestions[current].questionId, isCorrect: true })
    } else {
      // TODO: 오답 선택 시 로직
      setCorrect(false);
      record.current.questions.push({ questionId: chosenQuestions[current].questionId, isCorrect: false })
    }
  }

  // 문제 결과 페이지에서 '다음으로' 버튼을 눌렀을 때 호출되는 메서드
  const nextQuestion = () => {
    // TODO: 마지막 문제일 때를 고려한 로직 필요
    if (chosenQuestions.length <= current+1) {
      setPageType(QuizPageType.RESULT); // 마지막 문제를 해결한 경우 결과 페이지로 이동
      return;
    }

    setPageType(QuizPageType.QUESTION);
    setCurrent((prev) => prev + 1);
  }

  // 선택지 선택시 호출되는 메서드
  const selectQuestionComponent = () => {
    // 현재 퀴즈의 타입에 따라 다른 문제 페이지 반환
    switch (quiz.type) {
      case QuizTypes.IMAGE_MCQ:
        return <ImageMcqQuestionPage
          question={chosenQuestions[current] as ImageMcqDetailQuestion}
          afterSubmit={submitQuestion}
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
        return <ImageMcqQuestionResultPage
          isCorrect={correct}
          question={chosenQuestions[current] as ImageMcqDetailQuestion}
          nextQuestion={nextQuestion}
          userSelect={userSelect}
        />
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
      <Comments quizId={quiz.id}/>
    </>
  )
}

export default QuizPage;