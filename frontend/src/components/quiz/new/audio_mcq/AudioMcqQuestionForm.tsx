import classes from './AudioMcqForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {AudioUploadStatus, NewAudioMcqQuestion} from "../../../../types/question.ts";
import React, {useCallback, useEffect, useRef, useState} from "react";
import AudioUploadModal from "../common/modal/AudioUploadModal.tsx";
import AddAudioMcqQuestionButton from "./AddAudioMcqQuestionButton.tsx";
import {YouTubePlayer} from "react-youtube";
import {QuizTypes} from "../../types/Quiz.types.ts";
import {PlayerState} from "../../../../global/player/player.ts";
import {
  CHOICE_MAX_LIMIT_MSG,
  CHOICE_MIN_LIMIT_MSG,
  QUESTION_MIN_LIMIT_MST
} from "../../../../global/message/quiz_message.ts";
import {MAX_CHOICE_COUNT, MIN_CHOICE_COUNT} from "../../../../global/constant/question.ts";
import ExternalVideo from "../common/video/ExternalVideo.tsx";
import AudioMcqQuestionDeleteButton from "./AudioMcqQuestionDeleteButton.tsx";
import TextMcqChoiceList from "../common/choice/TextMcqChoiceList.tsx";
import VideoQuestionController from "../common/video/VideoQuestionController.tsx";

