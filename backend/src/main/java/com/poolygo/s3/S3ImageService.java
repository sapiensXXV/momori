package com.poolygo.s3;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ImageService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String saveImage(final MultipartFile file) throws IOException {
        // 고유 파일명 생성
        String fileName = "draft/quiz/image/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

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

    public void deleteObject(final String url) {
        if (StringUtils.hasText(url)) {
            amazonS3.deleteObject(bucket, url);
        }
    }

    public String copyDraftToPermanent(final String draftUrl) {
        String permanentUrl = "permanent/quiz/image/" + UUID.randomUUID();
        copyObject(draftUrl, permanentUrl);
        return amazonS3.getUrl(bucket, permanentUrl).toString();
    }

    public void copyObject(final String from, final String to) {
        try {
            log.info("copying from: [{}], to: [{}]", from, to);
            CopyObjectRequest copyRequest = new CopyObjectRequest(bucket, from, bucket, to);
            amazonS3.copyObject(copyRequest);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            // 예외 처리
        } catch (SdkClientException e) {
            e.printStackTrace();
            // 예외 처리
        }
    }
}
