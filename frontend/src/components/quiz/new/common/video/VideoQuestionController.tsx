import classes from './VideoQuestionController.module.css'
import LottieComponent from "../../../../lottie/LottieComponent.tsx";
import audioPlayAnimation from "../../../../../../public/animation/audio_playing.json";
import { FC } from "react";

type VideoQuestionControllerProps = {
  questionIndex: number;
  selectedQuestionIndex: number;
  handleShowAudioUploadModal: (qi: number) => void;
  handlePlayPause: (qi: number) => void;
  isHaveAudioId: (qi: number) => boolean;
  isPlay: boolean;
}

const VideoQuestionController: FC<VideoQuestionControllerProps> = ({
  questionIndex,
  selectedQuestionIndex,
  handleShowAudioUploadModal,
  handlePlayPause,
  isHaveAudioId,
  isPlay
}) => {
  return (
    <div className={classes.audioContentContainer}>
      <div
        className={classes.audioApplyButton}
        onClick={() => handleShowAudioUploadModal(questionIndex)}
      >
        {
          isHaveAudioId(questionIndex) ?
            <LottieComponent
              animationData={audioPlayAnimation}
              loop={true}
              autoplay={true}
              speed={1}
              isPaused={false}
              isStopped={!(isPlay && selectedQuestionIndex === questionIndex)}
            />
            : <span className={classes.audioApplyText}>오디오 등록</span>
        }
      </div>
      <div className={classes.audioButtonContainer} onClick={() => handlePlayPause(questionIndex)}>
        <div
          className={`${classes.audioButton} common-button ${isPlay && selectedQuestionIndex === questionIndex ? 'rotatingButton' : ''}`}
        >
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
               className="size-6">
            <path
              d="M15 6.75a.75.75 0 0 0-.75.75V18a.75.75 0 0 0 .75.75h.75a.75.75 0 0 0 .75-.75V7.5a.75.75 0 0 0-.75-.75H15ZM20.25 6.75a.75.75 0 0 0-.75.75V18c0 .414.336.75.75.75H21a.75.75 0 0 0 .75-.75V7.5a.75.75 0 0 0-.75-.75h-.75ZM5.055 7.06C3.805 6.347 2.25 7.25 2.25 8.69v8.122c0 1.44 1.555 2.343 2.805 1.628l7.108-4.061c1.26-.72 1.26-2.536 0-3.256L5.055 7.061Z"/>
          </svg>
        </div>
      </div>
    </div>
  )
}

export default VideoQuestionController;