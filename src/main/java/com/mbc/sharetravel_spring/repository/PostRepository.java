package com.mbc.sharetravel_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbc.sharetravel_spring.posts.TravelBoard;

public interface PostRepository extends JpaRepository<TravelBoard, Integer> {
	List<TravelBoard> findAll();
}