const AudioMcqQuestionForm = () => {
  const {quizType, questions, setQuestions} = useQuizContext<NewAudioMcqQuestion>();
  const [showAudioUploadModal, setShowAudioUploadModal] = useState<boolean>(false);
  const [selectedQuestionIndex, setSelectedQuestionIndex] = useState(0);

  // YouTube 플레이어 관련 상태
  const playerRef = useRef<YouTubePlayer | null>(null);
  const [isPlay, setIsPlay] = useState<boolean>(false);
  const [isReady, setIsReady] = useState<boolean>(false);

  const deleteQuestion = (qi: number) => {
    if (questions.length <= 1) {
      alert(QUESTION_MIN_LIMIT_MST);
      return;
    }
    setQuestions(prev =>
      prev.filter((_, index) => index !== qi)
    );
  };

  const handleShowAudioUploadModal = (index: number) => {
    setSelectedQuestionIndex(index);
    setShowAudioUploadModal(true);
  };

  const submitAudio = (
    audioId: string,
    startTime: number,
    playDuration: number | undefined,
    questionId: number
  ) => {
    setQuestions(prev => (
      prev.map((question, index) => (
        index !== questionId ? question : {
          audioStatus: AudioUploadStatus.UPLOADED,
          audioId: audioId,
          startTime: startTime,
          playDuration: playDuration,
          choices: question.choices
        }
      ))
    ));
  };

  // YouTube 이벤트 핸들러들
  const onReady = (event: any) => {
    playerRef.current = event.target;
    setIsReady(true);
  };

  const onStateChange = useCallback((event: any) => {
    const playerState = event.target.getPlayerState();

    switch (playerState) {
      case PlayerState.ENDED:
      case PlayerState.PAUSED:
        setIsPlay(false);
        break;
      case PlayerState.PLAYING:
        setIsPlay(true);
        break;
    }
  }, []);

  // 재생/정지 처리
  const handlePlayPause = useCallback((index: number) => {
    if (!playerRef.current) return;
    const player = playerRef.current;
    const currentQuestion = questions[index];

    // 다른 문제의 오디오를 선택한 경우
    if (selectedQuestionIndex !== index) {
      // 1. 현재 재생 중인 오디오가 있다면 정지
      if (isPlay) {
        player.pauseVideo();
      }

      // 2. 상태 업데이트
      setIsReady(false); // 영상이 바뀌면 당장 준비되진 않음.
      setSelectedQuestionIndex(index);
      setIsPlay(true);  // 새로운 영상은 재생할 것이므로 true로 설정

      // 3. useEffect에서 처리하도록 이동
    }
    // 같은 문제의 오디오를 선택한 경우
    else {
      if (isPlay) {
        player.pauseVideo();
        setIsPlay(false);
      } else {
        player.seekTo(currentQuestion.startTime, true);
        player.playVideo();
        setIsPlay(true);
      }
    }
  }, [selectedQuestionIndex, questions, isPlay]);


  useEffect(() => {
    const player = playerRef.current;
    const currentQuestion = questions[selectedQuestionIndex];
    if (player && currentQuestion) {
      player.seekTo(currentQuestion.startTime, true);
      if (isPlay) {  // isPlay 상태에 따라 자동 재생
        player.playVideo();
      }
    }
  }, [selectedQuestionIndex, questions, isPlay, isReady])

  const isHaveAudioId = (index: number) => {
    const audioId = questions[index].audioId;
    if (audioId !== null && audioId !== undefined && audioId !== '') {
      return true;
    }
    return false;
  }

  // 선택지
  // 선택지 정답 체크
  const choiceAnswerCheck = (qi: number, ci: number) => {
    setQuestions(prev =>
      prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          choices: question.choices.map((choice, cIdx) =>
            cIdx !== ci ? choice : {...choice, answer: !choice.answer}
          )
        }
      })
    )
  }
  // 선택지 삽입
  const addChoice = (index: number) => {
    setQuestions((prev) => (
      prev.map((question, qi) => {
        if (index !== qi) return question;
        if (question.choices.length >= MAX_CHOICE_COUNT) {
          alert(CHOICE_MAX_LIMIT_MSG);
          return question;
        }
        return {
          ...question,
          choices: [ ...question.choices, { content: "", answer: false} ]
        };
      })
    ))
  };

  // 선택지 입력 변화
  const choiceInputChange = (e: React.ChangeEvent<HTMLInputElement>, qi: number, ci: number) => {
    setQuestions(prev =>
      prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          choices: question.choices.map((choice, cIdx) =>
            cIdx !== ci ? choice : { content: e.target.value, answer: choice.answer }
          )
        }
      })
    )
  }

  // 선택지 삭제
  const deleteChoice = (qi: number, ci: number) => {
    if (questions[qi].choices.length <= MIN_CHOICE_COUNT) {
      alert(CHOICE_MIN_LIMIT_MSG);
      return;
    }

    setQuestions(prev => {
      return prev.map((question, qIdx) => {
        return qIdx !== qi ? question : {
          ...question,
          choices: question.choices.filter((_, cIdx) => cIdx !== ci),
        }
      })
    })
  }

  return (
    <>
      {showAudioUploadModal && (
        <AudioUploadModal
          quizType={QuizTypes.AUDIO_MCQ}
          setShowModal={setShowAudioUploadModal}
          questionIndex={selectedQuestionIndex}
          submitAudio={submitAudio}
        />
      )}
      <main className={`${classes.questionContainer} common-flex-column`}>
        {questions?.map((question, qi) => (
          <React.Fragment key={qi}>
            <hr className="common-hr"/>
            <div className={classes.question}>
              <AudioMcqQuestionDeleteButton deleteQuestion={deleteQuestion} questionIndex={qi} />
              <VideoQuestionController
                questionIndex={qi}
                selectedQuestionIndex={selectedQuestionIndex}
                handleShowAudioUploadModal={handleShowAudioUploadModal}
                handlePlayPause={handlePlayPause}
                isHaveAudioId={isHaveAudioId}
                isPlay={isPlay}
              />
              <button className={classes.choiceAddButton} onClick={() => addChoice(qi)}>
                <span>선택지 추가</span>
              </button>
              <TextMcqChoiceList
                choices={question?.choices}
                questionIndex={qi}
                quizType={quizType}
                choiceAnswerCheck={choiceAnswerCheck}
                choiceInputChange={choiceInputChange}
                deleteChoice={deleteChoice}
              />
            </div>
          </React.Fragment>
        ))}

        {/* 문제 추가 버튼 */}
        <AddAudioMcqQuestionButton/>
      </main>
      {/* 사용자에게는 보이지 않는 유튜브 화면 컴포넌트 */}
      {
        questions[selectedQuestionIndex]?.audioId !== undefined &&
        questions[selectedQuestionIndex]?.startTime !== undefined &&
        questions[selectedQuestionIndex]?.playDuration !== undefined &&
        <ExternalVideo
          videoId={questions[selectedQuestionIndex].audioId}
          startTime={questions[selectedQuestionIndex].startTime}
          playDuration={questions[selectedQuestionIndex].playDuration}
          onReady={onReady}
          onStateChange={onStateChange}
          autoPlay={0} // 자동 재생하지 않음.
        />
      }
    </>
  );
};

export default AudioMcqQuestionForm;