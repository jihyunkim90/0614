package com.web.maven.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class indexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("컨트롤러 있음");

		ModelAndView mv = new ModelAndView();
		mv.addObject("jihyunkim90", "어서오세요"); // ==setAttribute
		mv.setViewName("index");
		return mv;
	}

}
