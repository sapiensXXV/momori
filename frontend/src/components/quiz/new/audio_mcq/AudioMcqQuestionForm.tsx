import classes from './AudioMcqForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {AudioUploadStatus, NewAudioMcqQuestion} from "../../../../types/question.ts";
import React, {useCallback, useRef, useState} from "react";
import AudioUploadModal from "../common/modal/AudioUploadModal.tsx";
import AddAudioMcqQuestionButton from "./AddAudioMcqQuestionButton.tsx";
import YouTube, {YouTubePlayer} from "react-youtube";
import {QuizTypes} from "../../types/Quiz.types.ts";
import {PlayerState} from "../../../../global/player/player.ts";
import audioPlayAnimation from "../../../../../public/animation/audio_playing.json";
import Lottie, {LottieRef} from "lottie-react";
import LottieComponent from "../../../lottie/LottieComponent.tsx";

const AudioMcqQuestionForm = () => {
  const {questions, setQuestions} = useQuizContext<NewAudioMcqQuestion>();
  const [showAudioUploadModal, setShowAudioUploadModal] = useState<boolean>(false);
  const [selectQuestionIndex, setSelectedQuestionIndex] = useState(0);

  // YouTube 플레이어 관련 상태
  const playerRef = useRef<YouTubePlayer | null>(null);
  const [isPlay, setIsPlay] = useState<boolean>(false);

  // Lottie
  const lottieRef = useRef<LottieRef>(null);

  const deleteQuestion = (qi: number) => {
    setQuestions(prev =>
      prev.filter((_, index) => index !== qi)
    );
  };

  const addChoice = (index: number) => {
    console.log('add choice');
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
  const onReady = useCallback((event: any) => {
    playerRef.current = event.target;
  }, []);

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
    if (selectQuestionIndex !== index) {
      setSelectedQuestionIndex(index);
      setIsPlay(false);

      // 현재 재생 중인 오디오가 있다면 정지
      if (isPlay) {
        player.stopVideo();
      }

      // 새로운 시작 시간으로 이동
      player.seekTo(currentQuestion.startTime, true);
      player.playVideo();
    }
    // 같은 문제의 오디오를 선택한 경우
    else {
      if (isPlay) {
        player.stopVideo();
        setIsPlay(false);
      } else {
        player.seekTo(currentQuestion.startTime, true);
        player.playVideo();
        setIsPlay(true);
      }
    }
  }, [selectQuestionIndex, questions, isPlay]);

  const isHaveAudioId = (index: number) => {
    const audioId = questions[index].audioId;
    if (audioId !== null && audioId !== undefined && audioId !== '') {
      return true;
    }
    return false;
  }

  console.log(questions);
  console.log(isHaveAudioId(0));

  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: audioPlayAnimation,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice"
    }
  };

  const stopAnimation = () => {
    lottieRef.current?.goToAndStop(0, true);
  }
  return (
    <>
      {showAudioUploadModal && (
        <AudioUploadModal
          quizType={QuizTypes.AUDIO_MCQ}
          setShowModal={setShowAudioUploadModal}
          questionIndex={selectQuestionIndex}
          submitAudio={submitAudio}
        />
      )}
      <main className={`${classes.questionContainer} common-flex-column`}>
        {questions?.map((question, qi) => (
          <React.Fragment key={qi}>
            <hr className="common-hr"/>
            <div className={classes.question}>
              <div className={classes.deleteButtonContainer}>
                <button
                  className={classes.quizDeleteButton}
                  onClick={() => deleteQuestion(qi)}
                >
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5"
                       stroke="currentColor" className="size-6">
                    <path strokeLinecap="round" strokeLinejoin="round"
                          d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
                  </svg>
                </button>
              </div>
              <div className={classes.audioContentContainer}>
                <div
                  className={classes.audioApplyButton}
                  onClick={() => handleShowAudioUploadModal(qi)}
                >
                  {
                    isHaveAudioId(qi) ?
                      // <Lottie animationData={audioPlayAnimation} />
                      <LottieComponent
                        animationData={audioPlayAnimation}
                        loop={true}
                        autoplay={true}
                        speed={1}
                        isPaused={false}
                        isStopped={!(isPlay && selectQuestionIndex === qi)}
                      />
                      : <span className={classes.audioApplyText}>오디오 등록</span>
                  }
                </div>
                <div className={classes.audioButtonContainer}>
                  <div
                    className={`${classes.audioButton} common-button ${isPlay && selectQuestionIndex === qi ? 'rotatingButton' : ''}`}
                    onClick={() => handlePlayPause(qi)}
                    style={
                      isPlay && selectQuestionIndex === qi
                        ? {"--duration": `${questions[qi].playDuration}s`} as React.CSSProperties
                        : {}
                    }
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
                         className="size-6">
                      <path
                        d="M15 6.75a.75.75 0 0 0-.75.75V18a.75.75 0 0 0 .75.75h.75a.75.75 0 0 0 .75-.75V7.5a.75.75 0 0 0-.75-.75H15ZM20.25 6.75a.75.75 0 0 0-.75.75V18c0 .414.336.75.75.75H21a.75.75 0 0 0 .75-.75V7.5a.75.75 0 0 0-.75-.75h-.75ZM5.055 7.06C3.805 6.347 2.25 7.25 2.25 8.69v8.122c0 1.44 1.555 2.343 2.805 1.628l7.108-4.061c1.26-.72 1.26-2.536 0-3.256L5.055 7.061Z"/>
                    </svg>
                  </div>
                </div>
              </div>
              <div>{/* 선택지 */}</div>
            </div>
          </React.Fragment>
        ))}
        <AddAudioMcqQuestionButton/>

        <div className={classes.hiddenYoutubeContainer}>
          <YouTube
            videoId={questions[selectQuestionIndex]?.audioId}
            opts={{
              width: "100%",
              playerVars: {
                autoplay: 0,
                rel: 0,
                start: questions[selectQuestionIndex]?.startTime,
                ...(questions[selectQuestionIndex]?.playDuration !== undefined && {
                  end: questions[selectQuestionIndex]?.startTime + questions[selectQuestionIndex]?.playDuration
                })
              },
              volume: 0.5
            }}
            onReady={onReady}
            onStateChange={onStateChange}
          />
        </div>
      </main>
    </>
  );
};

export default AudioMcqQuestionForm;