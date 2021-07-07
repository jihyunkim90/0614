package com.web.maven.content;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ContentSvc {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<ContentVO> getList(SearchVO sv) {
		if(sv.getF()==null || sv.getF()=="") {
			sv.setF("title");
			
		}
	
		if(sv.getP()==0) {
			sv.setP(0);
			
		}else {
			sv.setP((sv.getP()-1)*10);
		}
		
		if(sv.getQ()==null) {
			sv.setQ("");
			
		}
		
		if(sv.getBoardid()==0) {
			sv.setBoardid(1);
		}

		return sqlSession.selectList("selectGetList",sv);
	}
	
	//헤더 게시판 목록조회
	public List<ContentVO> getAllList(SearchVO sv) {
		
		log.info("field :" +sv.getF());
		log.info("qurry :" +sv.getQ());
		log.info("page :" +sv.getP());
		log.info("boardid :" +sv.getBoardid());
		log.info("userRank :" +sv.getRank());
		
		System.out.println("Q :"+sv.getQ());
		System.out.println("F :"+sv.getF());
		return sqlSession.selectList("selectAllList",sv);
	}
	
	//디테일 조회
	public ContentVO getDetailList( int id) {
		
		
		return sqlSession.selectOne("selectDetailList", id);
	}

//사용자 권한별 종류 게시판 조회
	public List<ContentVO> getMyBoard(String userRank) {
		
		return sqlSession.selectList("getMyBoard",userRank);
	}

	//글쓰기 내용 저장
	public void regeditSave(ContentVO cv) {
		sqlSession.insert("regeditSave",cv);
		
	}
//내용수정
	public void modifyUpdate(ContentVO cv) {
		log.info("2838238563786347285");
		sqlSession.update("modifyUpdate",cv);
		
	}
	//게시판 새로 만드는 틀
	public void crateBoard(ContentVO cv) {
	
		sqlSession.update("createBoard",cv);
		
	}
//게시판 글 생성
	public void createBoardList(String boardname) {
		
		sqlSession.update("createBoardList",boardname);
		
	}

 //게시판 이름 가져오기
	public List<ContentVO> getboardName() {

		return 	sqlSession.selectList("getboardName");
	}

//게시판 디테일에 이름 불러서 칸에 이름 넣기
	public ContentVO createBoardDetail(int id) {
		return sqlSession.selectOne("createBoardDeatil", id);
	}
//게시판 이름 수정
	public void BoardNamemodifyUpdate(boardVO sv) {
		sqlSession.update("BoardNamemodifyUpdate",sv);
		
	}



}
