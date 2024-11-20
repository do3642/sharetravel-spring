package com.mbc.sharetravel_spring.domain;

import lombok.Data;

@Data
public class MemberCredentials {
	// Member객체 받을때 나머지 데이터 날리고 두개만 분리
	private String username;
	private String password;
}
