import classes from './AudioSubAnswerController.module.css';
import SubAnswerTag from "../../../common/tag/SubAnswerTag.tsx";
import {FC, useState} from "react";


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

  const submitAnswer = (value: string) => {
    // 이미 포함된 정답 내용이거나 빈 문제일 경우 반려.
    if (contents.includes(value)) {
      // TODO: 나중에 공통 알림창으로 내보낼 것.
      alert('이미 포함된 내용은 중복해서 작성할 수 없습니다.');
      return;
    }
    if (value.length === 0) {
      alert('빈 문자열을 정답으로 등록할 수 없습니다.');
      return;
    }
    addAnswer(questionIndex, answer);
    setAnswer(""); // 정답을 등록한 후에는 사용자의 입력칸을 빈칸으로 처리.
  }

  return (
    <>
      <main className={classes.subAnswerContainer}>
        <div className={classes.subAnswerSubmitContainer}>
          <input
            className={`common-input-sm`}
            placeholder={"정답을 입력하세요. (최대 10개, 30자 이하)"}
            value={answer}
            onChange={(e) => setAnswer(e.target.value)}
          />
          <div
            className={`common-button ${classes.subAnswerSubmitButton}`}
            onClick={() => submitAnswer(answer)}
          >추가</div>
        </div>
        <div className={classes.subAnswerListContainer}>
          {
            contents.map((content, index) => {
              return (
                <SubAnswerTag
                  key={`question_${questionIndex}_answer_${index}`}
                  questionIndex={questionIndex}
                  answerIndex={index}
                  deleteAnswer={deleteAnswer}
                  content={content}
                />
              );
            })
          }
        </div>

      </main>
    </>
  );
}

export default AudioSubAnswerController;
