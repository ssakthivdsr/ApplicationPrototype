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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		ApplicationTO result = template.queryForObject(query, param,
				BeanPropertyRowMapper.newInstance(ApplicationTO.class));
		return result;
	}

	public List<ApplicationTO> retrieveAllApplicationDetails() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
//		String query = "SELECT id as applicationId, dept_id as departmentId, name as applicationName, component_manager as nameOfTheComponentManager, sme as smeProvidedByManagers, primary_tech_sme as nameOfPrimaryTechSME, primary_ba as nameOfPrimaryBA, descr as applicationDescription, lob as lineOfBusiness, func as functionality FROM assessment.application order by id asc";
		String query = "SELECT \r\n"
				+ "	a.id as applicationId, a.dept_id as departmentId, d.name as departmentName, a.name as applicationName, \r\n"
				+ "	a.component_manager as nameOfTheComponentManager, a.sme as smeProvidedByManagers, a.primary_tech_sme as nameOfPrimaryTechSME, \r\n"
				+ "	a.primary_ba as nameOfPrimaryBA, 	a.descr as applicationDescription, lob as lineOfBusiness, func as functionality \r\n"
				+ "	FROM assessment.application as a,assessment.department as d\r\n" + "	where a.dept_id = d.id\r\n"
				+ "	order by a.id asc";
		List<ApplicationTO> result = template.query(query, new BeanPropertyRowMapper(ApplicationTO.class));
		return result;
	}

	public int storeApplicationDetails(ApplicationTO applicationTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		KeyHolder holder = new GeneratedKeyHolder();
		String sql = "insert into assessment.application values(DEFAULT,:applicationName,:applicationDescription,:departmentId,:lineOfBusiness,:functionality,:nameOfTheComponentManager,:smeProvidedByManagers,:nameOfPrimaryTechSME,:nameOfPrimaryBA)";
		/*
		 * Map params = new HashMap(); params.put("applicationName",
		 * applicationTO.getApplicationName()); params.put("applicationDescription",
		 * applicationTO.getApplicationDescription()); params.put("departmentId",
		 * applicationTO.getDepartmentId()); params.put("lineOfBusiness",
		 * applicationTO.getLineOfBusiness()); params.put("functionality",
		 * applicationTO.getFunctionality()); params.put("nameOfTheComponentManager",
		 * applicationTO.getNameOfTheComponentManager());
		 * params.put("smeProvidedByManagers",
		 * applicationTO.getSmeProvidedByManagers()); params.put("nameOfPrimaryTechSME",
		 * applicationTO.getNameOfPrimaryTechSME()); params.put("nameOfPrimaryBA",
		 * applicationTO.getNameOfPrimaryBA());
		 */
//		template.update(sql, params);

		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("applicationName", applicationTO.getApplicationName())
				.addValue("applicationDescription", applicationTO.getApplicationDescription())
				.addValue("departmentId", applicationTO.getDepartmentId())
				.addValue("lineOfBusiness", applicationTO.getLineOfBusiness())
				.addValue("functionality", applicationTO.getFunctionality())
				.addValue("nameOfTheComponentManager", applicationTO.getNameOfTheComponentManager())
				.addValue("smeProvidedByManagers", applicationTO.getSmeProvidedByManagers())
				.addValue("nameOfPrimaryTechSME", applicationTO.getNameOfPrimaryTechSME())
				.addValue("nameOfPrimaryBA", applicationTO.getNameOfPrimaryBA());
		String[] fields = { "id" };
		template.update(sql, parameters, holder, fields);
		return holder.getKey().intValue();
	}

	public int updateApplicationDetails(ApplicationTO applicationTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "update assessment.application set name=:applicationName,descr=:applicationDescription,dept_id=:departmentId,lob=:lineOfBusiness,func=:functionality,component_manager=:nameOfTheComponentManager,sme=:smeProvidedByManagers,primary_tech_sme=:nameOfPrimaryTechSME,primary_ba=:nameOfPrimaryBA where id=:applicationId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("applicationName", applicationTO.getApplicationName())
				.addValue("applicationDescription", applicationTO.getApplicationDescription())
				.addValue("departmentId", applicationTO.getDepartmentId())
				.addValue("lineOfBusiness", applicationTO.getLineOfBusiness())
				.addValue("functionality", applicationTO.getFunctionality())
				.addValue("nameOfTheComponentManager", applicationTO.getNameOfTheComponentManager())
				.addValue("smeProvidedByManagers", applicationTO.getSmeProvidedByManagers())
				.addValue("nameOfPrimaryTechSME", applicationTO.getNameOfPrimaryTechSME())
				.addValue("nameOfPrimaryBA", applicationTO.getNameOfPrimaryBA())
				.addValue("applicationId", applicationTO.getApplicationId());
		return template.update(sql, parameters);
	}
}
