package com.mbc.sharetravel_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.posts.TravelBoard;
import com.mbc.sharetravel_spring.repository.MemberRepository;
import com.mbc.sharetravel_spring.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	
	//여행 정보 게시물 등록
	public TravelBoard travelPosting(TravelBoard post) {
		
		Member member =post.getMember();
		// 등록 시 내 게시물 수 증가
		member.setPostCount(member.getPostCount()+1);
		memberRepository.save(member);
		
		return postRepository.save(post);
	}
	
	
//	//여행 정보 게시물 리스트 끊어호출
//	@Transactional(readOnly = true)
//	public Page<TravelBoard> getTravelList(Pageable pageable){
//		return postRepository.findAll(pageable);
//	}
	@Transactional(readOnly = true)
	public List<TravelBoard> getTravelList() {
	    return postRepository.findAll();
	}


	
	// 게시물 수정
	public TravelBoard modifyTravelBoard(Integer id) {
		
		TravelBoard post = postRepository.findById(id).get();
		
		return post;
	}
	
	public TravelBoard travelModifyPosting(TravelBoard prevPost,TravelBoard post) {
		prevPost.setCategory(post.getCategory());
		prevPost.setLocation(post.getLocation());
		prevPost.setTitle(post.getTitle());
		prevPost.setContent(post.getContent());
		return postRepository.save(prevPost);
	}
	
	
	// 게시물 삭제
	public void deleteTravelBoard(Integer id) {
		postRepository.deleteById(id);
	}
	
	


	//조회수 증가
	public void incrementViewCount(Integer postId) {
	    // 게시물 조회
	    TravelBoard post = postRepository.findById(postId)
	        .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다"));

	    post.setViewCount(post.getViewCount() + 1);
	    
	    postRepository.save(post); // 저장
	}
	
	//추천 증가
	public void recommendationCount(Integer postId) {
	    // 게시물 조회
	    TravelBoard post = postRepository.findById(postId)
	        .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다"));

	    post.setRecommendationCount(post.getRecommendationCount() + 1);
	    
	    postRepository.save(post); // 저장
	}
	
}
