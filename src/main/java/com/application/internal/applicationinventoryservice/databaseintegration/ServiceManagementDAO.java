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

import com.application.internal.applicationinventoryservice.to.ServiceManagementTO;

@Component
public class ServiceManagementDAO {
	
	@Autowired
	private DataSource dataSource;
		
	public List<ServiceManagementTO> retrieveServiceManagementData(int id) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id AS serviceManagementId, application_id AS applicationId, question_id as questionId, answer AS serviceManagementAnswer FROM assessment.service_management where application_id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		List<ServiceManagementTO> result = template.query(query, param, new BeanPropertyRowMapper(ServiceManagementTO.class));
		return result;
	}
	
	public void storeServiceManagementDetails(ServiceManagementTO serviceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.service_management values(DEFAULT, :applicationId, :questionId, :serviceManagementAnswer)";
		Map params = new HashMap();
		params.put("applicationId", serviceManagementTO.getApplicationId());
		params.put("questionId", serviceManagementTO.getQuestionId());
		params.put("serviceManagementAnswer", serviceManagementTO.getServiceManagementAnswer());
		template.update(sql, params);
	}
}
