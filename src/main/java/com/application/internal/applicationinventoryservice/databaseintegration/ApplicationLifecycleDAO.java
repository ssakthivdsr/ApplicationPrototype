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

import com.application.internal.applicationinventoryservice.to.ApplicationLifecycleTO;
@Component
public class ApplicationLifecycleDAO {

	@Autowired
	private DataSource dataSource;

	public List<ApplicationLifecycleTO> retrieveApplicationLifecycleByApplicationId(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as id, application_id as applicationId,question_id as questionId, answer FROM assessment.application_life_cycle where application_id=:applicationId order by question_id asc";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", applicationId);
		List<ApplicationLifecycleTO> result = template.query(query, param,
				new BeanPropertyRowMapper(ApplicationLifecycleTO.class));
		return result;
	}

	public void storeAndupdateApplicationLifecycleDetails(List<ApplicationLifecycleTO> ApplicationLifecycleTO)
			throws SQLException {

		if (countApplicationId(ApplicationLifecycleTO) > 0) {
			updateApplicationLifecycleDetails(ApplicationLifecycleTO);
		} else {
			storeApplicationLifecycleDetails(ApplicationLifecycleTO);
		}
	}
	
	public int countApplicationId(List<ApplicationLifecycleTO> ApplicationLifecycleTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String queryCount = "select count (*) as count FROM assessment.application_life_cycle where application_id= :applicationId";
		SqlParameterSource countParam = new MapSqlParameterSource("applicationId",
				ApplicationLifecycleTO.get(0).getApplicationId());
		int count = template.queryForObject(queryCount, countParam, Integer.class);
		return count;
	}
	
	public void storeApplicationLifecycleDetails(List<ApplicationLifecycleTO> ApplicationLifecycleTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.application_life_cycle values(DEFAULT,:applicationId,:questionId,:answer)";
		for (int i = 0; i < 14; i++) {
			Map param = new HashMap();
			param.put("applicationId", ApplicationLifecycleTO.get(i).getApplicationId());
			param.put("questionId", ApplicationLifecycleTO.get(i).getQuestionId());
			param.put("answer", ApplicationLifecycleTO.get(i).getAnswer());
			template.update(sql, param);
		}
	}
	
	public void updateApplicationLifecycleDetails(List<ApplicationLifecycleTO> ApplicationLifecycleTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String updateQuery = "UPDATE assessment.application_life_cycle SET answer = :answer  WHERE question_id = :questionId and application_id = :applicationId";
		for (int i = 0; i < 14; i++) {
			Map updateParam = new HashMap();
			updateParam.put("applicationId", ApplicationLifecycleTO.get(i).getApplicationId());
			updateParam.put("questionId", ApplicationLifecycleTO.get(i).getQuestionId());
			updateParam.put("answer", ApplicationLifecycleTO.get(i).getAnswer());
			template.update(updateQuery, updateParam);
		}
	}
	
}
