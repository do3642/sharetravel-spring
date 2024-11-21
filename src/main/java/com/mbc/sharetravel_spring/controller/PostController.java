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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/travelBoard/posts")
    public ResponseEntity<TravelBoard> createPost(@RequestBody TravelBoard post) {
    	System.out.println(post.getContent());
    	System.out.println(post);
        TravelBoard savedPost = postRepository.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
    
    @PostMapping("/test/img")
    public ResponseEntity<?> testImg(@RequestParam("image") MultipartFile file) throws Exception{
       String fileName = file.getOriginalFilename();
//       Path path = Paths.get("/travelBdImg", fileName);
//       Path path = Paths.get("travel_img", fileName);
       Path path = Paths.get("travelimg/", fileName);
       System.out.println(path.getParent());
       System.out.println("파일 저장 경로: " + path.toAbsolutePath());
       Files.createDirectories(path.getParent());
       Files.write(path, file.getBytes());

       return new ResponseEntity<>("/travelimg/"+fileName, HttpStatus.OK);
    }
}
