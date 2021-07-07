package com.web.maven.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@EnableTransactionManagement
@Configuration
public class ApplicationContext {
 
	
	@Bean
	public DataSource dataSource() {
	 BasicDataSource bds = new BasicDataSource();
	 bds.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
	 bds.setUrl("jdbc:log4jdbc:mysql://3.36.186.85:3306/PROJECT");
	 bds.setUsername("jihyunkim90");
	 bds.setPassword("1234");
	 bds.setInitialSize(3);
	 bds.setMaxIdle(10);
	 bds.setMinIdle(3);
	 bds.setValidationQuery("SELECT 1");
	 
	 return bds;
	 
 }
	@Bean
	public SqlSessionFactory sqlSession() throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource());
		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/sql/*.xml"));
	
	return sfb.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSession());
	}
	
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr =new CommonsMultipartResolver();
		return cmr;
	}
	
}
