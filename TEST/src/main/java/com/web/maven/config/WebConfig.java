package com.web.maven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com")
public class WebConfig  implements WebMvcConfigurer{

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
	 InternalResourceViewResolver irv =new InternalResourceViewResolver();
	 irv.setPrefix("/WEB-INF/board/");
	 irv.setSuffix(".jsp");
	 
	 return irv;
 }
	public void addResourceHandlers(ResourceHandlerRegistry rhr) {
		rhr.addResourceHandler("/js/**").addResourceLocations("/js/");
		rhr.addResourceHandler("/css/**").addResourceLocations("/css/");
		rhr.addResourceHandler("/img/**").addResourceLocations("/img/");
	}
	
	
}
