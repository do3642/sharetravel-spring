package com.mbc.sharetravel_spring.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("USER")
public class User extends Member {

    // 일반회원에만 필요한 추가 속성 (예: 일반회원만 사용하는 필드)
   
}
