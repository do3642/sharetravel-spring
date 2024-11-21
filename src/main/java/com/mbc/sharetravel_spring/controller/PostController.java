package com.mbc.sharetravel_spring.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbc.sharetravel_spring.posts.TravelBoard;
import com.mbc.sharetravel_spring.repository.PostRepository;
import com.mbc.sharetravel_spring.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/travelBoard/posts")
    public ResponseEntity<TravelBoard> createPost(@RequestBody TravelBoard post) {
    	System.out.println(post);
    	
    	postService.travelPosting(post);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/test/img")
    public ResponseEntity<?> testImg(@RequestParam("image") MultipartFile file) throws Exception{
       String fileName = file.getOriginalFilename();
       Path path = Paths.get("travelimg/", fileName);
       Files.createDirectories(path.getParent());
       Files.write(path, file.getBytes());

       return new ResponseEntity<>("/travelimg/"+fileName, HttpStatus.OK);
    }
}
