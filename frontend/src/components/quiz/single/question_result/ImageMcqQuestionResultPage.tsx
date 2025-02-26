import classes from './McqQuestionResult.module.css';
import {ImageMcqDetailQuestion} from "../../../../types/question.ts";
import React, {FC, useEffect, useState} from "react";
import {percent} from "../../../../global/util/percent.tsx";
import PercentageBar from "./PercentageBar.tsx";

type ImageMcqQuestionResultPageProps = {
  isCorrect: boolean;
  question: ImageMcqDetailQuestion;
  nextQuestion: () => void;
  userSelect: number;
}
const ImageMcqQuestionResultPage: FC<ImageMcqQuestionResultPageProps> = ({
  isCorrect,
  question,
  nextQuestion,
  userSelect
}) => {
  const [sumOfChoices, setSumOfChoices] = useState<number>(0);
  const [isComposing, setIsComposing] = useState<boolean>(false);

  useEffect(() => {
    setSumOfChoices(calculateSelectSum);
    const handleKeyDown = (e: React.KeyboardEvent) => {
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
          <div className={classes.imageContainer}>
            <img className={classes.questionImage} src={question.imageUrl}/>
          </div>
          <span className={classes.resultText}>{isCorrect ? "정답!" : "오답"}</span>
          <div className={`${classes.nextButton} common-button`} onClick={nextQuestion}>
            <span>다음으로</span>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3"
                 stroke="currentColor" className="size-6">
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
                      <PercentageBar percentage={ percent(sumOfChoices, choice.selectedCount) } />
                      {/*<PercentageBar percentage={percent(100, 100)}/>*/}
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

export default ImageMcqQuestionResultPage;