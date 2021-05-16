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
	private String hostName = "ec2-23-23-128-222.compute-1.amazonaws.com:5432/d3tjkj6uhv9da3";
	private String userName = "jmucacukmuqirk";
	private String cred = "c10b854e470d25669c14c1f736320378a62cbaaa440bdb187b19ca63075c7c6b";

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
				"jdbc:postgresql://ec2-23-23-128-222.compute-1.amazonaws.com:5432/d3tjkj6uhv9da3?sslmode=require");
		dataSource.setUsername(userName);
		dataSource.setPassword(cred);
		return dataSource;
	}
	 
	
	/*
	 * private String databaseDriver = "jdbc:postgresql://"; private String hostName
	 * = "ec2-52-87-107-83.compute-1.amazonaws.com:5432/dbih80evtcg5ij"; private
	 * String userName = "enasdlqxihdtmq"; private String cred =
	 * "e1bbf3841ddfd79e4524eaae06284467dc719e58c9d11d70fcd91a20f2ac22ab";
	 * 
	 * @Bean public Connection connection() throws SQLException { String dburl =
	 * databaseDriver + hostName + "?user=" + userName + "&password=" + cred +
	 * "&sslmode=require"; return DriverManager.getConnection(dburl); }
	 * 
	 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
	 * new DriverManagerDataSource();
	 * dataSource.setDriverClassName("org.postgresql.Driver"); dataSource.setUrl(
	 * "jdbc:postgresql://ec2-52-87-107-83.compute-1.amazonaws.com:5432/dbih80evtcg5ij?sslmode=require"
	 * ); dataSource.setUsername(userName); dataSource.setPassword(cred); return
	 * dataSource; }
	 */
}