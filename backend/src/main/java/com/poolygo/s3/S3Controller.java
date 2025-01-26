package com.poolygo.s3;


import com.poolygo.global.infrastructure.aws.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class S3Controller {
    private final AwsProperties awsProperties;



}
