package com.mbc.sharetravel_spring.dto;



import org.springframework.data.domain.Page;

import com.mbc.sharetravel_spring.posts.TravelBoard;

import lombok.Getter;

@Getter
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private Page<TravelBoard> page;
	private int totalPages;
	private long totalElements;
	
	
	public PageDTO(Page<TravelBoard> page) {
		this.page = page;
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		
		int currentPage = page.getNumber()+1;
		
		this.endPage = (int) (Math.ceil(currentPage/5.0)*5);
		this.startPage = endPage - 4;
		
		if(totalPages < endPage) {
			endPage = totalPages;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.totalPages;
		
		
	}
	
}