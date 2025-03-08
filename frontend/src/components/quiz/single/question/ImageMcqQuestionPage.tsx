import classes from './McqQuestion.module.css'
import {FC, useEffect, useState} from "react";
import {ImageMcqDetailQuestion} from "../../../../types/question.ts";
import {useAlertManager} from "../../../alert/useAlertManager.hook.tsx";
import {CHOICE_SELECT_NEED_MSG} from "../../../../global/message/quiz_message.ts";

type ImageMcqQuestionPageProps = {
  question: ImageMcqDetailQuestion;
  afterSubmit: (isSelectAnswer: boolean, selectedIndex: number) => void;
}

const ImageMcqQuestionPage: FC<ImageMcqQuestionPageProps> = ({question, afterSubmit}) => {

  const [selected, setSelected] = useState<number | null>(null);
  const [isComposing, setIsComposing] = useState<boolean>(false);
  const { showAlert, AlertContainer } = useAlertManager();

  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Enter' && !isComposing) {
        e.preventDefault();
        e.stopPropagation();
        answerSubmit(selected);
      }
      e.preventDefault();
      const number = Number(e.key);
      if (!isNaN(number) && number <= question.choices.length+1 && number >= 1) {
        choiceSelect(number-1); // 인덱스는 0부터 시작하기 때문
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
  }, [selected, isComposing]);


  const choiceSelect = (index: number) => {
    setSelected(index);
  }

  const isSelected = (index: number) => {
    if (index === selected) {
      return true;
    }
    return false;
  }

  // console.log(selected);
  const answerSubmit = (selectedIndex: number | null) => {
    if (selectedIndex == null) {
      showAlert(CHOICE_SELECT_NEED_MSG, 5000);
      return;
    }
    // selectedIndex 는 배열 인덱스 0부터 시작하기 때문에 1번 -> 0, 2번 -> 1에 매핑됨
    if (question.choices[selectedIndex].answer) {
      // 정답을 선택한 경우 afterSubmit 메서드에 true 전달
      afterSubmit(true, selectedIndex);
    } else {
      // 오답을 선택한 경우 afterSubmit 메서드에 false 전달
      afterSubmit(false, selectedIndex);
    }
  }

  return (
    <>
      <AlertContainer/>
      <main className={classes.questionContainer}>
        <div className={classes.questionContentContainer}>
          <div className={classes.imageContainer}>
            <img className={classes.questionImage} src={question.imageUrl}/>
          </div>
          <div className={classes.choiceContainer}>
            {
              question.choices.map((choice, index) => {
                return (
                  <div
                    key={`${question.questionId}_${index}`}
                    className={`${classes.choiceContentContainer} ${isSelected(index) ? classes.selectedChoice : ''}`}
                    onClick={() => choiceSelect(index)}>
                    <span className={classes.choiceNumber}>{index + 1}.</span>
                    <span>{choice.content}</span>
                  </div>
                )
              })
            }
          </div>
          <div className={`${classes.submitButton} common-button`} onClick={() => answerSubmit(selected)}>
            <div className={classes.submitButtonContentContainer}>
              <span>제출 </span>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3" stroke="currentColor" className="size-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="m7.49 12-3.75 3.75m0 0 3.75 3.75m-3.75-3.75h16.5V4.499"/>
              </svg>
            </div>
          </div>
        </div>
      </main>
    </>
  )
}

export default ImageMcqQuestionPage;