import classes from './McqQuestionResult.module.css'
import {AudioMcqDetailQuestion} from "../../../../types/question.ts";
import {FC, useEffect, useState} from "react";
import PercentageBar from "./PercentageBar.tsx";
import {percent} from "../../../../global/util/percent.tsx";
import YouTube from "react-youtube";


type AudioMcqQuestionResultPageProps = {
  isCorrect: boolean;
  question: AudioMcqDetailQuestion;
  nextQuestion: () => void; // 다음 문제를 호출하는 함수
  userSelect: number; // 사용자가 선택한 선택지 번호
}

const AudioMcqQuestionResultPage: FC<AudioMcqQuestionResultPageProps> = ({
  isCorrect,
  question,
  nextQuestion,
  userSelect
}) => {

  const [sumOfChoices, setSumOfChoices] = useState<number>(0);

  useEffect(() => {
    setSumOfChoices(calculateSelectSum);
  }, []);

  const calculateSelectSum = () => {
    let result = 0;
    question.choices.map((choice) => {
      result += choice.selectedCount;
    })
    return result;
  }

  return (
    <>
      <main className={classes.resultContainer}>
        <div className={classes.resultContentContainer}>
          <div className={classes.resultVideoPlayer}>
            <YouTube
              videoId={question.audioId}
              opts={{
                width: "100%",
                playerVars: {
                  autoplay: 0,
                  rel: 0,
                  start: question.startTime,
                },
                volume: 0.5
              }}
            />
          </div>
          <span className={classes.resultText}>{isCorrect ? "정답!" : "오답"}</span>
          <div className={`${classes.nextButton} common-button`} onClick={nextQuestion}>
            <span>다음으로</span>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3" stroke="currentColor" className="size-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="m8.25 4.5 7.5 7.5-7.5 7.5"/>
            </svg>
          </div>
          <div className={classes.choiceResultContainer}>
            {
              question.choices.map((choice, index) => {
                return (
                  <div
                    key={`question_${question.questionId}_choice_${index}`}
                    className={`
                    ${classes.choiceAndResult} 
                    ${choice.answer ? classes.isAnswerChoice : ''}
                    ${(!choice.answer && userSelect === index) ? classes.userChooseChoice : ''}
                    `}>
                    <div className={classes.choiceContentContainer}>

                      <span className={classes.choiceNumber}>{index + 1}. </span>
                      <span className={classes.choiceContent}>
                        {
                          choice.answer ? (
                            <span className={classes.answerMark}>(정답) </span>
                          ) : null
                        }
                        {
                          (!choice.answer && userSelect === index) ? (
                            <span className={classes.userChooseMark}>(내 선택) </span>
                          ) : null
                        }
                        {choice.content}
                      </span>
                    </div>
                    <div className={classes.choiceInfo}>
                      <PercentageBar percentage={percent(sumOfChoices, choice.selectedCount)}/>
                    </div>
                  </div>
                )
              })
            }
          </div>
        </div>
      </main>
    </>
  );
}

export default AudioMcqQuestionResultPage;