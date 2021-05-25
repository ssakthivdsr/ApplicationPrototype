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

@Component
public class DepartmentDAO {

	@Autowired
	private DataSource dataSource;

	public DepartmentTO retrieveDepartmentData(int id) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as departmentId, name as departmentName, owner as departmentOwner FROM assessment.department where id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		DepartmentTO result = template.queryForObject(query, param,
				BeanPropertyRowMapper.newInstance(DepartmentTO.class));
		return result;
	}

	public List<DepartmentTO> retrieveAllDepartmentDetails() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as departmentId, name as departmentName, owner as departmentOwner FROM assessment.department order by id asc";
		List<DepartmentTO> result = template.query(query, new BeanPropertyRowMapper(DepartmentTO.class));
		return result;
	}

	public void storeDepartmentData(String departmentName, String departmentOwner) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.department values(DEFAULT,:departmentName,:departmentOwner)";
		Map<String, String> params = new HashMap<String, String>();
		params.put("departmentName", departmentName);
		params.put("departmentOwner", departmentOwner);
		template.update(sql, params);
	}

	public void storeDepartmentDetails(DepartmentTO departmentTO) throws SQLException {
		storeDepartmentData(departmentTO.getDepartmentName(), departmentTO.getDepartmentOwner());
	}

	public int updateDepartmentDetails(DepartmentTO departmentTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "update assessment.department set name=:departmentName, owner = :departmentOwner where id=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("departmentName", departmentTO.getDepartmentName())
				.addValue("departmentOwner", departmentTO.getDepartmentOwner())
				.addValue("id", departmentTO.getDepartmentId());

		return template.update(sql, parameters);
	}

}
