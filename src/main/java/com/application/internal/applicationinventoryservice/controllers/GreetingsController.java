package com.application.internal.applicationinventoryservice.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    /**
     *
     * @param name the name to greet
     * @return greeting text
     * @throws Exception 
     */
    @RequestMapping(value = "/inventory/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    
    public String greetingText(@PathVariable("name") String name) throws Exception {
        return "Department details for departmentId:" + name + " is : " + retrieveValueFromDate(name);
    }
    
    private String retrieveValueFromDate(String id) throws Exception {
    	String dbURL1 = "jdbc:postgresql://ec2-54-163-254-204.compute-1.amazonaws.com:5432/d7e4cpif9fb7ik?user=hrksltgzjcfprq&password=1485871a74d91617ed97e0b4ba565632002feb9f4b63bceeb3b0c0e88eb4541f&sslmode=require";
		Connection conn = DriverManager.getConnection(dbURL1);
		Statement stmt = conn.createStatement();
		String sql = "SELECT id,departmentname,departmentowner FROM department where id=" + Integer.parseInt(id);
	    ResultSet rs = stmt.executeQuery(sql);
	    int departmentId  = 0;
        String departmentname = "";
        String departmentowner = "";
	    while(rs.next()){
	         //Retrieve by column name
	    	 departmentId  = rs.getInt("id");
	         departmentname = rs.getString("departmentname");
	         departmentowner = rs.getString("departmentowner");

	         //Display values
	         System.out.print("ID: " + departmentId);
	         System.out.print(", First: " + departmentname);
	         System.out.println(", departmentowner: " + departmentowner);
	      }
	    return "\ndepartmentId:" + departmentId + ", departmentname: " + departmentname  + ", departmentowner: " + departmentowner;
    }
}
