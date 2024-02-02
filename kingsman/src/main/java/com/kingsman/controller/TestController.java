package com.kingsman.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class TestController {

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 저장할 디렉토리 경로 지정
            String uploadDir = "./uploads";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 파일을 서버에 저장
            String fileName = file.getOriginalFilename();
            File serverFile = new File(uploadDir + File.separator + fileName);
            FileOutputStream stream = new FileOutputStream(serverFile);
            stream.write(file.getBytes());
            stream.close();

            return "File uploaded successfully: " + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }
}

