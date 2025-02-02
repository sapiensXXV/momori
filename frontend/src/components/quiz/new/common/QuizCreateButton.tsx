import classes from './QuizCreateButton.module.css'

const QuizCreateButton = () => {
  return (
    <>
      <button className={`common-button ${classes.quizCreateButton}`}>
        등록
      </button>
    </>
  )
}

export default QuizCreateButton;