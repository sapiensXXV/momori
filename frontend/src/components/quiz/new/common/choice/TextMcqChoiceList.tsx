import {QuizTypes} from "../../../types/Quiz.types.ts";
import classes from "./TextMcqChoiceList.module.css";
import {NewAudioMcqChoice, NewImageMcqChoice} from "../../../../../types/choice.ts";
import React, {FC} from "react";
import TextMcqChoice from "./TextMcqChoice.tsx";

type TextMcqChoiceProps = {
  choices: NewAudioMcqChoice[] | NewImageMcqChoice[];
  questionIndex: number;
  quizType: QuizTypes;
  choiceAnswerCheck: (qi: number, ci: number) => void;
  choiceInputChange: (e: React.ChangeEvent<HTMLInputElement>, qi: number, ci: number) => void;
  deleteChoice: (qi: number, ci: number) => void;
}

const TextMcqChoiceList: FC<TextMcqChoiceProps> = ({
  quizType,
  choices,
  questionIndex,
  choiceAnswerCheck,
  choiceInputChange,
  deleteChoice
}) => {

  const getTextMcqChoice = () => {
    if (quizType === QuizTypes.IMAGE_MCQ) {
      return (
        <div className={classes.choiceContainer}>
          {choices?.map((choice: NewImageMcqChoice, ci: number) => ( // NewImageMcqChoice
            <TextMcqChoice
              questionIndex={questionIndex}
              choiceIndex={ci}
              choiceAnswerCheck={choiceAnswerCheck}
              choiceInputChange={choiceInputChange}
              deleteChoice={deleteChoice}
              answer={choice.answer}
              content={choice.content}
            />
          ))}
        </div>
      )
    } else if (quizType === QuizTypes.AUDIO_MCQ) {
      return (
        <div className={classes.choiceContainer}>
          {choices?.map((choice: NewAudioMcqChoice, ci: number) => ( // NewAudioMcqChoice
            <TextMcqChoice
              questionIndex={questionIndex}
              choiceIndex={ci}
              choiceAnswerCheck={choiceAnswerCheck}
              choiceInputChange={choiceInputChange}
              deleteChoice={deleteChoice}
              answer={choice.answer}
              content={choice.content}
            />
          ))}
        </div>
      )
    }
  }


  return (
    getTextMcqChoice()
  )
}

export default TextMcqChoiceList;