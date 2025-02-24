import {AudioMcqDetailQuestion} from "../../../../types/question.ts";
import {FC, useCallback, useEffect, useRef, useState} from "react";
import classes from "./McqQuestion.module.css";
import ExternalVideo from "../../new/common/video/ExternalVideo.tsx";
import {YouTubeEvent, YouTubePlayer} from "react-youtube";
import LottieComponent from "../../../lottie/LottieComponent.tsx";
import audioPlayingAnimation from "../../../../../public/animation/audio_playing.json";
import {PlayerState} from "../../../../global/player/player.ts";

type AudioMcqQuestionPageProps = {
  question: AudioMcqDetailQuestion;
  afterSubmit: (isSelectAnswer: boolean, selectedIndex: number) => void;
}

const AudioMcqQuestionPage: FC<AudioMcqQuestionPageProps> = ({question, afterSubmit}) => {

  const [selected, setSelected] = useState<number | null>(null);
  const [isVideoReady, setIsVideoReady] = useState<boolean>(false);
  const youtubePlayerRef = useRef<YouTubePlayer | null>(null);
  const [isVideoPlaying, setIsVideoPlaying] = useState<boolean>(false);

  useEffect(() => {
    return (() => {
      setIsVideoReady(false);
    });
  }, []);

  console.log(`isVideoReady=${isVideoReady}`);

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

    switch (playerState) {
      case PlayerState.ENDED:
      case PlayerState.PAUSED:
        setIsVideoPlaying(false);
        break;
      case PlayerState.PLAYING:
        setIsVideoPlaying(true);
        break;
    }
  }, []);

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
        />
        <div className={classes.questionContentContainer}>
          {/* ExternalVideo 재생버튼  */}
          <div className={classes.audioButtonContainer} onClick={audioButtonClicked}>
            {
              // 비디오가 준비되었을 때 로딩화면을 보여준다.
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
  )
}

export default AudioMcqQuestionPage;