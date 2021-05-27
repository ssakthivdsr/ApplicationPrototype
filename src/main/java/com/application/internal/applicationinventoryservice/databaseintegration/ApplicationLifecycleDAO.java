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
import com.application.internal.applicationinventoryservice.to.CountTO;
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
		String queryCount = "SELECT count (*) FROM assessment.application_life_cycle where application_id=:applicationId";
		SqlParameterSource count = new MapSqlParameterSource("applicationId", applicationLifecycleTO.getApplicationId());
		CountTO countValue = template.queryForObject(queryCount, count,BeanPropertyRowMapper.newInstance(CountTO.class));
		
		if (countValue.getCount() > 0) {
			String query = "UPDATE assessment.application_life_cycle SET answer = :answer  WHERE question_id = :questionId and application_id = :applicationId";
			for (int i = 0; i < 14; i++) {
				Map updateQuery = new HashMap();
				updateQuery.put("applicationId", applicationLifecycleTO.getApplicationId());
				updateQuery.put("questionId", applicationLifecycleTO.questionAnswer[i].getQuestionId());
				updateQuery.put("answer", applicationLifecycleTO.questionAnswer[i].getAnswer());
				template.update(query, updateQuery);
			}
		}

		else {
			String sql = "insert into assessment.application_life_cycle values(DEFAULT,:applicationId,:questionId,:answer)";
			for (int i = 0; i < 14; i++) {
				Map params = new HashMap();
				params.put("applicationId", applicationLifecycleTO.getApplicationId());
				params.put("questionId", applicationLifecycleTO.questionAnswer[i].getQuestionId());
				params.put("answer", applicationLifecycleTO.questionAnswer[i].getAnswer());
				template.update(sql, params);
			}
		}
	}	
	
	public void updateApplicationLifecycleDetails (List <ApplicationLifecycleRetrieveTO> ApplicationLifecycleRetrieveTO) throws SQLException {
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		int rowCount;
		String selectQuery = "select count (*) FROM assessment.application_life_cycle where application_id= :applicationId";
		SqlParameterSource count = new MapSqlParameterSource("applicationId", ApplicationLifecycleRetrieveTO.get(0).getApplicationId());
		CountTO Value = template.queryForObject(selectQuery,count,BeanPropertyRowMapper.newInstance(CountTO.class));
		rowCount=Value.getCount();
		if (rowCount > 0) {
			String updateQuery = "UPDATE assessment.application_life_cycle SET answer = :answer  WHERE question_id = :questionId and application_id = :applicationId";
			for (int i = 0; i < 14; i++) {
				Map updateParam = new HashMap();
				updateParam.put("applicationId", ApplicationLifecycleRetrieveTO.get(i).getApplicationId());
				updateParam.put("questionId", ApplicationLifecycleRetrieveTO.get(i).getQuestionId());
				updateParam.put("answer", ApplicationLifecycleRetrieveTO.get(i).getAnswer());
				template.update(updateQuery, updateParam);
			}
		}	
			
		else {			
			String sql = "insert into assessment.application_life_cycle values(DEFAULT,:applicationId,:questionId,:answer)";
			for (int i = 0; i < 14; i++) {
					Map Param = new HashMap();
					Param.put("applicationId", ApplicationLifecycleRetrieveTO.get(i).getApplicationId());
					Param.put("questionId", ApplicationLifecycleRetrieveTO.get(i).getQuestionId());
					Param.put("answer", ApplicationLifecycleRetrieveTO.get(i).getAnswer());
					template.update(sql,Param);
			}	
	  }
		
		
	}
	
}
