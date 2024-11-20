package com.mbc.sharetravel_spring.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("COMPANY")
public class Company extends Member {

    // 기업회원에만 필요한 추가 속성 (예: 사업자번호와 관련된 필드)
//    @Column(nullable = false, length = 15)
//    private String businessNumber; // 사업자 번호
}
