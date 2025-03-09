import axios from "axios";

export const handleError = (error: Error | unknown): void => {

  if (axios.isAxiosError(error)) {
    alert(error.response?.data?.message || "서버와의 통신 중 에러가 발생했습니다.");
  } else if(error instanceof Error) {
    alert("예기치 못한 에러가 발생했습니다.");
  }
}

export const handleErrorWithCustomAlert = (error: Error | unknown, showAlert: (msg: string, duration?: number) => void): void => {
  if (axios.isAxiosError(error)) {
    showAlert(error.response?.data?.message || "서버와의 통신 중 에러가 발생했습니다.")
  } else if (error instanceof Error) {
    showAlert("예기치 못한 에러가 발생했습니다.")
  }
}