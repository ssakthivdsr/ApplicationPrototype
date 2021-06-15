package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.List;

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
		String query = "SELECT a.id as applicationId, a.dept_id as departmentId, d.name as departmentName, a.name as applicationName, a.component_manager as nameOfTheComponentManager, a.sme as smeProvidedByManagers, a.primary_tech_sme as nameOfPrimaryTechSME, a.primary_ba as nameOfPrimaryBA, a.descr as applicationDescription, a.lob as lineOfBusiness, a.func as functionality, c.business_value as businessValue, c.agility as agility, c.business_total as businessTotal,c.tech_total as techTotal FROM assessment.application as a, assessment.department as d, assessment.application_score as c where a.dept_id = d.id and a.id = :id and c.application_id = :id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		ApplicationTO result = template.queryForObject(query, param,
				BeanPropertyRowMapper.newInstance(ApplicationTO.class));
		return result;
	}

	public List<ApplicationTO> retrieveAllApplicationDetails() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

		String query = "SELECT a.id as applicationId, a.dept_id as departmentId, d.name as departmentName, a.name as applicationName, a.component_manager as nameOfTheComponentManager, a.sme as smeProvidedByManagers, a.primary_tech_sme as nameOfPrimaryTechSME, a.primary_ba as nameOfPrimaryBA, a.descr as applicationDescription, a.lob as lineOfBusiness, a.func as functionality, c.business_value as businessValue, c.agility as agility, c.business_total as businessTotal,c.tech_total as techTotal FROM assessment.application as a, assessment.department as d, assessment.application_score as c where a.dept_id = d.id and c.application_id=a.id";
		List<ApplicationTO> result = template.query(query, new BeanPropertyRowMapper(ApplicationTO.class));
		return result;
	}

	public int storeApplicationDetails(ApplicationTO applicationTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		KeyHolder holder = new GeneratedKeyHolder();
		String sql = "insert into assessment.application values(DEFAULT,:applicationName,:applicationDescription,:departmentId,:lineOfBusiness,:functionality,:nameOfTheComponentManager,:smeProvidedByManagers,:nameOfPrimaryTechSME,:nameOfPrimaryBA)";
		String scoreSql = "insert into assessment.application_score values(DEFAULT,:applicationId,0,0,0,0)";

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
		SqlParameterSource scoreParameters = new MapSqlParameterSource().addValue("applicationId",
				holder.getKey().intValue());
		template.update(scoreSql, scoreParameters);
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
