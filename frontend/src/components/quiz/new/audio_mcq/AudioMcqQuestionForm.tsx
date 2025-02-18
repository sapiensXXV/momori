import classes from'./AudioMcqForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {NewAudioMcqQuestion} from "../../../../types/question.ts";
import {useState} from "react";
import AudioUploadModal from "../common/modal/AudioUploadModal.tsx";

const AudioMcqQuestionForm = () => {

  const { questions, setQuestions } = useQuizContext<NewAudioMcqQuestion>();
  const [ showAudioUploadModal, setShowAudioUploadModal ] = useState<boolean>(false);

  const deleteQuestion = (qi: number) => {
    setQuestions(prev =>
      prev.filter((_, index) => index !== qi)
    );
  }

  const addChoice = (index: number) => {

  }


  return(
    <>
      {
        showAudioUploadModal ? <AudioUploadModal setShowModal={setShowAudioUploadModal} /> : null
      }
      <main >

      </main>
    </>
  )
}

export default AudioMcqQuestionForm;