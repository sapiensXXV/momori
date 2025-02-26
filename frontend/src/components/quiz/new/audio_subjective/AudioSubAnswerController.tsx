import classes from './AudioSubAnswerController.module.css';
import SubAnswerTag from "../../../common/tag/SubAnswerTag.tsx";
import React, {FC, useState} from "react";


type AudioSubAnswerControllerProps = {
  questionIndex: number; // 문제 인덱스
  contents: string[];
  addAnswer: (qi: number, value: string) => void; // 정답을 추가했을 때 호출되는 메서드
  deleteAnswer: (qi: number, index: number) => void; // 정답을 삭제할 때 호출되는 메서드
}

const AudioSubAnswerController: FC<AudioSubAnswerControllerProps> = ({
  questionIndex,
  contents,
  addAnswer,
  deleteAnswer
}) => {

  const [answer, setAnswer] = useState<string>("");
  const [isComposing, setIsComposing] = useState<boolean>(false); // IME 입력 중 여부

  const submitAnswer = (value: string) => {
    console.log('submitAnswer called with:', value);
    if (contents.includes(value)) {
      alert('이미 포함된 내용은 중복해서 작성할 수 없습니다.');
      return;
    }
    if (value.length === 0) {
      alert('빈 문자열을 정답으로 등록할 수 없습니다.');
      return;
    }
    setAnswer("");
    addAnswer(questionIndex, value);
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter' && !isComposing) { // IME 입력 중이 아닐 때만 처리
      e.preventDefault();
      submitAnswer(answer);
    }
  };

  const handleCompositionStart = () => {
    setIsComposing(true);
  };

  const handleCompositionEnd = () => {
    setIsComposing(false);
  };

  return (
    <main className={classes.subAnswerContainer}>
      <div className={classes.subAnswerSubmitContainer}>
        <input
          className={`common-input-sm`}
          placeholder={"정답을 입력하세요. (최대 10개, 30자 이하)"}
          value={answer}
          onChange={(e) => setAnswer(e.target.value)}
          onKeyDown={handleKeyDown}
          onCompositionStart={handleCompositionStart} // IME 입력 시작
          onCompositionEnd={handleCompositionEnd}     // IME 입력 종료
        />
        <div
          className={`common-button ${classes.subAnswerSubmitButton}`}
          onClick={() => submitAnswer(answer)}
        >
          추가
        </div>
      </div>
      <div className={classes.subAnswerListContainer}>
        {contents?.map((content, index) => (
          <SubAnswerTag
            key={`question_${questionIndex}_answer_${index}`}
            questionIndex={questionIndex}
            answerIndex={index}
            deleteAnswer={deleteAnswer}
            content={content}
          />
        ))}
      </div>
    </main>
  );
};

export default AudioSubAnswerController;
