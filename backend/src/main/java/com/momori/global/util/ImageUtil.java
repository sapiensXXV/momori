package com.momori.global.util;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImageUtil {

    public byte[] convertToWebp(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "webp", outputStream);
            return outputStream.toByteArray();
        }
    }

}
