import {useEffect, useRef, useState} from "react";
import {useParams} from "react-router-dom";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleErrorWithCustomAlert} from "../../../global/error/error.ts";
import QuizIntroductionPage from "./QuizIntroductionPage.tsx";
import QuizResultPage from "./quiz_result/QuizResultPage.tsx";
import {initQuizDetail, QuizDetail} from "../../../types/quiz.ts";
import {
  AudioMcqDetailQuestion, AudioSubDetailQuestion,
  DetailQuestion,
  ImageMcqDetailQuestion, ImageSubjectiveDetailQuestion,
} from "../../../types/question.ts";
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
import classes from './QuizPage.module.css';
import {useAlertManager} from "../../alert/useAlertManager.hook.tsx";

enum QuizPageType {
  INTRODUCTION = "introduction",
  QUESTION = "question",
  QUESTION_RESULT = "question_result",
  RESULT = "result",
}

export type McqQuizAttemptRecord = {
  quizId: string;
  type: QuizTypes;
  questions: {
    questionId: string;
    isCorrect: boolean;
    choices: number[]; // 해당 문제에서 선택한 선택지 번호
  }[];
}

export type SubQuizAttemptRecord = {
  quizId: string;
  type: QuizTypes;
  questions: {
    questionId: string;
    isCorrect: boolean;
    userInput: string; // 사용자가 입력한 정답.
  }[];
}

const mcqInitAttemptRecord: McqQuizAttemptRecord = {
  quizId: "quiz_id",
  type: QuizTypes.IMAGE_MCQ,
  questions: []
}

const subInitAttemptRecord: SubQuizAttemptRecord = {
  quizId: "quiz_id",
  type: QuizTypes.IMAGE_MCQ,
  questions: []
}

