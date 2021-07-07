package com.web.maven.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContentCtr {
	@Autowired
	private ContentSvc ContentSvc;

	// 게시판 선택 후 목록 조회
	@RequestMapping(value = "/board/list")
	public String getList(HttpServletRequest req, SearchVO sv, ModelMap modelMap) {

		log.info("p :" + sv.getP());
		log.info("f :" + sv.getF());
		log.info("q :" + sv.getQ());
		log.info("b :" + sv.getBoardid());

		// 세션값 불러오기
		String userID = req.getSession().getAttribute("userID").toString();
		String userName = req.getSession().getAttribute("userName").toString();
		String userRank = req.getSession().getAttribute("userRank").toString();

		List<?> list = ContentSvc.getList(sv);

		modelMap.addAttribute("sv", sv);
		modelMap.addAttribute("list", list);
		modelMap.addAttribute("userRank", userRank);

		return "content/ContentList";

	}

	@RequestMapping(value = "/detail")
	public String getDetail(HttpServletRequest req, ModelMap modelMap) {

		int id = Integer.parseInt(req.getParameter("id"));

		ContentVO cv = ContentSvc.getDetailList(id);

		modelMap.addAttribute("nt", cv);
		return "content/Detail";

	}

	// 헤더에서 검색 조회한 결과
	@RequestMapping(value = "/board/allcontent")
	public String getAllList(HttpServletRequest req, SearchVO sv, ModelMap modelMap) {

		log.info("p :" + sv.getP());
		log.info("f :" + sv.getF());
		log.info("q :" + sv.getQ());
		log.info("b :" + sv.getBoardid());

		// 세션값 불러오기
		String userID = req.getSession().getAttribute("userID").toString();
		String userName = req.getSession().getAttribute("userName").toString();
		String userRank = req.getSession().getAttribute("userRank").toString();

		log.info("userID" + userID);
		log.info("userRank" + userRank);
		log.info("userName" + userName);

		List<?> list = ContentSvc.getList(sv);

		modelMap.addAttribute("sv", sv);
		modelMap.addAttribute("list", list);
		return "content/AllContentList";

	}

	// 글쓰기 form
	@RequestMapping(value = "/board/regedit")
	public String regeditPage(HttpServletRequest req, ModelMap modelMap) {

		// 세션값 불러오기
		String userID = req.getSession().getAttribute("userID").toString();
		String userName = req.getSession().getAttribute("userName").toString();
		String userRank = req.getSession().getAttribute("userRank").toString();

		log.info("userID" + userID);
		log.info("userRank" + userRank);
		log.info("userName" + userName);

		// 내가 글을 쓸 수 있는 게시판 목록 조회
		List<ContentVO> boardlist = ContentSvc.getMyBoard(userRank);

		// jsp 로보내기 (보내는것, 값 넣는거)
		modelMap.addAttribute("userID", userID);
		modelMap.addAttribute("boardlist", boardlist);
		modelMap.addAttribute("userRank", userRank);
		return "content/regedit";

	}

	// 글쓰기 저장
	@RequestMapping(value = "/board/regeditSave")
	public String regeditSave(HttpServletRequest req, ContentVO cv, ModelMap modelMap) {

		log.info("boardid :" + cv.getBoardid());
		log.info("writer_id" + cv.getWriter_id());
		log.info("content " + cv.getContent());

		ContentSvc.regeditSave(cv);

		return "redirect:/board/list";

	}

	// 내용 수정
	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest req, ModelMap modelMap) {

		log.info("modify 들어옴");
		int id = Integer.parseInt(req.getParameter("id"));

		ContentVO list = ContentSvc.getDetailList(id);

		modelMap.addAttribute("nt", list);

		return "content/modify";

	}

	// 내용 수정
	@RequestMapping(value = "modifyUpdate")
	public String modifyUpdate(HttpServletRequest req, ContentVO cv, ModelMap modelMap) {

		log.info("수정 들어옴");
		log.info("title:" + cv.getTitle());
		log.info("content" + cv.getContent());
		log.info("writer_id" + cv.getWriter_id());
		log.info("id" + cv.getId());

		ContentSvc.modifyUpdate(cv);

		modelMap.addAttribute("nt", cv);
		return "redirect:/board/list";

	}

	// 게시판 생성
	@RequestMapping(value = "/createBoard")
	public String createBoard(HttpServletRequest req, ModelMap modelMap) {

		// 세션값 불러오기
		String userID = req.getSession().getAttribute("userID").toString();
		String userName = req.getSession().getAttribute("userName").toString();
		String userRank = req.getSession().getAttribute("userRank").toString();

		log.info("userID" + userID);
		log.info("userRank" + userRank);
		log.info("userName" + userName);

		// 내가 글을 쓸 수 있는 게시판 목록 조회
		List<ContentVO> boardlist = ContentSvc.getMyBoard(userRank);

		// jsp 로보내기 (보내는것, 값 넣는거)
		modelMap.addAttribute("userID", userID);
		modelMap.addAttribute("boardlist", boardlist);
		modelMap.addAttribute("userRank", userRank);

		
		

		// 게시판 이름 가져오기
		List<ContentVO> boardnamelist = ContentSvc.getboardName();

		modelMap.addAttribute("list", boardnamelist);

		
		return "etc/createBoard";

	}

	// 새로운 게시판 이름 생성하러 가기 페이지 이동
	@RequestMapping(value = "/addBoard")
	public String addBoard(HttpServletRequest req, ContentVO cv, ModelMap modelMap) {


		String boardname=req.getParameter("boardname");
		
		ContentSvc.createBoardList(boardname);

		return "etc/addBoard";

	}


	
	
//게시판 이름 누르면 디테일로 보내기
	@RequestMapping(value = "/createBoardDetail")
	public String createBoardDetail(HttpServletRequest req, ModelMap modelMap) {
		log.info("createBoardDetail 들어옴");
		int id=Integer.parseInt(req.getParameter("id"));

		ContentVO list = ContentSvc.createBoardDetail(id);

		modelMap.addAttribute("list", list);

	
		return "etc/createBoardDetail";

	}
	//게시판 이름 수정
	
	@RequestMapping(value = "/BoardNameUpdate")
	public String BoardNameUpdate(HttpServletRequest req,boardVO sv,  ModelMap modelMap) {
		
		log.info(sv.getBoardname());
		log.info(sv.getBoardid());
		log.info("게시판 명 수정 들어옴");


		ContentSvc.BoardNamemodifyUpdate(sv);

		modelMap.addAttribute("list", sv);
		return "redirect:/board/list";

	}
	
	
	

}
