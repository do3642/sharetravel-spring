package com.mbc.sharetravel_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbc.sharetravel_spring.domain.Product;
import com.mbc.sharetravel_spring.repository.MarketRepository;

@Service
public class MarketService {
	
	@Autowired
	private MarketRepository marketRepository;
	
	public List<Product> getProductList() {
		return marketRepository.findAllByOrderByIdDesc();
	}
	
}
