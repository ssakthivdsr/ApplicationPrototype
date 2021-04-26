package com.application.internal.applicationinventoryservice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataBaseConfig {
	private String databaseDriver = "jdbc:postgresql://";
	private String hostName = "ec2-54-163-254-204.compute-1.amazonaws.com:5432/d7e4cpif9fb7ik";
	private String userName = "hrksltgzjcfprq";
	private String cred = "1485871a74d91617ed97e0b4ba565632002feb9f4b63bceeb3b0c0e88eb4541f";
	
	@Bean
	public Connection connection() throws SQLException {
		String dburl = databaseDriver + hostName + "?user=" + userName + "&password=" + cred + "&sslmode=require";
		return DriverManager.getConnection(dburl);
	}
	
	 @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.postgresql.Driver");
	        dataSource.setUrl("jdbc:postgresql://ec2-54-163-254-204.compute-1.amazonaws.com:5432/d7e4cpif9fb7ik?sslmode=require");
	        dataSource.setUsername(userName);
	        dataSource.setPassword(cred);
	        return dataSource;
	    }
}