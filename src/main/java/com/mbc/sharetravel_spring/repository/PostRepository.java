package com.mbc.sharetravel_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbc.sharetravel_spring.posts.TravelBoard;

public interface PostRepository extends JpaRepository<TravelBoard, Long> {

}
