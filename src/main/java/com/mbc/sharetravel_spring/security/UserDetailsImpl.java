package com.mbc.sharetravel_spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mbc.sharetravel_spring.domain.Member;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails{
	private Member member;
	
	public UserDetailsImpl(Member member) {
		this.member = member;
	}

	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(() -> {
			return "ROLE_" + member.getRole();
			// 인증한 사용자가 가진 권한을 리턴시키는 메서드
		});
		
		return roleList;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
}