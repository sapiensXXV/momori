import classes from './AudioUploadModal.module.css'
import AudioUploadModalHeader from "./AudioUploadModalHeader.tsx";
import React, {FC, useEffect, useRef, useState} from "react";
import {getYoutubeVideoUrl} from "../../../../../global/util/videoUtil.ts";
import YouTube from "react-youtube";
import {checkIsNumber} from "../../../../../global/util/number.ts";

type AudioUploadModalProps = {
  setShowModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const AudioUploadModal: FC<AudioUploadModalProps> = ({ setShowModal }) => {

  const inputRef = useRef<HTMLInputElement>(null);
  const [videoUrl, setVideoUrl] = useState<string>("");
  const [videoId, setVideoId] = useState<string>("");
  const [startSeconds, setStartSeconds] = useState<number>(0);
  const [startMinutes, setStartMinutes] = useState<number>(0);
  const [playDuration, setPlayDuration] = useState<number | undefined>(undefined);

  useEffect(() => {
    inputRef.current?.focus();
  }, []);

  const submitUrl = () => {
    const videoId = getYoutubeVideoUrl(videoUrl);
    if (videoId) {
      setVideoId(videoId);
    } else {
      alert('유튜브 링크가 유효하지 않습니다.')
    }
  }

  const videoOK = () => {
    console.log('videook');
    // TODO: 부모 컴포넌트에 전달해야하는 내용은 무엇인가?
    // 비디오 ID, 시작시간(초, startSeconds + startsMinutes * 60), 지속시간(playDuration)
  }

  // 시작시간 메서드

  const changeStartSecond = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value: string = e.target.value;
    if (value === undefined || value === null || value === "") { // NaN 값이 되지 않도록 방지
      setStartSeconds(0);
      return;
    }

    if (!checkIsNumber(value) || parseInt(value) >= 60) return; // 숫자가 아니고, 60을 넘었다면 반영하지 않음.
    setStartSeconds(parseInt(value));
  }

  const changeStartMinute = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value: string = e.target.value;
    if (value === undefined || value === null || value === "") { // NaN 값이 되지 않도록 방지
      setStartMinutes(0);
      return;
    }

    if (!checkIsNumber(value)) return; // 숫자가 아니라면 반영하지 않음.
    setStartMinutes(parseInt(value));
  }

  const calculateStartTime = (): number => {
    return startMinutes * 60 + startSeconds;
  }

  // 지속시간 메서드

  const changePlayDuration = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value: string = e.target.value;
    if (value === undefined || value === null || value === "") { // NaN 값이 되지 않도록 방지
      setPlayDuration(undefined);
      return;
    }

    if (!checkIsNumber(e.target.value)) return; // 숫자가 아니라면 반영하지 않음.
    setPlayDuration(parseInt(e.target.value));
  }

  return (
    <>
      <main className={classes.modalLayer}>
        <div className={classes.modalContainer}>
          <AudioUploadModalHeader setShowModal={setShowModal}/>
          <div className={classes.divider}></div>
          <div className={classes.linkContainer}>
            <input
              className={`common-input-sm ${classes.linkInput}`}
              type={"text"}
              placeholder={"링크를 입력하세요"}
              ref={inputRef}
              value={videoUrl}
              onChange={(e) => setVideoUrl(e.target.value)}
            />
            <div
              className={`common-button ${classes.linkButton}`}
              onClick={submitUrl}
            >등록</div>
          </div>
        {/* 유튜브 표시 부분 */}
          <div className={classes.videoContainer}>
            {
              videoUrl &&
              <YouTube
                videoId={videoId}
                opts={{
                  width: "100%",
                  playerVars: {
                    autoplay: 0, // 1: 자동재생, 0: 자동재생 하지 않음.
                    rel: 0, //관련 동영상 표시하지 않음
                    // modestbranding: 1, // 컨트롤 바에 youtube 로고를 표시하지 않음
                    start: calculateStartTime(), // 시작 시간
                    ...(playDuration !== undefined && { end: calculateStartTime() + playDuration }) // 종료 시간
                  },
                  volume: 0.5
                }}
              />
            }
          </div>
          <div className={classes.timeEditorContainer}>
            <div className={classes.startTimeContainer}>
              <span className={classes.timeText}>시작 시간: </span>
              <input
                className={`common-input-sm ${classes.timeInput}`}
                type={"text"}
                value={startMinutes}
                onChange={(e) => changeStartMinute(e)}
              />
              <span className={classes.timeText}>분</span>
              <input
                className={`common-input-sm ${classes.timeInput}`}
                type={"text"}
                value={startSeconds}
                onChange={(e) => changeStartSecond(e)}
              />
              <span className={classes.timeText}>초</span>
            </div>
            <div className={classes.verticalDivider}></div>
            <div className={classes.playDurationContainer}>
              <span className={classes.timeText}>지속 시간: </span>
              <input
                className={`common-input-sm ${classes.timeInput}`}
                type={"text"}
                onChange={(e) => changePlayDuration(e)}
              />
              <span className={classes.timeText}>초</span>
            </div>
          </div>
          <div
            className={`common-button ${classes.okButton}`}
            onClick={videoOK}
          >확인</div>
        </div>
      </main>
    </>
  )
}

export default AudioUploadModal;