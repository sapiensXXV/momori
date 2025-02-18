import classes from './AudioMcqForm.module.css'
import {useQuizContext} from "../../../../context/QuizContext.tsx";
import {AudioUploadStatus, NewAudioMcqQuestion} from "../../../../types/question.ts";
import React, {useState} from "react";
import AudioUploadModal from "../common/modal/AudioUploadModal.tsx";
import AddAudioMcqQuestionButton from "./AddAudioMcqQuestionButton.tsx";
import YouTube from "react-youtube";
import {QuizTypes} from "../../types/Quiz.types.ts";

const AudioMcqQuestionForm = () => {

  const {questions, setQuestions} = useQuizContext<NewAudioMcqQuestion>();
  const [showAudioUploadModal, setShowAudioUploadModal] = useState<boolean>(false);
  const [selectQuestionIndex, setSelectedQuestionIndex] = useState(0);

  const deleteQuestion = (qi: number) => {
    setQuestions(prev =>
      prev.filter((_, index) => index !== qi)
    );
  }

  const addChoice = (index: number) => {
    console.log('add choice')
  }

  const handleShowAudioUploadModal = (index: number) => {
    // index: question의 index
    setSelectedQuestionIndex(index);
    setShowAudioUploadModal(true);
  }

  // 문제의 오디오를 선택했을 때 호출되는 메서드
  const submitAudio = (
    audioId: string,
    startTime: number,
    playDuration: number | undefined,
    questionId: number
  ) => {

    setQuestions(prev => (
      prev.map((question, index) => (
        index !== questionId ? question : {
          audioStatus: AudioUploadStatus.UPLOADED,
          audioId: audioId,
          startTime: startTime,
          playDuration: playDuration,
          choices: question.choices
        }
      ))
    ))
  }

  const playAudio = (index: number) => {
    console.log(`play audio of question #${index + 1}`);
    setSelectedQuestionIndex(index);
  }

  console.log(questions);

  return (
    <>
      {
        showAudioUploadModal ? <AudioUploadModal
          quizType={QuizTypes.AUDIO_MCQ}
          setShowModal={setShowAudioUploadModal}
          questionIndex={selectQuestionIndex}
          submitAudio={submitAudio}
        /> : null
      }
      <main className={`${classes.questionContainer} common-flex-column`}>
        {
          questions?.map((question, qi) => (
            <React.Fragment>
              <hr className="common-hr"/>
              <div className={classes.question}>
                <div className={classes.deleteButtonContainer}>
                  <button
                    className={classes.quizDeleteButton}
                    onClick={() => deleteQuestion(qi)}
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" className="size-6">
                      <path strokeLinecap="round" strokeLinejoin="round"
                            d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"/>
                    </svg>
                  </button>
                </div>
                {/*영상 선택*/}
                <div className={classes.audioContentContainer}>
                  {/* 오디오 정보 설정 버튼 */}
                  <div className={classes.audioApplyButton} onClick={() => handleShowAudioUploadModal(qi)}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" className="size-6">
                      <path stroke-linecap="round" stroke-linejoin="round"
                            d="M19.114 5.636a9 9 0 0 1 0 12.728M16.463 8.288a5.25 5.25 0 0 1 0 7.424M6.75 8.25l4.72-4.72a.75.75 0 0 1 1.28.53v15.88a.75.75 0 0 1-1.28.53l-4.72-4.72H4.51c-.88 0-1.704-.507-1.938-1.354A9.009 9.009 0 0 1 2.25 12c0-.83.112-1.633.322-2.396C2.806 8.756 3.63 8.25 4.51 8.25H6.75Z"/>
                    </svg>
                  </div>
                  {/* 오디오 미리듣기 버튼 컨테이너 */}
                  <div className={classes.audioButtonContainer}>
                    {/* 재생/일시정지 버튼 */}
                    <div className={`${classes.audioButton} common-button`} onClick={() => playAudio(qi)}>
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
                           className="size-6">
                        <path
                          d="M15 6.75a.75.75 0 0 0-.75.75V18a.75.75 0 0 0 .75.75h.75a.75.75 0 0 0 .75-.75V7.5a.75.75 0 0 0-.75-.75H15ZM20.25 6.75a.75.75 0 0 0-.75.75V18c0 .414.336.75.75.75H21a.75.75 0 0 0 .75-.75V7.5a.75.75 0 0 0-.75-.75h-.75ZM5.055 7.06C3.805 6.347 2.25 7.25 2.25 8.69v8.122c0 1.44 1.555 2.343 2.805 1.628l7.108-4.061c1.26-.72 1.26-2.536 0-3.256L5.055 7.061Z"/>
                      </svg>
                    </div>

                  </div>
                </div>
                {/* 선택지 추가 버튼 */}
                {/* 선택지 */}
                <div>

                </div>
              </div>
            </React.Fragment>
          ))
        }
        <AddAudioMcqQuestionButton/>
        {/* 사용자에게는 보이지 않는 유튜브 재생 블록. 재생 버튼을 누르면 그 영상이 트리거되어 재생되어야한다. */}
        {/* 즉, 사용자가 재생 버튼을 클릭하면 selectQuestionIndex 값이 수정되어 로딩한 영상도 바뀌어야한다. */}
        {/* 선택한 영상의 오디오 준비 상태 audioStatus 값에 따라 재생여부, 버튼에 나타나는 애니메이션이 결정된다. */}

        <div className={classes.hiddenYoutubeContainer}>
          <YouTube
            videoId={questions[selectQuestionIndex]?.audioId}
            opts={{
              width: "100%",
              playerVars: {
                autoplay: 0, // 1: 자동재생, 0: 자동재생 하지 않음.
                rel: 0, //관련 동영상 표시하지 않음
                start: questions[selectQuestionIndex]?.startTime, // 시작 시간
                ...(questions[selectQuestionIndex]?.playDuration !== undefined &&
                  {end: questions[selectQuestionIndex]?.startTime + questions[selectQuestionIndex]?.playDuration} // 종료 시간
                )
              },
              volume: 0.5
            }}
          />
        </div>
      </main>
    </>
  )
}

export default AudioMcqQuestionForm;