package com.example.restservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/images")
public class ImageController{
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        try{
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            Path filePath = Paths.get(UPLOAD_DIR+file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "Image uploaded successfully: "+filePath.toString();
        }catch(IOException e){
            return "Image upload failed: "+e.getMessage();
        }
    }
}
