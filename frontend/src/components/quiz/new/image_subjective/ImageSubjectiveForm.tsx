import {FC} from "react";
import {ImageSubjectiveQuestion} from "../../../../types/question.ts";


type ImageSubjectiveFormProps = {
  questions: ImageSubjectiveQuestion[];
  setQuestions: (questions: ImageSubjectiveQuestion[]) => void;
}

const ImageSubjectiveForm: FC<ImageSubjectiveFormProps> = ({
  questions,
  setQuestions,
}) => {
  console.log(questions);
  console.log(setQuestions);
  return (
    <>
      이미지-주관식
    </>
  )
}

export default ImageSubjectiveForm;