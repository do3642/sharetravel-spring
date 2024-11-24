package com.mbc.sharetravel_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
	private Integer travelBoardId;
    private Integer memberId;
    private String content;
}
