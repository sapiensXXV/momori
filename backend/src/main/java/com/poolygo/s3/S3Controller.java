package com.poolygo.s3;


import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class S3Controller {
    private final S3ImageService s3ImageService;

    @PostMapping(
        value = "/quiz/draft/image",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageUrlDto> tempQuiz(
        @RequestParam("image") MultipartFile file,
        @RequestParam("prevImageUrl") String prevImageUrl
    ) throws IOException {
        log.info("파일 사이즈={}KB", file.getSize() / 1024);
        log.info("파일 이름={}", file.getOriginalFilename());
        log.info("파일 타입={}", file.getContentType());
        log.info("이전 이미지 URL={}", prevImageUrl);
        try {
            s3ImageService.deleteObject(prevImageUrl);
            String fileUrl = s3ImageService.saveImage(file);
            return ResponseEntity.ok().body(new ImageUrlDto(fileUrl));
        } catch (AmazonS3Exception e) {
            log.info("s3 exception={}", e.getMessage());
        }
        return null;
    }

}