const QuizPage = () => {

  const [pageType, setPageType] = useState<QuizPageType>(QuizPageType.INTRODUCTION);
  const [quiz, setQuiz] = useState<QuizDetail>(initQuizDetail);
  const [chosenQuestions, setChosenQuestions] = useState<DetailQuestion[]>([]);
  const [current, setCurrent] = useState(0); // 현재 퀴즈 번호
  const [correct, setCorrect] = useState<boolean>(false)
  const [userSelect, setUserSelect] = useState<number>(0); // 사용자가 선택한 객관식 답안 번호
  const [_, setUserInput] = useState<string>(""); // 사용자가 입력한 주관식 답안

  const mcqRecord = useRef<McqQuizAttemptRecord>(mcqInitAttemptRecord); // 객관식 퀴즈가 끝난 후 결과를 표시하고 통계용으로 서버에 전달하는 데 사용
  const subRecord = useRef<SubQuizAttemptRecord>(subInitAttemptRecord); // 주관식 퀴즈가 끝난 후 결과를 표시하고 통계용으로 서버에 전달하는 데 사용

  const {quizId} = useParams();

  const { showAlert, AlertContainer } = useAlertManager();

  useEffect(() => {
    axiosJwtInstance.get(`/api/quiz/${quizId}`)
      .then((response) => {
        setQuiz(response.data);
        mcqRecord.current.quizId = response.data.id; // 퀴즈 ID 저장
        mcqRecord.current.type = response.data.type
        subRecord.current.quizId = response.data.id; // 퀴즈 ID 저장
        subRecord.current.type = response.data.type
      })
      .catch((error) => {
        handleErrorWithCustomAlert(error, showAlert);
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

  const getQuizRecord = () => {
    if (quiz.type === QuizTypes.IMAGE_MCQ || quiz.type === QuizTypes.AUDIO_MCQ) {
      return mcqRecord.current;
    } else if (quiz.type === QuizTypes.AUDIO_SUBJECTIVE || quiz.type === QuizTypes.IMAGE_SUBJECTIVE) {
      return subRecord.current;
    }
    // TODO: 이지선다 퀴즈기록 반환 (이지선다 퀴즈 용 새로운 useRef 를 정의해야함.)
    return mcqRecord.current;
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
        return <QuizResultPage quizId={quizId} record={getQuizRecord()} distribution={quiz.scoreDistribution} />;
    }
  }

  const submitMcqQuestion = (isSelectAnswer: boolean, selectedIndex: number) => {
    // userSelect, correct 변수는 사용자가 현재 푼 문제에 대한 정보이다.
    // userSelect 는 사용자가 선택한 선택지의 번호, correct 는 정답(true), 오답(false) 여부이다.
    setUserSelect(selectedIndex);
    // 객관식, 주관식, 이미지, 오디오 각각 결과화면도 다르게 보여주어야하기 때문에 다른 컴포넌트의 정의가 필요하다.
    if (isSelectAnswer) {
      // 사용자가 정답을 맞추었을 때
      setCorrect(true);
      mcqRecord.current.questions.push({
          questionId: chosenQuestions[current].questionId,
          isCorrect: true,
          choices: [selectedIndex] // 미래에 복수정답 퀴즈가 나올 수 있어 배열로 데이터를 전달한다.
        })
    } else {
      // 사용자가 오답을 선택했을 때
      setCorrect(false);
      mcqRecord.current.questions.push({
        questionId: chosenQuestions[current].questionId,
        isCorrect: false,
        choices: [selectedIndex]
      })
    }
    // 어떠한 상태이든 문제 결과 화면으로 넘어가야함.
    setPageType(QuizPageType.QUESTION_RESULT);
  }

  // 주관식 문제를 제출하고, 사용자의 입력과 결과를 받아오는 메서드
  // - 문제 결과 페이지에서 표시될 상태 데이터를 설정한다.
  // - 퀴즈 결과 페이지에서 사용될 기록(record)를 기록한다.
  const submitSubQuestion = (isCorrect: boolean, userInput: string) => {
    setPageType(QuizPageType.QUESTION_RESULT);
    setUserInput(userInput);
    if (isCorrect) {
      // 정답을 맞추었을 경우
      setCorrect(true);
      subRecord.current.questions.push({
        questionId: chosenQuestions[current].questionId,
        isCorrect: true,
        userInput: userInput
      })
    } else {
      // 오답일 경우
      setCorrect(false);
      subRecord.current.questions.push({
        questionId: chosenQuestions[current].questionId,
        isCorrect: false,
        userInput: userInput
      })
    }
  }

  // 문제 결과 페이지에서 '다음으로' 버튼을 눌렀을 때 호출되는 메서드
  const nextQuestion = () => {
    // 마지막 문제일 때를 고려
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
          afterSubmit={submitMcqQuestion}
        />
      case QuizTypes.IMAGE_SUBJECTIVE:
        return <ImageSubjectiveQuestionPage
          question={chosenQuestions[current] as ImageSubjectiveDetailQuestion}
          afterSubmit={submitSubQuestion}
        />
      case QuizTypes.AUDIO_MCQ:
        return <AudioMcqQuestionPage
          question={chosenQuestions[current] as AudioMcqDetailQuestion}
          afterSubmit={submitMcqQuestion}
        />
      case QuizTypes.AUDIO_SUBJECTIVE:
        return <AudioSubjectiveQuestionPage
          question={chosenQuestions[current] as AudioSubDetailQuestion}
          afterSubmit={submitSubQuestion}
        />
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
        return <ImageSubjectiveQuestionResultPage
          isCorrect={correct}
          question={chosenQuestions[current] as ImageSubjectiveDetailQuestion}
          nextQuestion={nextQuestion}
        />
      case QuizTypes.AUDIO_MCQ:
        return <AudioMcqQuestionResultPage
          isCorrect={correct}
          question={chosenQuestions[current] as AudioMcqDetailQuestion}
          nextQuestion={nextQuestion}
          userSelect={userSelect}
        />
      case QuizTypes.AUDIO_SUBJECTIVE:
        return <AudioSubjectiveQuestionResultPage
          isCorrect={correct}
          question={chosenQuestions[current] as AudioSubDetailQuestion}
          nextQuestion={nextQuestion}
        />
      case QuizTypes.BINARY_CHOICE:
        return <ImageBinaryQuestionResultPage/>
    }
  }

  return (
    <>
      <AlertContainer/>
      <div className={classes.quizPageMain}>
        { selectComponent() }
        <Comments quizId={quizId}/>
      </div>
    </>
  )
}

export default QuizPage;