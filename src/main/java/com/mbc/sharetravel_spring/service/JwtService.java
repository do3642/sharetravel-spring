package com.mbc.sharetravel_spring.service;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	// 토큰만료 시간설정해둔 변수
	static final long EXPIRATIONTIME = 24 *60 *60 *1000;
	// 헤더에 사용할 접두어 설정
	static final String PREFIX = "Bearer ";
	// 암호화 키 생성 (HS256 알고리즘)
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	// 로그인 아이디를 이용해서 JWT토큰 생성 후 리턴시켜주는 메서드
	public String getToken(String username) {
		String token = Jwts.builder()
				.setSubject(username) // 보통 로그인한사람의 아이디,닉네임을 담아주긴함
//				.claim("email","유저이메일") // 이렇게 토큰안에 사용자 정보 추가 세팅할수있음
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact(); // 위에 생성된걸 문자열로 리턴시켜버림
		return token;
	}
	
	
	// 요청 객체에서 헤더에 Jwt를 추출하고 그안에 있는 username을 리턴
	public String getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(token != null) {
			String user = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX,""))		
					.getBody()
					.getSubject();
				if(user != null)
					return user;
			}
		return null;
	}
}
