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

import com.application.internal.applicationinventoryservice.to.RegulatoryTO;

@Component
public class RegulatoryDAO {

	@Autowired
	private DataSource dataSource;

	public List<RegulatoryTO> retrieveRegulatoryData(int id) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as regulatoryId, application_id AS applicationId,regulatory_value AS regulatoryValue FROM assessment.regulatory_details where application_id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		List<RegulatoryTO> result = template.query(query, param, new BeanPropertyRowMapper(RegulatoryTO.class));
		return result;
	}

	
	
	public void storeAndUpdateRegulatoryDetails(List<RegulatoryTO> regulatoryTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String deleteRegulatory = "delete from assessment.regulatory_details where application_id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", regulatoryTO.get(0).getApplicationId());
		template.update(deleteRegulatory, param);
		for (RegulatoryTO regulatoryValue:regulatoryTO) {
			String sql = "insert into assessment.regulatory_details values(DEFAULT,:applicationId,:regulatoryValue)";
			Map params = new HashMap();
			params.put("applicationId", regulatoryValue.getApplicationId());
			params.put("regulatoryValue", regulatoryValue.isRegulatoryValue());
			template.update(sql, params);
			}
		
	}

}
