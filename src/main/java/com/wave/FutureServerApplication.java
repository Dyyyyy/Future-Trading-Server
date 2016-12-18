package com.wave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class FutureServerApplication {

	@Bean(name = "dataSource")
	public static DataSource dataSource(){
		org.apache.tomcat.jdbc.pool.DataSource datasource=new org.apache.tomcat.jdbc.pool.DataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://10.60.42.203:8888/db_05");
		datasource.setUsername("T05");
		datasource.setPassword("ywHJOiq2");
		return datasource;
	}

	public static void main(String[] args) {

		SpringApplication.run(FutureServerApplication.class, args
		);
	}
}
