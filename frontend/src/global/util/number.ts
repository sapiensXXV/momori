// 파라미터로 전달받은 문자열이 숫자인지 확인하는 함수이다.
// 숫자라면 true, 숫자가 아니라면 false를 반환한다.
export const checkIsNumber = (value: string): boolean => {
  if (!/^\d*$/.test(value)) {
    return false; // 숫자가 아닌 문자가 입력된 경우 false를 반환한다.
  }
  return true;
}