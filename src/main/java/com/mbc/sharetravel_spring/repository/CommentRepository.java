package com.mbc.sharetravel_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbc.sharetravel_spring.posts.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByTravelBoardId(Integer postId);
	List<Comment> findByTravelBoardIdOrderByCreateDateDesc(Integer travelBoardId);

}
