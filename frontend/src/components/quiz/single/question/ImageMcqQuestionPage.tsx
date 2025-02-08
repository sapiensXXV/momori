import classes from './McqQuestion.module.css'
import {FC, useState} from "react";
import {ImageMcqDetailQuestion} from "../../../../types/question.ts";

type ImageMcqQuestionPageProps = {
  question: ImageMcqDetailQuestion;
  afterSubmit: (isSelectAnswer: boolean) => void;
}

const ImageMcqQuestionPage: FC<ImageMcqQuestionPageProps> = ({question, afterSubmit}) => {

  const [selected, setSelected] = useState<number | null>(null);

  const choiceSelect = (index: number) => {
    setSelected(index);
  }

  const isSelected = (index: number) => {
    if (index === selected) {
      return true;
    }
    return false;
  }

  const answerSubmit = (selectedIndex: number | null) => {
    if (selectedIndex == null) {
      alert('선택지 중 하나를 선택해야합니다.')
      return;
    }
    // selectedIndex 는 배열 인덱스 0부터 시작하기 때문에 1번 -> 0, 2번 -> 1에 매핑됨
    if (question.choices[selectedIndex].isAnswer) {
      // 정답을 선택한 경우 afterSubmit 메서드에 true 전달
      afterSubmit(true);
    } else {
      // 오답을 선택한 경우 afterSubmit 메서드에 false 전달
      afterSubmit(false);
    }
  }

  return (
    <>
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