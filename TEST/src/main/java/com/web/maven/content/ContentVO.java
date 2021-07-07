package com.web.maven.content;

import java.util.Date;


import lombok.Data;


@Data
public class ContentVO {
	private int boardid;
	private String boardname;
	private String board;
	private int id;
	private String title;
	private String writer_id;
	private String content;
	private Date regdate;
	private int hit;
	private int like;
	private int cocnt;
	private String files;
	private int count;
	

}
