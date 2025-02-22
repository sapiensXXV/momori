import classes from './AudioUploadModal.module.css'
import {FC} from "react";

type AudioUploadModalCloseButtonProps = {
  setShowModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const AudioUploadModalCloseButton: FC<AudioUploadModalCloseButtonProps> = ({ setShowModal }) => {

  const closeModal = () => {
    setShowModal(false);
  }

  return (
    <>
      <div className={classes.modalCloseButton} onClick={closeModal}>
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" className="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12"/>
        </svg>
      </div>
    </>
  )
}

export default AudioUploadModalCloseButton;