package com.web.maven.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginCtr {
	
	private static final Integer cookieExpire =60*60*24*30; ///1month
	
	@Autowired
	private UserSvc UserSvc;
	
	
	private static final Logger log= LoggerFactory.getLogger(LoginCtr.class);
	
	//메서드 호출 
	@RequestMapping(value="login")
	public String Login(HttpServletRequest req, ModelMap modelMap) {
		
		System.out.println("Login method 에 들어옴");
		
		
		
		String userid =get_cookie("sid", req);
		
		modelMap.addAttribute("id", userid);
		
		return "user/login";
		
	}
	
	//메서드 호출 
		@RequestMapping(value="/logout")
		public String Logout(HttpServletRequest req, ModelMap modelMap) {
			
			 HttpSession session =req.getSession();
			session.removeAttribute("userID");
			session.removeAttribute("userName");
			session.removeAttribute("userRank");
		
			
			return "redirect:/login";
			
		}
	
	@RequestMapping(value="LoginCheck")
	public String LoginCheck(HttpServletRequest req, HttpServletResponse resp, LoginVO lv,ModelMap modelMap) {
	 
		 UserVO uv=UserSvc.LoginCheck(lv);
		 
		 //조회 결과가 없다면
		 if(uv==null) {
			modelMap.addAttribute("ment","ID or pass 틀림");
			
			
			return "user/login";
			
		 }
		 
		 
		 log.info("id :", lv.getId());
		
		 
		 
		 //세션처리시작
		 HttpSession session =req.getSession();
		 //세션에 접속자 정보 입력
		 session.setAttribute("userID", uv.getId());
		 session.setAttribute("userRank", uv.getUrank());
		 session.setAttribute("userName", uv.getName());
		
		 
		 //쿠키 생성
		 
		 if("Y".equals(lv.getRemember())){ //remember me 체크
			 setCookie("sid", lv.getId(), resp);
		 }else {
			 setCookie("sid", "", resp);
		 }
				 
		 return "redirect:/board/list";
		
	}
	//회원가입
	@RequestMapping(value="signup")
	public String SignUp(HttpServletRequest req,  ModelMap modelMap) {
			
		log.info("사인업 들어왔음");
		

		 
		return "user/SingUp";
		
	}
	
	//회원가입 입력 
	@RequestMapping(value="registUser")
	public String RegistUser(HttpServletRequest req, UserVO uv, ModelMap modelMap) {
			
		log.info("등록 들어왔음");
		log.info("id:"+uv.getId());
		log.info("pass:"+uv.getPass());
		UserSvc.registUser(uv);
		 
		return "etc/success";
		
	}
	
	//idcheck
		@RequestMapping(value="idCheck")
		public String IdCheck(HttpServletRequest req, ModelMap modelMap) {
				
    log.info("아이디 체크에 들어옴");
			 String id=req.getParameter("id");
			  log.info("id :" +id);
			  
			  int cnt =UserSvc.idCheck(id);
			  
			  log.info("check :"+cnt);
			
			 modelMap.addAttribute("cnt", cnt);
			 modelMap.addAttribute("id", id);
		
			
			return "user/idCheck";
			
		}
		
	
	//쿠키 불러오기
		private String get_cookie(String cid, HttpServletRequest req) {
			String ret="";
			
			if(req ==null) {
				return ret;
			}
			
			Cookie[] cookies=req.getCookies();
			
			if(cookies ==null) {
				return ret;
			}
			
			for(Cookie ck :cookies) {
				if(ck.getName().equals(cid)) {
					
					ret=ck.getValue();
					System.out.println("ck.getname"+ck.getName());
					System.out.println("ck value :" +ck.getValue());
					System.out.println("ret :" +ret);
					ck.setMaxAge(cookieExpire);
					break;
				}
			}
			return ret;
		}



		//쿠키생성
		private void setCookie(String cid, String pid, HttpServletResponse resp) {
			Cookie ck=new Cookie(cid, pid); //sid, id(접속한 사람 id)
			ck.setPath("/");
			ck.setMaxAge(cookieExpire);
			resp.addCookie(ck);
		}
		
		
		
}
