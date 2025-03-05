import axios from "axios";

export const handleError = (error: Error | unknown): void => {

  if (axios.isAxiosError(error)) {
    alert(error.response?.data?.message || "서버와의 통신 중 에러가 발생하였습니다.");
  } else if(error instanceof Error) {
    alert("예기치 못한 에러가 발생하였습니다.");
  }
}