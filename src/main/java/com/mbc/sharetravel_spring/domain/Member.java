package com.mbc.sharetravel_spring.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type", discriminatorType = DiscriminatorType.STRING)
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, length = 40)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(unique = true, nullable = false, length = 40)
    private String nickname; // 사용자 닉네임 또는 기업 상호명

    @Column(nullable = false, length = 15)
    private String ssn; // 주민번호 앞자리 또는 사업자 번호

    @CreationTimestamp
    private Timestamp createDate;

    private int postCount = 0;
    private int commentCount = 0;
    
    @Transient // DB에 저장되지 않도록 설정
    private MultipartFile thumbnail; // 썸네일 이미지 (실제 파일 객체)

    @Column
    private String thumbnailFilename; // 저장된 파일명 (파일 시스템에 저장된 파일명)


    @Enumerated(EnumType.STRING)
    private RoleType role;
}


