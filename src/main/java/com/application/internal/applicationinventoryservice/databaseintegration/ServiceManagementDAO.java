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

import com.application.internal.applicationinventoryservice.scoreengine.TechAttributesRuleEngine;
import com.application.internal.applicationinventoryservice.to.ServiceManagementTO;

@Component
public class ServiceManagementDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private TechAttributesRuleEngine techAttributesRuleEngine;

	public List<ServiceManagementTO> retrieveServiceManagementByApplicationId(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as serviceManagementId ,application_id as applicationId,question_id as questionId,answer FROM assessment.service_management where application_id=:applicationId order by question_id asc";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", applicationId);
		List<ServiceManagementTO> result = template.query(query, param,
				new BeanPropertyRowMapper(ServiceManagementTO.class));
		return result;
	}

	public void storeAndupdateServiceManagementDetails(List<ServiceManagementTO> ServiceManagementTO)
			throws SQLException {


		if (countApplicationId(ServiceManagementTO) > 0) {
			updateServiceManagementDetails(ServiceManagementTO);
		} else {
			storeServiceManagementDetails(ServiceManagementTO);
		}
		
		if (countApplicationScoreId(ServiceManagementTO) > 0) {
			updateTechTotal(ServiceManagementTO);
		} else {
			storeTechTotal(ServiceManagementTO);
		}
	}

	public int countApplicationId(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String queryCount = "select count (*) as count FROM assessment.service_management where application_id= :applicationId";
		SqlParameterSource countParam = new MapSqlParameterSource("applicationId",
				ServiceManagementTO.get(0).getApplicationId());
		int count = template.queryForObject(queryCount, countParam, Integer.class);
		return count;
	}

	public void storeServiceManagementDetails(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.service_management values(DEFAULT,:applicationId,:questionId,:answer)";
		for (int i = 0; i < 47; i++) {
			Map Param = new HashMap();
			Param.put("applicationId", ServiceManagementTO.get(i).getApplicationId());
			Param.put("questionId", ServiceManagementTO.get(i).getQuestionId());
			Param.put("answer", ServiceManagementTO.get(i).getAnswer());
			template.update(sql, Param);
		}
	}

	public void updateServiceManagementDetails(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String updateQuery = "UPDATE assessment.service_management SET answer = :answer  WHERE question_id = :questionId and application_id = :applicationId";
		for (int i = 0; i < 47; i++) {
			Map updateParam = new HashMap();
			updateParam.put("applicationId", ServiceManagementTO.get(i).getApplicationId());
			updateParam.put("questionId", ServiceManagementTO.get(i).getQuestionId());
			updateParam.put("answer", ServiceManagementTO.get(i).getAnswer());
			template.update(updateQuery, updateParam);
		}
	}

	public int countApplicationScoreId(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String queryCount = "select count (*) as count FROM assessment.application_score where application_id= :applicationId";
		SqlParameterSource countParam = new MapSqlParameterSource("applicationId",
				ServiceManagementTO.get(0).getApplicationId());
		int count = template.queryForObject(queryCount, countParam, Integer.class);
		return count;
	}

	public void storeTechTotal(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.application_score values(DEFAULT,:applicationId,:business_value,:agility,:business_total,:tech_total)";
		Map Param = new HashMap();
		Param.put("applicationId", ServiceManagementTO.get(0).getApplicationId());
		Param.put("business_value", 0);
		Param.put("agility", 0);
		Param.put("business_total", 0);
		Param.put("tech_total", techAttributesRuleEngine.techAttributeTotal(ServiceManagementTO));
		template.update(sql, Param);
	}

	public void updateTechTotal(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String updateQuery = "UPDATE assessment.application_score SET tech_total = :tech_total  WHERE application_id = :applicationId";
		Map updateParam = new HashMap();
		updateParam.put("applicationId", ServiceManagementTO.get(0).getApplicationId());
		updateParam.put("tech_total", techAttributesRuleEngine.techAttributeTotal(ServiceManagementTO));
		template.update(updateQuery, updateParam);
	}

}