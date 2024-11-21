package com.mbc.sharetravel_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
