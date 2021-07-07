package com.web.maven.content;

import lombok.Data;

@Data
public class SearchVO {
	private int boardid; // 게시판종류
	private int p; // page
	private String f; // 검색조건
	private String q; // 검색어
	private String rank;

}
