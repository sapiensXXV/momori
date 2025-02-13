export function percent(total: number, value: number): number {
  if (total === 0) {
    return 0;
  }
  // console.log(`percent(${total}, ${value}) = ${Math.floor((value / total) * 100)}`);
  return Math.floor((value / total) * 100);
}

/**
 * userScore: 사용자의 점수 (예: 75)
 * distribution: 각 bin(5점 단위)에 해당하는 사용자 수 배열 (예: [count0, count1, ..., countN])
 */
export function calculatePercentile(userScore: number, distribution: number[]): number {
  // 전체 사용자 수 계산
  const totalCount = distribution.reduce((sum, count) => sum + count, 0);

  // 사용자가 속한 bin의 인덱스 계산 (0~4점 → 0, 5~9점 → 1, …)
  let userBinIndex: number = Math.floor(userScore / 10);
  if (userScore === 100) {
    userBinIndex = 9;
  }

  // userScore가 distribution 범위를 벗어나면 에러 처리
  if (userBinIndex >= distribution.length) {
    throw new Error("User score exceeds distribution bins.");
  }

  // 사용자보다 높은 bin(더 높은 점수를 가진)의 사용자 수 합산
  const higherCount = distribution.slice(userBinIndex + 1)
    .reduce((sum, count) => sum + count, 0);

  // 현재 bin 내 사용자 수
  const currentBinCount = distribution[userBinIndex];

  // 현재 bin 내에서 사용자의 평균 위치를 추정 (전체의 중간)
  const estimatedUserRankWithinBin = currentBinCount / 2;

  // 전체 순위에서 사용자의 예상 순위
  const estimatedRank = higherCount + estimatedUserRankWithinBin;

  // 상위 퍼센트 = (전체 사용자 수 - 예상 순위) / 전체 사용자 수 * 100
  const percentile = ((totalCount - estimatedRank) / totalCount) * 100;

  return 100 - Math.round(percentile);
}

// // 예시 데이터:
// const distribution: number[] = [10, 15, 20, 25, 30, 20, 15, 10, 5, 2];
// // 예를 들어, 사용자의 점수가 75점이면
// const userScore = 75;
// const userPercentile = calculatePercentile(userScore, distribution);
// console.log(`사용자는 상위 ${userPercentile}% 안에 속합니다.`);
