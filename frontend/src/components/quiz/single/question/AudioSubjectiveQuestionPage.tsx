import React, {FC, useCallback, useEffect, useRef, useState} from "react";
import classes from "./SubjectiveQuestion.module.css";
import {
  AudioSubDetailQuestion,
} from "../../../../types/question.ts";
import LottieComponent from "../../../lottie/LottieComponent.tsx";
import audioPlayingAnimation from "../../../../../public/animation/audio_playing.json";
import CircularProgressBar from "../../../common/CircularProgressBar.tsx";
import ExternalVideo from "../../new/common/video/ExternalVideo.tsx";
import {YouTubeEvent, YouTubePlayer} from "react-youtube";
import {PlayerState} from "../../../../global/player/player.ts";
import {useAlertManager} from "../../../alert/useAlertManager.hook.tsx";

type AudioSubjectiveQuestionPageProps = {
  question: AudioSubDetailQuestion;
  afterSubmit: (isCorrect: boolean, userInput: string) => void;
}

const AudioSubjectiveQuestionPage: FC<AudioSubjectiveQuestionPageProps> = ({ question, afterSubmit }) => {

  const [userInput, setUserInput] = useState<string>("");
  const inputRef = useRef<HTMLInputElement>(null);

  const [isVideoReady, setIsVideoReady] = useState<boolean>(false);
  const youtubePlayerRef = useRef<YouTubePlayer | null>(null);
  const [isVideoPlaying, setIsVideoPlaying] = useState<boolean>(false);
  const [progress, setProgress] = useState<number>(0);

  // 타이머 관련 ref
  const timerRef = useRef<NodeJS.Timeout | null>(null);
  const timerStartTimeRef = useRef<number>(0);

  const [isComposing, setIsComposing] = useState<boolean>(false);
  const {showAlert, AlertContainer} = useAlertManager();

  useEffect(() => {
    inputRef.current?.focus();
  }, []);

  const answerKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter' && !isComposing) {
      e.preventDefault()
      e.stopPropagation()
      answerSubmit();
    }
  }

  const handleCompositionStart = () => {
    setIsComposing(true);
  };

  const handleCompositionEnd = () => {
    setIsComposing(false);
  };

  const answerSubmit = useCallback(() => {
    if (userInput == null || userInput === undefined || userInput.length === 0) {
      showAlert('정답을 입력해주세요');
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

    // 포커스 해제
    inputRef.current?.blur();
  }, [userInput]);

  const videoReady = (e: YouTubeEvent) => {
    youtubePlayerRef.current = e.target; // 플레이어 설정
    setIsVideoReady(true);
  }

  const onStateChange = useCallback((event: any) => {
    const playerState = event.target.getPlayerState();
    switch (playerState) {
      case PlayerState.ENDED:
      case PlayerState.PAUSED:
        setIsVideoPlaying(false);
        stopCalculateProgress();
        break;
      case PlayerState.PLAYING:
        setIsVideoPlaying(true);
        startCalculateProgress();
        break;
      default:
        setIsVideoPlaying(false);
        stopCalculateProgress();
    }
  }, []);

  const startCalculateProgress = () => {
    timerStartTimeRef.current = Date.now();
    timerRef.current = setInterval(() => {
      const elapsed = Date.now() - timerStartTimeRef.current; // ms 단위
      const newProgress = ((elapsed / 1000) / question.playDuration) * 100;
      if (newProgress >= 100) {
        setProgress(100);
        if (timerRef.current) {
          // 타이머를 삭제
          clearInterval(timerRef.current);
          timerRef.current = null;
        }
      } else {
        setProgress(newProgress);
      }
    }, 30); // 100ms 간격으로 실행
  }

  const stopCalculateProgress = () => {
    if (timerRef.current) {
      // 타이머를 삭제
      clearInterval(timerRef.current);
      timerRef.current = null;
    }
    setProgress(0); // 영상이 종료되거나 정지되면 진행 상태를 0으로 초기화
  }

  const audioButtonClicked = () => {
    if (!isVideoReady) return;

    const player = youtubePlayerRef.current;
    if (isVideoPlaying) {
      // 영상이 재생중인 경우
      player.pauseVideo(); // 영상 정지
      setIsVideoPlaying(false);
    } else {

      player.seekTo(question.startTime, true); // 시작 시간으로 돌아간다.
      player.playVideo(); // 영상 재생
      setIsVideoPlaying(true);
    }
  }

  return (
    <>
      <AlertContainer/>
      <main className={classes.questionContainer}>
        <ExternalVideo
          videoId={question.audioId}
          startTime={question.startTime}
          playDuration={question.playDuration}
          onReady={videoReady}
          onStateChange={onStateChange}
          autoPlay={1} // 자동재생
        />
        <div className={classes.questionContentContainer}>
          <CircularProgressBar progress={progress} circleWidth={200}>
            <div
              className={`${classes.audioButtonContainer}`}
              onClick={audioButtonClicked}
            >
              {
                // 비디오가 준비되기 전에는 로딩 화면을 보여준다.
                isVideoReady
                  ? <LottieComponent
                    animationData={audioPlayingAnimation}
                    loop={true}
                    autoplay={true}
                    speed={1}
                    isPaused={false}
                    isStopped={!isVideoPlaying}
                  />
                  : <img src={"/img/icon/loading.gif"}/>
              }
            </div>
          </CircularProgressBar>
          <input
            className={`common-input-sm ${classes.answerInput}`}
            ref={inputRef}
            onChange={(e) => setUserInput(e.target.value)}
            type={"text"}
            placeholder={"정답을 입력하세요"}
            value={userInput}
            onKeyDown={(e) => answerKeyDown(e)}
            onCompositionStart={handleCompositionStart} // IME 입력 시작
            onCompositionEnd={handleCompositionEnd}     // IME 입력 종료
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

export default AudioSubjectiveQuestionPage;