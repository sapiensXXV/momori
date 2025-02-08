/**
 * 주어진 배열에서 랜덤하게 count 개의 요소를 선택하여 반환하는 함수
 * @param array 원본 배열
 * @param count 선택할 요소의 개수
 * @returns 선택된 요소들의 배열
 */
export function getRandomElements<T>(array: T[], count: number): T[] {
  // 배열을 복사해서 원본을 보존
  const result = [...array];
  // 배열을 랜덤하게 섞음
  for (let i = result.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [result[i], result[j]] = [result[j], result[i]];
  }
  // 섞인 배열에서 상위 count 개의 요소를 잘라서 반환
  return result.slice(0, count);
}
