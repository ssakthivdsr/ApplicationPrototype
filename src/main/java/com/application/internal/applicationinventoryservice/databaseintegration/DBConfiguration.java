package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

@Component
public class DBConfiguration {
	private String databaseDriver = "jdbc:postgresql://";
	private String hostName = "ec2-54-163-254-204.compute-1.amazonaws.com:5432/d7e4cpif9fb7ik";
	private String userName = "hrksltgzjcfprq";
	private String cred = "1485871a74d91617ed97e0b4ba565632002feb9f4b63bceeb3b0c0e88eb4541f";
	
	public Connection retrieveDatabaseConnection() throws Exception {
		String dburl = databaseDriver + hostName + "?user=" + userName + "&password=" + cred + "&sslmode=require";
		return DriverManager.getConnection(dburl);
		
	}

}
