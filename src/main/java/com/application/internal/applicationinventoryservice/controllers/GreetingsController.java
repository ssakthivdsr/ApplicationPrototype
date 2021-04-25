package com.application.internal.applicationinventoryservice.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.internal.applicationinventoryservice.databaseintegration.DBConfiguration;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private DBConfiguration dbConfiguration;
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
    	Connection conn = dbConfiguration.retrieveDatabaseConnection();
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
	    sql = "insert into department(id,departmentname,departmentowner) values(DEFAULT,'bank','department that deals with bank applications')";
	    stmt.executeUpdate(sql);
	    return "\ndepartmentId:" + departmentId + ", departmentname: " + departmentname  + ", departmentowner: " + departmentowner;
    }
}
