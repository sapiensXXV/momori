import classes from './TextMcqChoiceList.module.css'
import React, {FC} from "react";


type TextMcqChoiceProps = {
  questionIndex: number;
  choiceIndex: number;
  choiceAnswerCheck: (qi: number, ci: number) => void;
  choiceInputChange: (e: React.ChangeEvent<HTMLInputElement>, qi: number, ci: number)=> void;
  deleteChoice: (qi: number, ci: number) => void;
  answer: boolean;
  content: string;
}

const TextMcqChoice: FC<TextMcqChoiceProps> = ({
  questionIndex,
  choiceIndex,
  choiceAnswerCheck,
  choiceInputChange,
  deleteChoice,
  answer,
  content,
}) => {
  return (
    <div className={classes.choice} key={`${questionIndex}_${choiceIndex}_choice`}>
      {/* 체크박스 레이블 */}
      <label className={classes.checkboxContainer}>
        <input
          type="checkbox"
          checked={answer}
          onChange={() => choiceAnswerCheck(questionIndex, choiceIndex)}
          className={classes.hiddenCheckbox}
        />
        <span className={classes.customCheckbox}></span>
      </label>
      <span className={classes.choiceNumber}>{choiceIndex + 1}. </span>
      <input
        className={`${classes.choiceInput} common-input-sm`}
        type="text"
        placeholder="선택지를 입력하세요"
        value={content}
        onChange={(e) => choiceInputChange(e, questionIndex, choiceIndex)}
      />
      <button className={`${classes.choiceDeleteButton} common-button`}
              onClick={() => deleteChoice(questionIndex, choiceIndex)}>
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" className="size-6">
          <path stroke-linecap="round" stroke-linejoin="round"
                d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
        </svg>
      </button>
    </div>
  )
}

export default TextMcqChoice;