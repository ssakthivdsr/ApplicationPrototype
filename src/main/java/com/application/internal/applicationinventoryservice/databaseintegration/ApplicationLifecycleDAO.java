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

import com.application.internal.applicationinventoryservice.to.ApplicationLifecycleRetrieveTO;
import com.application.internal.applicationinventoryservice.to.ApplicationLifecycleTO;
import com.application.internal.applicationinventoryservice.to.DepartmentTO;

@Component
public class ApplicationLifecycleDAO {

	@Autowired
	private DataSource dataSource;

	public List<ApplicationLifecycleRetrieveTO> retrieveApplicationLifecycleByApplicationId(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as id, application_id as applicationId,question_id as questionId, answer FROM assessment.application_life_cycle where application_id=:applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", applicationId);
		List<ApplicationLifecycleRetrieveTO> result = template.query(query, param,
				new BeanPropertyRowMapper(ApplicationLifecycleRetrieveTO.class));
		return result;
	}

	public void storeApplicationLifecycleDetails(ApplicationLifecycleTO applicationLifecycleTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.application_life_cycle values(DEFAULT,:applicationId,:questionId,:applicationLifecycleAnswers)";
		// Total 14 questions in application lifecycle questions table
		for (int i = 0; i < 14; i++) {
			Map params = new HashMap();
			params.put("applicationId", applicationLifecycleTO.getApplicationId());
			params.put("questionId", applicationLifecycleTO.questionAnswer[i].getQuestionId());
			if (applicationLifecycleTO.questionAnswer[i].getAnswer().length() != 0) {
				params.put("applicationLifecycleAnswers", applicationLifecycleTO.questionAnswer[i].getAnswer());
				template.update(sql, params);
			}
		}
	}
}