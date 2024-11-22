package com.mbc.sharetravel_spring.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String title; // 상품명
	
	@Column(nullable = false)
	private int price; // 가격
	
	@Lob
	@Column
	private String information; // 상품정보
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id") // 외래 키
    private List<Img> images;
	
	private int cnt; // 조회수
	
	@CreationTimestamp
	private Timestamp createDate; // 등록일
	
	@Column(name = "like_count")
	private int likeCount; // 찜 수
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "userid")
//	private Member member;
	private String writer;
	
	

}
