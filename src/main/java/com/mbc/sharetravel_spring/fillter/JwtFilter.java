package com.mbc.sharetravel_spring.fillter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mbc.sharetravel_spring.service.JwtService;



@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtSerivce;

	@Override // 요청 받았을때 실행되는 메서드
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(jwt != null) {
			String username = jwtSerivce.getAuthUser(request);
			
			Authentication auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
	
}