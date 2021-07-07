package com.web.maven.etc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SideBarCtr {
	
	@Autowired
	private EtcSvc EtcSvc;

	
	
	@RequestMapping(value="/etc/sidebar")
	public String getMenuList(HttpServletRequest req,ModelMap modelMap) {
		
		
		String userRank =req.getSession().getAttribute("userRank").toString();
		log.info("userRank"+userRank);
		
		List<MenuVO> list =EtcSvc.getSideMenu(userRank);
		
		
		modelMap.addAttribute("menu",list);
		
		return "etc/sidebar";
		
	}
	
	
}
