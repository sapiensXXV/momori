package com.poolygo.s3;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/quiz/image")
    public ResponseEntity<ImageUrlDto> tempQuiz(
        @RequestParam("image") MultipartFile file
    ) throws IOException {
//        log.info("파일 사이즈={}KB", file.getSize() / 1024);
//        log.info("파일 이름={}", file.getOriginalFilename());
//        log.info("파일 타입={}", file.getContentType());
        String fileUrl = s3ImageService.save(file);
        return ResponseEntity.ok().body(new ImageUrlDto(fileUrl));
    }

}
