import {FC} from "react";
import YouTube, {YouTubeEvent} from "react-youtube";
import classes from "./ExternalVideo.module.css";

type ExternalVideoProps = {
  videoId: string;
  startTime: number;
  playDuration: number;
  onReady?: (event: YouTubeEvent) => void;
  onStateChange?: (event: YouTubeEvent<number>) => void;
  autoPlay: number;
}

const ExternalVideo: FC<ExternalVideoProps> = ({
  videoId,
  startTime,
  playDuration,
  onReady,
  onStateChange,
  autoPlay
}) => {
  return (
    <div className={classes.hiddenYoutubeContainer}>
      <YouTube
        videoId={videoId}
        opts={{
          width: "100%",
          playerVars: {
            autoplay: autoPlay, // 자동 재생
            rel: 0,
            start: startTime,
            ...(playDuration !== undefined && {
              end: startTime + playDuration
            })
          },
          volume: 1
        }}
        onReady={onReady}
        onStateChange={onStateChange}
      />
    </div>
  )
}

export default ExternalVideo;