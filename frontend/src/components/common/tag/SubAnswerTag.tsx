import {FC} from "react";
import classes from './SubAnswerTag.module.css';
import {getRandomTagColor} from "./tag_color.ts";

type SubAnswerTagProps = {
  questionIndex: number;
  answerIndex: number
  deleteAnswer: (qi: number, index: number) => void;
  content: string;
}

const SubAnswerTag: FC<SubAnswerTagProps> = ({ questionIndex, answerIndex, deleteAnswer, content}) => {

  return (
    <div
      className={classes.subAnswerTagContainer}
      style={{
        backgroundColor: `${getRandomTagColor(content)}38`,
        borderColor: getRandomTagColor(content),
      }}
    >
      <text>{content}</text>
      <div className={classes.tagDeleteButton} onClick={() => deleteAnswer(questionIndex, answerIndex)}>
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
             className="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12"/>
        </svg>
      </div>


    </div>
  )
}

export default SubAnswerTag;