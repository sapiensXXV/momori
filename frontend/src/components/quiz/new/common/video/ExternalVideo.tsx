import {FC} from "react";
import YouTube, {YouTubeEvent} from "react-youtube";
import classes from "./ExternalVideo.module.css";

type ExternalVideoProps = {
  videoId: string;
  startTime: number;
  playDuration: number;
  onReady?: (event: YouTubeEvent) => void;
  onStateChange?: (event: YouTubeEvent<number>) => void;
}

const ExternalVideo: FC<ExternalVideoProps> = ({
  videoId,
  startTime,
  playDuration,
  onReady,
  onStateChange
}) => {
  return (
    <div className={classes.hiddenYoutubeContainer}>
      <YouTube
        videoId={videoId}
        opts={{
          width: "100%",
          playerVars: {
            autoplay: 0,
            rel: 0,
            start: startTime,
            ...(playDuration !== undefined && {
              end: startTime + playDuration
            })
          },
          volume: 0.5
        }}
        onReady={onReady}
        onStateChange={onStateChange}
      />
    </div>
  )
}

export default ExternalVideo;