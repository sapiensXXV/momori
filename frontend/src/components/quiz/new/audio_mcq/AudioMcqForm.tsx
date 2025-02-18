import classes from'./AudioMcqForm.module.css'
import AudioMcqMetadataForm from "./AudioMcqMetadataForm.tsx";
import AudioMcqQuestionForm from "./AudioMcqQuestionForm.tsx";

export default function AudioMcqForm() {
  return (
    <>
      <AudioMcqMetadataForm/>
      <AudioMcqQuestionForm/>
    </>
  )
}