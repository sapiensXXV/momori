import classes from './AudioUploadModal.module.css'
import AudioUploadModalCloseButton from "./AudioUploadModalCloseButton.tsx";
import {FC} from "react";

type AudioUploadModalHeaderProps = {
  setShowModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const AudioUploadModalHeader: FC<AudioUploadModalHeaderProps> = ({
  setShowModal
}) => {
  return (
    <>
      <main className={classes.headerLayer}>
        <span>링크 업로드</span>
        <AudioUploadModalCloseButton setShowModal={setShowModal}/>
      </main>
    </>
  )
}

export default AudioUploadModalHeader;