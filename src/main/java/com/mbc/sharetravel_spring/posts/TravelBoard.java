package com.mbc.sharetravel_spring.posts;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.mbc.sharetravel_spring.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TravelBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 게시글 고유번호
    
    @Column(nullable = false, length = 100)
    private String category; // 카테고리(국내,국외)
    
    @Column(nullable = false, length = 100)
    private String location; // 여행지
    
    @Column(nullable = false, length = 100)
    private String title; // 제목
    
    @CreationTimestamp
    private Timestamp createDate; // 생성 시간

    private Timestamp updateDate; // 수정 시간
    
    @ManyToOne
    private Member member; // 게시글 작성자 (유저 정보)
    
    @OneToMany(mappedBy = "travelBoard")
    private List<Comment> comments; // 해당 게시글에 달린 댓글들
    
    @Column(nullable = false)
    private int recommendationCount = 0; // 추천 수
    
    @Column(nullable = false)
    private int viewCount = 0; // 조회수
    
    @Lob
    private String content; // 내용
    
}
