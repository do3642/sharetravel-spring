package com.mbc.sharetravel_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbc.sharetravel_spring.domain.Member;



@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	Optional<Member> findByUsername(String username);

}
