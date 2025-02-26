export const tagColor: Record<number, string> = {
  [0]: "#2E8FE5",
  [1]: "#FC4870",
  [2]: "#40B5B3",
  [3]: "#FE8C32",
  [4]: "#8646FE",
  [5]: "#FEC345",
  [6]: "#BDBFC4",
}

export const getRandomTagColor = (input: string): string => {
  let hash = 0;
  for (let i = 0; i < input.length; i++) {
    hash = (hash * 31 + input.charCodeAt(i)) % 7;
  }
  return tagColor[hash];
}