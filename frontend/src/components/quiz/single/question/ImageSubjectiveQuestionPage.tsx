import classes from './SubjectiveQuestion.module.css';
import {ImageSubjectiveDetailQuestion} from "../../../../types/question.ts";
import {FC, useEffect, useRef, useState} from "react";

type ImageSubjectiveQuestionPageProps = {
  question: ImageSubjectiveDetailQuestion;
  afterSubmit: (isCorrect: boolean, userInput: string) => void;
}

const ImageSubjectiveQuestionPage: FC<ImageSubjectiveQuestionPageProps> = ({ question, afterSubmit  }) => {

  const [userInput, setUserInput] = useState<string>("");
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    inputRef.current?.focus();
  }, []);

  const answerSubmit = () => {
    if (userInput == null || userInput === undefined || userInput.length === 0) {
      alert('정답을 입력해주세요');
      return;
    }
    const target = userInput.toLowerCase();
    const isCorrect = question.answers.some(answer => answer.toLowerCase() === target);

    // 정답 여부에 따라 afterSubmit 메서드에 다른 파라미터 전달
    if (isCorrect) {
      afterSubmit(true, userInput);
    } else {
      afterSubmit(false, userInput);
    }
  }
  return (
    <>
      <main className={classes.questionContainer}>
        <div className={classes.questionContentContainer}>
          <div className={classes.imageContainer}>
            <img className={classes.questionImage} src={question.imageUrl} />
          </div>
          <input
            className={`common-input-sm ${classes.answerInput}`}
            ref={inputRef}
            onChange={(e) => setUserInput(e.target.value)}
            type={"text"}
            placeholder={"정답을 입력하세요"}
          />

          <div className={`${classes.submitButton} common-button`} onClick={answerSubmit}>
            <div className={classes.submitButtonContentContainer}>
              <span>제출 </span>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3"
                   stroke="currentColor" className="size-6">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="m7.49 12-3.75 3.75m0 0 3.75 3.75m-3.75-3.75h16.5V4.499"/>
              </svg>
            </div>
          </div>
        </div>
      </main>
    </>
  )
}

export default ImageSubjectiveQuestionPage;