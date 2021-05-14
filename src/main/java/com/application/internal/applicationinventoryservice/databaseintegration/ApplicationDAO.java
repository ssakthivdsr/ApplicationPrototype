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
import com.application.internal.applicationinventoryservice.to.ApplicationTO;

@Component
public class ApplicationDAO {
	
	@Autowired
	private DataSource dataSource;
	
	public ApplicationTO retrieveApplicationData(int id) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as applicationId, dept_id as departmentId, name as applicationName, component_manager as nameOfTheComponentManager, sme as smeProvidedByManagers, primary_tech_sme as nameOfPrimaryTechSME, primary_ba as nameOfPrimaryBA, descr as applicationDescription, lob as lineOfBusiness, func as functionality FROM assessment.application where id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		ApplicationTO result = template.queryForObject(query, param, BeanPropertyRowMapper.newInstance(ApplicationTO.class));
		return result;
	}
	
	public List<ApplicationTO> retrieveAllApplicationDetails() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as applicationId, dept_id as departmentId, name as applicationName, component_manager as nameOfTheComponentManager, sme as smeProvidedByManagers, primary_tech_sme as nameOfPrimaryTechSME, primary_ba as nameOfPrimaryBA, descr as applicationDescription, lob as lineOfBusiness, func as functionality FROM assessment.application order by id asc";
		List<ApplicationTO> result = template.query(query, new BeanPropertyRowMapper(ApplicationTO.class));
		return result;
	}
	
	public void storeApplicationDetails(ApplicationTO applicationTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.application values(DEFAULT,:departmentId,:applicationName,:nameOfTheComponentManager,:smeProvidedByManagers,:nameOfPrimaryTechSME,:nameOfPrimaryBA,:applicationDescription,:lineOfBusiness,:functionality)";
		Map params = new HashMap();
		params.put("applicationName", applicationTO.getApplicationName());
		params.put("applicationDescription", applicationTO.getApplicationDescription());
		params.put("departmentId", applicationTO.getDepartmentId());
		params.put("lineOfBusiness", applicationTO.getLineOfBusiness());
		params.put("functionality", applicationTO.getFunctionality());
		params.put("nameOfTheComponentManager", applicationTO.getNameOfTheComponentManager());
		params.put("smeProvidedByManagers", applicationTO.getSmeProvidedByManagers());
		params.put("nameOfPrimaryTechSME", applicationTO.getNameOfPrimaryTechSME());
		params.put("nameOfPrimaryBA", applicationTO.getNameOfPrimaryBA());
		template.update(sql, params);
	}
}

