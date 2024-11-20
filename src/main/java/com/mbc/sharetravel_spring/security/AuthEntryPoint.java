package com.mbc.sharetravel_spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
	//권한이 없는 사용자가 요청을 날렸을때 메세지 보낼 클래스
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 권한없음 상태코드
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write("로그인 후 이용해주세요.");
	}

	
}
