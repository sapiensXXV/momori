package com.momori.global.util;

import com.momori.global.exception.ExceptionCode;
import com.momori.global.exception.S3Exception;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class S3Util {

    public String parseKeyFromUrl(String url) {
        try {
            URI uri = URI.create(url);
            String path = uri.getPath();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            return path;
        } catch (Exception e) {
            throw new S3Exception(ExceptionCode.INVALID_S3_OBJECT_KEY);
        }
    }

}
