export const getYoutubeVideoUrl = (url: string): string | null => {
  // const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
  // const match = url.match(regExp);
  // return match && match[7].length === 11 ? match[7] : null;

  const regExp = /^.*((youtu\.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
  const match = url.match(regExp);
  // match[7]에 videoId가 추출되며, 일반적으로 videoId는 11자리입니다.
  return match && match[7].length === 11 ? match[7] : null;
}