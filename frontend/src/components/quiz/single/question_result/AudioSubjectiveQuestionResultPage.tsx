import {AudioSubDetailQuestion} from "../../../../types/question.ts";
import {FC, useEffect, useState} from "react";
import classes from "./SubjectiveQuestionResult.module.css";
import PercentageBar from "./PercentageBar.tsx";
import {percent} from "../../../../global/util/percent.tsx";
import YouTube from "react-youtube";

type AudioSubjectiveQuestionResultPageProps = {
  isCorrect: boolean;
  question: AudioSubDetailQuestion;
  nextQuestion: () => void; // 다음 문제를 호출하는 함수
}

const AudioSubjectiveQuestionResultPage: FC<AudioSubjectiveQuestionResultPageProps> = ({ isCorrect, question, nextQuestion }) => {

  const [isComposing, setIsComposing] = useState<boolean>(false);

  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Enter' && !isComposing) {
        e.preventDefault();
        e.stopPropagation();
        nextQuestion();
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    window.addEventListener('compositionstart', () => setIsComposing(true));
    window.addEventListener('compositionend', () => setIsComposing(false));

    return () => {
      window.removeEventListener('keydown', handleKeyDown);
      window.removeEventListener('compositionstart', () => setIsComposing(true));
      window.removeEventListener('compositionend', () => setIsComposing(false));
    };
  }, []);

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
            <span className={classes.resultText}>{isCorrect ? "정답!" : "오답!"}</span>
            <span className={classes.answerText}>{question.answers[0]}</span>
            <div className={classes.percentageContainer}>
              <span className={classes.percentageText}>정답률: </span>
              <PercentageBar percentage={percent(question.tryCount, question.correctCount)}/>
            </div>
            <div className={`${classes.nextButton} common-button`} onClick={nextQuestion}>
              <span>다음으로</span>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3"
                   stroke="currentColor" className="size-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="m8.25 4.5 7.5 7.5-7.5 7.5"/>
              </svg>
            </div>
          </div>
      </main>

    </>
);
}

export default AudioSubjectiveQuestionResultPage;