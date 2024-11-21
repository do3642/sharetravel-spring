package com.mbc.sharetravel_spring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    private final String uploadDir = "src/main/resources/static/travelBdImg/";

    @PostMapping
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir + fileName);
        file.transferTo(dest);

        Map<String, String> response = new HashMap<>();
        response.put("url", "/travelBdImg/" + fileName);
        return response;
    }
}