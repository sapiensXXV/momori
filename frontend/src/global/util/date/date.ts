export function formatDateTime(dateStr) {
  // 입력된 문자열을 Date 객체로 변환
  const inputDate = new Date(dateStr);
  const now = new Date();

  // 두 날짜의 차이를 밀리초 단위로 계산 (24시간 = 86,400,000ms)
  const oneDay = 24 * 60 * 60 * 1000;
  const diff = now - inputDate;

  // 숫자가 한 자리일 경우 앞에 0을 붙이는 헬퍼 함수
  function pad(num) {
    return num < 10 ? '0' + num : num;
  }

  // 시간 정보 추출 (24시간 형식)
  const hours = pad(inputDate.getHours());
  const minutes = pad(inputDate.getMinutes());

  // 24시간 미만인 경우 시간만 반환
  if (diff < oneDay) {
    return `${hours}:${minutes}`;
  } else {
    // 24시간 이상인 경우 날짜와 시간 반환
    const year = inputDate.getFullYear();
    // getMonth()는 0부터 시작하므로 1을 더해줍니다.
    const month = pad(inputDate.getMonth() + 1);
    const day = pad(inputDate.getDate());
    return `${year}.${month}.${day} ${hours}:${minutes}`;
  }
}