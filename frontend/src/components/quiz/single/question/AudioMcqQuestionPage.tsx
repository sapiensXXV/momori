import {AudioMcqDetailQuestion} from "../../../../types/question.ts";
import {FC, useCallback, useEffect, useRef, useState} from "react";
import classes from "./McqQuestion.module.css";
import ExternalVideo from "../../new/common/video/ExternalVideo.tsx";
import {YouTubeEvent, YouTubePlayer} from "react-youtube";
import LottieComponent from "../../../lottie/LottieComponent.tsx";
import audioPlayingAnimation from "../../../../../public/animation/audio_playing.json";
import {PlayerState} from "../../../../global/player/player.ts";
import CircularProgressBar from "../../../common/CircularProgressBar.tsx";

type AudioMcqQuestionPageProps = {
  question: AudioMcqDetailQuestion;
  afterSubmit: (isSelectAnswer: boolean, selectedIndex: number) => void;
}

const AudioMcqQuestionPage: FC<AudioMcqQuestionPageProps> = ({question, afterSubmit}) => {

  const [selected, setSelected] = useState<number | null>(null);
  const [isVideoReady, setIsVideoReady] = useState<boolean>(false);
  const youtubePlayerRef = useRef<YouTubePlayer | null>(null);
  const [isVideoPlaying, setIsVideoPlaying] = useState<boolean>(false);
  const [progress, setProgress] = useState<number>(0);

// 타이머 관련 ref
  const timerRef = useRef<NodeJS.Timeout | null>(null);
  const timerStartTimeRef = useRef<number>(0);

  useEffect(() => {
    return (() => {
      setIsVideoReady(false);
      setIsVideoPlaying(false);
      // 타이머가 남아있다면 해제
      if (timerRef.current) {
        clearInterval(timerRef.current);
        timerRef.current = null;
      }
    });
  }, []);

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
    if (question.choices[selectedIndex].answer) {
      // 정답을 선택한 경우 afterSubmit 메서드에 true 전달
      afterSubmit(true, selectedIndex);
    } else {
      // 오답을 선택한 경우 afterSubmit 메서드에 false 전달
      afterSubmit(false, selectedIndex);
    }
  }

  const videoReady = (e: YouTubeEvent) => {
    youtubePlayerRef.current = e.target; // 플레이어 설정
    setIsVideoReady(true);
  }

  const onStateChange = useCallback((event: any) => {
    const playerState = event.target.getPlayerState();
    console.log(playerState);
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
    console.log('audioButton 이 클릭되었습니다.')
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
  );
}

export default AudioMcqQuestionPage;