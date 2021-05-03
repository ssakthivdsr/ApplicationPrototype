package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.application.internal.applicationinventoryservice.to.DepartmentTO;
import com.application.internal.applicationinventoryservice.to.RetrieveDepartmentTO;

@Component
public class DepartmentDAO {
	
	@Autowired
	private DataSource dataSource;
	
	public RetrieveDepartmentTO retrieveDepartmentData(int id) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id,departmentname,departmentowner FROM department where id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		RetrieveDepartmentTO result = template.queryForObject(query, param, BeanPropertyRowMapper.newInstance(RetrieveDepartmentTO.class));
		return result;
	}
	
	public List<RetrieveDepartmentTO> retrieveAllDepartmentDetails() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id,departmentname,departmentowner FROM department ORDER BY Id desc";
		List<RetrieveDepartmentTO> result = template.query(query, new BeanPropertyRowMapper(RetrieveDepartmentTO.class));
		return result;
	}
	
	public void storeDepartmentData(String departmentName, String departmentOwner) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into department(id,departmentname,departmentowner) values(DEFAULT,:departmentName,:departmentOwner)";
		Map<String, String> params = new HashMap<String, String>();
		params.put("departmentName", departmentName);
		params.put("departmentOwner", departmentOwner);
		template.update(sql, params);
	}
	
	public void storeDepartmentDetails(DepartmentTO departmentTO) throws SQLException {
		storeDepartmentData(departmentTO.getDepartmentName(), departmentTO.getDepartmentOwner());
	}
	
//	public RetrieveDepartmentTO retrieveDepartmentData(String id) throws SQLException {
//		RetrieveDepartmentTO result = new RetrieveDepartmentTO();
//		Statement stmt = connection.createStatement();
//		String sql = "SELECT id,departmentname,departmentowner FROM department where id=" + Integer.parseInt(id);
//	    ResultSet rs = stmt.executeQuery(sql);
//	    int departmentId  = 0;
//        String departmentname = "";
//        String departmentowner = "";
//	    while(rs.next()){
//	         //Retrieve by column name
//	    	 departmentId  = rs.getInt("id");
//	         departmentname = rs.getString("departmentname");
//	         departmentowner = rs.getString("departmentowner");
//	         result.setId(departmentId);
//	         result.setDepartmentName(departmentname);
//	         result.setDepartmentOwner(departmentowner);
//	         //Display values
//	         System.out.print("ID: " + departmentId);
//	         System.out.print(", First: " + departmentname);
//	         System.out.println(", departmentowner: " + departmentowner);
//	    }
////	    return "\ndepartmentId:" + departmentId + ", departmentname: " + departmentname  + ", departmentowner: " + departmentowner;
//	    
//	    return result;
//	}
	
//	public void storeDepartmentData(String departmentName, String departmentOwner) throws SQLException {
//		Statement stmt = connection.createStatement();
//		String sql = "insert into department(id,departmentname,departmentowner) values(DEFAULT,'" + departmentName +"','" + departmentOwner + "')";
//	    stmt.executeUpdate(sql);
//	}

}
