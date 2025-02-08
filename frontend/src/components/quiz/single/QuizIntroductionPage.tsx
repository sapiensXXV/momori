import classes from "./QuizIntroductionPage.module.css"
import {FC} from "react";


type QuizIntroductionPageProps = {
  thumbnailUrl: string;
  title: string;
  description: string;
  questionCount: number;
  setQuestionCount: (value: number) => void;
}

const QuizIntroductionPage: FC<QuizIntroductionPageProps> = (
  {
    thumbnailUrl,
    title,
    description,
    questionCount,
    setQuestionCount
  }) => {

  const possibleCounts = [5, 10, 20, 30, 40, 50];
  // const allowCounts = possibleCounts.filter((count) => count <= questionCount);
  const allowCounts = [5, 10, 20, 30, 40];
  return (
    <>
      <main className={classes.introContainer}>
        <div className={classes.introContentContainer}>
          <div className={classes.introThumbnail}>
            <img src={thumbnailUrl}/>
          </div>
          <span className={classes.introTitle} >{title}</span>
          <span className={classes.introDescription} >{description}</span>
          <div className={classes.countButtonContainer}>
            {
              allowCounts.map((count) => (
                <div
                  key={`quiz_${title}_allow_${count}`}
                  className={`common-button ${classes.countButton}`}
                  onClick={() => setQuestionCount(count)}
                >{count}개 풀기</div>
              ))
            }
          </div>

        </div>

      </main>
    </>
  )
}

export default QuizIntroductionPage;