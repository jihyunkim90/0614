package com.web.maven.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSvc {
	@Autowired
	private SqlSessionTemplate sqlSession;
	//로그인
	public UserVO LoginCheck(LoginVO lv) {
		return sqlSession.selectOne("LoginCheck", lv);
	}
//아이디중복체크
	public int idCheck(String id) {
		
		return sqlSession.selectOne("idCheck",id);
	}
//회원가입(사용자정보등록)
	public void registUser(UserVO uv) {
		
		
		 sqlSession.insert("registUser",uv);
	}
}
