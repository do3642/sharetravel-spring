package com.mbc.sharetravel_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.domain.TestVO;
import com.mbc.sharetravel_spring.service.MemberService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private MemberService memberService;
	
	
	@PostMapping("/User-Register")
	public ResponseEntity<?> userRegister(@RequestBody Member member) {
		memberService.insert(member);
		
		return new ResponseEntity<>("회원가입성공",HttpStatus.OK);
	}
	
	@PostMapping("/Company-Register")
	public ResponseEntity<?> companyRegister(@RequestBody Member member) {
		memberService.insert(member);
		
		return new ResponseEntity<>("회원가입성공",HttpStatus.OK);
	}


	

		
}
