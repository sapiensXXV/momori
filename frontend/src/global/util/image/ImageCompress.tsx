import imageCompression from 'browser-image-compression';
import classes from "../../../components/quiz/new/common/QuestionImage.module.css";
import {ImageUploadStatus} from "../../../types/question.ts";

export interface CompressionOptions {
  maxSizeMB?: number;    // 최대 파일 크기 (MB)
  maxWidthOrHeight?: number; // 최대 해상도
  useWebWorker?: boolean; // 웹 워커 사용 여부
  fileType?: string;     // 변환할 파일 형식 (ex: 'image/webp')
}

export const compressImage = async (
  file: File,
  options?: CompressionOptions
): Promise<File> => {
  const defaultOptions: CompressionOptions = {
    maxSizeMB: 1,
    maxWidthOrHeight: 1920,
    useWebWorker: true,
    fileType: 'image/webp',
  };

  try {
    // 압축 실행
    const compressedFile = await imageCompression(
      file,
      { ...defaultOptions, ...options }
    );

    // 압축 후 크기 검증
    if (compressedFile.size > (options?.maxSizeMB ?? 1) * 1024 * 1024) {
      alert('파일을 충분히 압축하지 못했습니다.')
      throw new Error('파일을 충분히 압축하지 못했습니다');
    }
    return compressedFile;
  } catch (error) {
    alert('이미지 압축 실패')
    throw new Error(`이미지 압축 실패: ${error instanceof Error ? error.message : 'Unknown error'}`);
  }
};
