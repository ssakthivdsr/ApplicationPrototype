package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDAO {
	@Autowired
	private Connection connection;
	
	public String retrieveDepartmentData(String id) throws SQLException {
		Statement stmt = connection.createStatement();
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
	
	public void storeDepartmentData(String departmentName, String departmentOwner) throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "insert into department(id,departmentname,departmentowner) values(DEFAULT,'" + departmentName +"','" + departmentOwner + "')";
	    stmt.executeUpdate(sql);
	}

}
