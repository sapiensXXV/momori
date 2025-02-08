export function percent(total: number, value: number): number {
  if (total === 0) {
    return 0;
  }
  return Math.floor((value / total) * 100);
}