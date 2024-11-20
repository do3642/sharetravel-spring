package com.mbc.sharetravel_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mbc.sharetravel_spring.domain.Member;
import com.mbc.sharetravel_spring.repository.MemberRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Member principal = memberRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException(username + "은 없는 아이디입니다.");
				});
		return new UserDetailsImpl(principal);

	}
	
	
}
