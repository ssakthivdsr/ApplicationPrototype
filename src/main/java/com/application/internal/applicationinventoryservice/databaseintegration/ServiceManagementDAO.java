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

import com.application.internal.applicationinventoryservice.to.ServiceManagementQuestionAnswerTO;
import com.application.internal.applicationinventoryservice.to.ServiceManagementRetrieveTO;
import com.application.internal.applicationinventoryservice.to.ServiceManagementTO;

@Component
public class ServiceManagementDAO {

	@Autowired
	private DataSource dataSource;

	public List<ServiceManagementRetrieveTO> retrieveServiceManagementByApplicationId(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as serviceManagementId ,application_id as applicationId,question_id as questionId,answer FROM assessment.service_management where application_id=:applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", applicationId);
		List<ServiceManagementRetrieveTO> result = template.query(query, param,
				new BeanPropertyRowMapper(ServiceManagementRetrieveTO.class));

		return result;
	}

	public void storeServiceManagementDetails(ServiceManagementTO serviceManagementTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.service_management values(DEFAULT,:applicationId,:questionId,:answer)";
		// Total 47 questions in service management questions table
		for (int i = 0; i <= 46; i++) {
			Map params = new HashMap();
			params.put("applicationId", serviceManagementTO.getApplicationId());
			params.put("questionId", serviceManagementTO.questionAnswer[i].getQuestionId());
			if (serviceManagementTO.questionAnswer[i].getAnswer().length() != 0) {
				params.put("answer", serviceManagementTO.questionAnswer[i].getAnswer());
				template.update(sql, params);
			}
		}

	}
}
