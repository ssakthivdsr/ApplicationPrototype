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
	private String hostName = "ec2-34-193-101-0.compute-1.amazonaws.com:5432/ddv34mle2ilkan";
	private String userName = "uzkyokvvejpdfm";
	private String cred = "7fd18db309bb1aff144af0b29cdc8882f46ab38118a3b99385ac98401bab6aeb";

	@Bean
	public Connection connection() throws SQLException {
		String dburl = databaseDriver + hostName + "?user=" + userName + "&password=" + cred + "&sslmode=require";
		return DriverManager.getConnection(dburl);
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(
				"jdbc:postgresql://ec2-34-193-101-0.compute-1.amazonaws.com:5432/ddv34mle2ilkan?sslmode=require");
		dataSource.setUsername(userName);
		dataSource.setPassword(cred);
		return dataSource;
	}
}