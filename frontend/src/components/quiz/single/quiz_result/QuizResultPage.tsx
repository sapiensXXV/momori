import classes from './QuizResultPage.module.css'
import {McqQuizAttemptRecord, SubQuizAttemptRecord} from "../QuizPage.tsx";
import {FC, useEffect, useRef} from "react";
import {Chart, ChartConfiguration, Colors, registerables} from "chart.js";
import {calculatePercentile} from "../../../../global/util/percent.tsx";
import {axiosJwtInstance} from "../../../../global/configuration/axios.ts";
import {handleError} from "../../../../global/error/error.ts";
import {useNavigate} from "react-router-dom";
import quizResult from "../../../../global/api/quizResult.ts";
import quizResultApiMap from "../../../../global/api/quizResult.ts";

type QuizResultPageProps = {
  quizId: string | undefined;
  record: McqQuizAttemptRecord | SubQuizAttemptRecord;
  distribution: number[];
}

const QuizResultPage: FC<QuizResultPageProps> = ({quizId, record, distribution}) => {

  const chartRef = useRef<HTMLCanvasElement | null>(null);
  const chartInstance = useRef<Chart | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    console.log(record);
    // 차트 생성
    if (!chartRef.current) return;
    const ctx = chartRef.current.getContext("2d")
    if (!ctx) return;

    Chart.register(...registerables);
    Chart.register(Colors)

    const config: ChartConfiguration<"bar", number[], string> = {
      data: {
        labels: ["0~9", "10~19", "20~29", "30~39", "40~49", "50~59", "60~69", "70~79", "80~89", "90~100"],
        datasets: [
          {
            type: "line",
            label: '점수 분포 라인',
            data: distribution,
            borderColor: 'rgba(54, 162, 235, 1)',
            backgroundColor: 'rgba(54, 162, 235, 1)',
            fill: false,
            tension: 0.4,
            order: 1,
          },
          {
            type: "bar",
            label: "점수 분포 막대",
            data: distribution,
            backgroundColor: [
              "rgba(255, 159, 64, 0.6)",
            ],
            borderColor: [
              "rgba(255, 159, 64, 1)",
            ],
            borderWidth: 2,
            order: 2
          },
        ],
      },
      options: {
        responsive: true,
        layout: {
          padding: 0,
        },
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    }

    if (chartInstance.current) {
      chartInstance.current.destroy();
    }
    chartInstance.current = new Chart(ctx, config);
    console.log(record);
    // 퀴즈 결과 데이터 서버로 전달
    axiosJwtInstance.post(
      quizResultApiMap[record.type],
      {
        quizId: quizId,
        score: calculateScore(),
        questions: record.questions,
        type: record.type
      }
    )
      .then(() => {
        console.log('통계 등록')
      })
      .catch((error) => {
        handleError(error);
      })


    // 컴포넌트 언마운트 시 차트 인스턴스 정리
    return () => {
      if (chartInstance.current) {
        chartInstance.current.destroy();
      }
      record.questions = []; // 배열을 초기화 해주지 않으면 페이지에 재 접속했을 때 이전 기록이 계속 남아있음.
    };
  }, [])

  const calculateScore = () => {
    const correct: number = record.questions
      .filter((question) => question.isCorrect).length;
    return Math.floor(correct / record.questions.length * 100);
  }

  const refreshPage = () => {
    window.location.reload();
  }

  return (
    <>
      <main className={classes.resultContainer}>
        <div className={classes.resultContentContainer}>
          <span className={classes.resultTitle}>퀴즈 결과</span>
          <div className={classes.resultScore}>{calculateScore()}점
            (상위 {calculatePercentile(calculateScore(), distribution)}%)
          </div>
          <canvas className={classes.chart} ref={chartRef}></canvas>
          <div className={classes.afterButtonContainer}>
            <div className={`common-button ${classes.afterButton}`} onClick={() => navigate('/')}>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"/>
              </svg>
              <span>홈으로</span>
            </div>
            <div className={`common-button ${classes.afterButton}`} onClick={refreshPage}>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99"/>
              </svg>
              <span>재도전</span>
            </div>
          </div>
        </div>
      </main>
    </>
  )
}

export default QuizResultPage;