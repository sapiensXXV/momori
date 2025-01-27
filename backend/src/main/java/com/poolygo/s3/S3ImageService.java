package com.poolygo.s3;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ImageService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String saveDraftImage(final MultipartFile file) throws IOException {
        // 고유 파일명 생성
        String fileName = "draft/quiz/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 메타데이터 설정 image/webp
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/webp");
        metadata.setContentLength(file.getSize());

        // S3에 업로드
        amazonS3.putObject(
            bucket,
            fileName,
            file.getInputStream(),
            metadata
        );

        // 파일 URL 생성
        return amazonS3.getUrl(bucket, fileName).toString();
    }
}
