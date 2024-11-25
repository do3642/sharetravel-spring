package com.mbc.sharetravel_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbc.sharetravel_spring.domain.Product;

@Repository
public interface MarketRepository extends JpaRepository< Product, Integer >{
	List<Product> findAllByOrderByIdDesc();
}
