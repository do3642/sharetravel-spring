package com.mbc.sharetravel_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mbc.sharetravel_spring.MemberRepository;
import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.domain.RoleType;

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
	
}
