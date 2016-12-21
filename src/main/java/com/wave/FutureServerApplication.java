package com.wave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class FutureServerApplication extends WebMvcConfigurationSupport{

	@Bean(name = "dataSource")
	public static DataSource dataSource(){
		org.apache.tomcat.jdbc.pool.DataSource datasource=new org.apache.tomcat.jdbc.pool.DataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://10.60.42.203:8888/db_05");
		datasource.setUsername("T05");
		datasource.setPassword("ywHJOiq2");
		return datasource;
	}

	@Override
	protected void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
	}

	public static void main(String[] args) {

		SpringApplication.run(FutureServerApplication.class, args
		);
	}
}
