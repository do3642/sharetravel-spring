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

    
    // 조회수 제한
    @PostMapping("/travel-board/{postId}/increment-view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable Integer postId, Authentication auth, HttpSession session) {
        // 세션에서 조회한 게시물 목록을 가져옴
        Set<Integer> viewedPosts = (Set<Integer>) session.getAttribute("viewedPosts");
        

        if (viewedPosts == null) {
            viewedPosts = new HashSet<>();
        }

        System.out.println("세션에서 조회한 게시물 목록: " + viewedPosts);  // 세션 상태 확인

        if (viewedPosts.contains(postId)) {
            return ResponseEntity.ok().build(); // 이미 본 게시물이라 조회수 증가 안함
        }

        // 로그인한 사용자 처리
        if (auth != null && auth.isAuthenticated()) {
            String username = (String) auth.getPrincipal();
            Member user = memberService.getMember(username);

            if (user != null) {
                Integer userId = user.getId();
                postService.incrementViewCount(postId, userId);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            // 비로그인 사용자의 경우, 조회수만 증가
            postService.incrementViewCountForGuest(postId);
        }

        // 비로그인 사용자의 경우 세션에 조회한 게시물 ID 저장
        viewedPosts.add(postId);
        session.setAttribute("viewedPosts", viewedPosts); // 세션에 저장

        System.out.println("세션에 저장된 게시물 목록: " + viewedPosts);
        return ResponseEntity.ok().build();
    }




}
