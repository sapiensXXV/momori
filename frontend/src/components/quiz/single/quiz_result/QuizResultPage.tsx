import classes from './QuizResultPage.module.css'
import {QuizAttemptRecord} from "../QuizPage.tsx";
import {FC, useEffect, useRef} from "react";
import {Chart, ChartConfiguration, Colors, registerables} from "chart.js";
import {calculatePercentile} from "../../../../global/util/percent.tsx";

type QuizResultPageProps = {
  record: QuizAttemptRecord;
  distribution: number[];
}

const QuizResultPage: FC<QuizResultPageProps> = ({record, distribution}) => {

  const chartRef = useRef<HTMLCanvasElement | null>(null);
  const chartInstance = useRef<Chart | null>(null);

  useEffect(() => {
    console.log('useEffect 호출');
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

    // 퀴즈 결과 데이터 서버로 전달

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

  return (
    <>
      <main className={classes.resultContainer}>
        <div className={classes.resultContentContainer}>
          <span className={classes.resultTitle}>퀴즈 결과</span>
          <div className={classes.resultScore}>{calculateScore()}점 (상위 {calculatePercentile(calculateScore(), distribution)}%)</div>

          <canvas className={classes.chart} ref={chartRef}></canvas>
        </div>
      </main>
    </>
  )
}

export default QuizResultPage;