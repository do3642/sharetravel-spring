package com.mbc.sharetravel_spring.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.domain.RoleType;
import com.mbc.sharetravel_spring.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입
	public void insert(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole(RoleType.USER);
		
		memberRepository.save(member);
	}
	// 회원가입
	public void companyInsert(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole(RoleType.COMPANY);
		
		memberRepository.save(member);
	}
	
	
	
	// auth의 아이디를 db에 대조해서 꺼낼때 사용
	public Member getMember(String username) {
	    Member member = memberRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Member not found"));
		
		return member;
	}
	
}
