package com.mbc.sharetravel_spring.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.posts.TravelBoard;
import com.mbc.sharetravel_spring.repository.PostRepository;
import com.mbc.sharetravel_spring.service.MemberService;
import com.mbc.sharetravel_spring.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostService postService;
    
    @Autowired
    private MemberService memberService;

    //게시물 등록 주소
    @PostMapping("/travelBoard/posts")
    public ResponseEntity<TravelBoard> createPost(@RequestBody TravelBoard post) {
    	System.out.println(post);
    	
    	postService.travelPosting(post);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // 이미지 이름 등록한 이미지로 바꿔주고 서버에 저장하는 맵핑
    @PostMapping("/test/img")
    public ResponseEntity<?> testImg(@RequestParam("image") MultipartFile file) throws Exception{
    	String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("travelimg/", fileName);
       Files.createDirectories(path.getParent());
       Files.write(path, file.getBytes());

       return new ResponseEntity<>("/travelimg/"+fileName, HttpStatus.OK);
    }
    
    
    // pageable방식
//    @GetMapping("/travel-board")
//    public Page<TravelBoard> getTravelList(@PageableDefault(size=15,sort="id",direction = Direction.DESC) Pageable pageable) {
//    	
//    	Page<TravelBoard> postList = postService.getTravelList(pageable);
//    	
//    	return postList;
//    }
//    
    
    // 요청 시 travelBoard 전체 게시물 리턴
    @GetMapping("/travel-board")
    public List<TravelBoard> getTravelList() {
        List<TravelBoard> postList = postService.getTravelList();
        return postList;
    }
    
    
    // 게시물 수정
    @GetMapping("/travel-board/write/{id}")
    public TravelBoard getModifyTravelBoard(@PathVariable Integer id) {
      
    	TravelBoard post = postService.modifyTravelBoard(id);
    	
    	return post;
       
    }
    
    @PutMapping("/travel-board/write/{id}")
    public ResponseEntity<TravelBoard> modifyPost(@RequestBody TravelBoard post, @PathVariable Integer id) {
  
    	TravelBoard prevPost = postService.modifyTravelBoard(id);
    	System.out.println(prevPost);
    	System.out.println(post);
    	postService.travelModifyPosting(prevPost,post);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
    // 게시물 삭제
    @DeleteMapping("/travel-board/{id}")
    public ResponseEntity<String> deleteTravelBoard(@PathVariable Integer id) {
    	postService.deleteTravelBoard(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    	
    }

    
    // 조회수
    @PostMapping("/travel-board/{postId}/increment-view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable Integer postId) {
        // 조회한 게시물 목록을 가져옴
    	postService.incrementViewCount(postId);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/travel-board/{postId}/like")
    public ResponseEntity<Void> recommendationCount(@PathVariable Integer postId) {
        // 조회한 게시물 목록을 가져옴
    	System.out.println("좋아요테스트");
    	postService.recommendationCount(postId);
    	return new ResponseEntity<>(HttpStatus.OK);
    }




}
