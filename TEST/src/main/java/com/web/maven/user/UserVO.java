package com.web.maven.user;


import java.util.Date;

import lombok.Data;


@Data
public class UserVO  {
	private String id;
	private String pass;
	private String name;
	private String email;
	private Date regdate;
	private String urank;
	private int number;
	

}
