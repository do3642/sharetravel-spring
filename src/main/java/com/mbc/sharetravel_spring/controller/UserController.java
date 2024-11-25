package com.mbc.sharetravel_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.domain.MemberCredentials;
import com.mbc.sharetravel_spring.service.JwtService;
import com.mbc.sharetravel_spring.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	// 회원가입
	@PostMapping("/User-Register")
	public ResponseEntity<?> userRegister(@RequestBody Member member) {
		memberService.insert(member);
		
		return new ResponseEntity<>("회원가입성공",HttpStatus.OK);
	}
	
	@PostMapping("/Company-Register")
	public ResponseEntity<?> companyRegister(@RequestBody Member member) {
		memberService.companyInsert(member);
		
		return new ResponseEntity<>("회원가입성공",HttpStatus.OK);
	}


	
	// 로그인
	@PostMapping("/login")						// credentials안에는 사용자가 로그인할때 입력한 아이디와 패스워드를 받는 변수
	public ResponseEntity<?> login(@RequestBody MemberCredentials credentials){
		UsernamePasswordAuthenticationToken creds =
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword());
		// 받아온 데이터와 비교시켜줄 매니저 호출
		Authentication auth = authenticationManager.authenticate(creds);
		
		String jwts = jwtService.getToken(auth.getName());
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		}
	
	
	
	@GetMapping("/user")
	public ResponseEntity<?> userInfo(Authentication auth) {
//		System.out.println(auth.getName());
		String username = auth.getName();
		Member member = memberService.getMember(username);
		return new ResponseEntity<>(member,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/test")
	public String test() {
		return "Hello React & Spring Boot!";
	}

		
}
