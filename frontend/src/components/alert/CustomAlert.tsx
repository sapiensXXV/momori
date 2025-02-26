import {FC, useEffect, useState} from "react";
import {AnimationState} from "./animation_state.ts";
import {alertStyles} from "./alert.styles.ts";

type CustomAlertProps = {
  message: string;
  duration?: number;
  isVisible: boolean;
  onClose: () => void;
}

const CustomAlert: FC<CustomAlertProps> = ({ message, duration=3000, isVisible, onClose }) => {

  const [animationState, setAnimationState] = useState<AnimationState>(AnimationState.ENTERING);

  useEffect(() => {
    let timeoutId: NodeJS.Timeout;
    if (isVisible) {
      setAnimationState(AnimationState.ENTERING);
      timeoutId = setTimeout(() => {
        setAnimationState(AnimationState.VISIBLE);
        timeoutId = setTimeout(() => {
          setAnimationState(AnimationState.EXITING);
          timeoutId = setTimeout(() => {
            onClose();
            setAnimationState(AnimationState.HIDDEN);
          }, 500) // 퇴장 애니메이션 시간
        }, duration)
      }, 500) // 등장 애니메이션 시간
    }

    return (() => {
      if (timeoutId) clearTimeout(timeoutId);
    })
  }, [isVisible, duration, onClose]);

  const handleClose = () => {
    setAnimationState(AnimationState.EXITING);
    // 퇴장 애니메이션 실행 후 onClose 호출
    setTimeout(() => {
      setTimeout(() => {
        onClose();
        setAnimationState(AnimationState.HIDDEN);
      }, 500)
    })
  }

  const getAlertStyle = () => {
    switch (animationState) {
      case AnimationState.ENTERING:
        return {...alertStyles.alertBox, ...alertStyles.entering};
      case AnimationState.VISIBLE:
        return {...alertStyles.alertBox, ...alertStyles.visible};
      case AnimationState.EXITING:
        return {...alertStyles.alertBox, ...alertStyles.exiting};
      case AnimationState.HIDDEN:
      default:
        return {...alertStyles.alertBox, ...alertStyles.hidden};
    }
  }

  return (
    <>
      {
        (!isVisible && animationState === AnimationState.HIDDEN) ? null :
          <div style={alertStyles.alertContainer}>
            <div style={getAlertStyle()}>
              <div style={alertStyles.message}>{message}</div>
              <button
                onClick={handleClose}
                style={alertStyles.closeButton}
                aria-label="Close"
              >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12"/>
                </svg>
              </button>
            </div>
          </div>
      }
    </>
  )
}

export default CustomAlert;