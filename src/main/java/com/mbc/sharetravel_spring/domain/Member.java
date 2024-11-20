package com.mbc.sharetravel_spring.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Member {
	
	@Id
	private String username;
	private String password;
	private String ssn;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
}
