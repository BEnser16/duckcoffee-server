package com.duckcoffee.app.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/api/img")
@CrossOrigin("http://localhost:3000")
public class ImageController {

    private static final String imgDirectory = "../app/img";

    @PostMapping("/upload")
    public String ImageUpload(@RequestParam("file") MultipartFile imageFile) {
        try {
            // get file name
            String fileName = imageFile.getOriginalFilename();

            // store file path
            Path path = Paths.get(imgDirectory + File.separator + fileName);

            // 保存到 file system
            Files.write(path , imageFile.getBytes());

            System.out.println("/app/img" + fileName);

            // 返回文件的URL
            return "/app/img/" + fileName;

        } catch (IOException exception) {
            exception.printStackTrace();

            return "圖片上傳失敗！";
        }
    }

}
