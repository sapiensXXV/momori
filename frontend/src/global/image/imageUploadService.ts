import {compressImage} from "../util/image/ImageCompress.tsx";
import {axiosJwtInstance} from "../configuration/axios.ts";

// compressImage, axiosJwtInstance, handleError 등의 함수/객체는
// 해당 모듈 또는 유틸 파일에서 가져온다고 가정합니다.
export async function uploadQuizImage(
  file: File,
  prevImageUrl: string
): Promise<string> {
  // 1. 이미지 압축
  const compressedFile = await compressImage(file);

  // 2. FormData 구성
  const formData = new FormData();
  formData.append("image", compressedFile);
  formData.append("prevImageUrl", prevImageUrl);

  // 3. 서버에 이미지 전송
  const response = await axiosJwtInstance.post<{ imageUrl: string }>(
    `/api/quiz/draft/image`,
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );

  // 4. 업로드된 이미지 URL 반환
  return response.data.imageUrl;
}
