package com.poolygo.quizdraft.presentation;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.poolygo.global.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Slf4j
public class QuizDraftController {

    private final AmazonS3 amazonS3;
    private final ImageUtil imageUtil;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("/draft/image-mcq")
    public ResponseEntity<?> tempQuiz(
        @RequestParam("image") MultipartFile file
    ) throws IOException {
        log.info("파일 사이즈={}KB", file.getSize() / 1024);
        log.info("파일 이름={}", file.getOriginalFilename());
        log.info("파일 타입={}", file.getContentType());
        // 고유 파일명 생성
        String fileName = "temp/quiz/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 메타데이터 설정 image/webp
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/webp");

        // S3에 업로드
        amazonS3.putObject(
            bucket,
            fileName,
            file.getInputStream(),
            metadata
        );

        // 파일 URL 생성
        String fileUrl = amazonS3.getUrl(bucket, fileName).toString();

        return ResponseEntity.ok().body(Map.of("imageUrl", fileUrl));
    }
}
