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
import com.application.internal.applicationinventoryservice.to.BusinessPartnerTO;

@Component
public class BusinessPartnerDAO {

	@Autowired
	private DataSource dataSource;

	public BusinessPartnerTO retrieveBusinessPartnerData(int id) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String query = "SELECT id as businessPartnerId, application_id as applicationId, primary_business_partner as primaryBusinessPartner, secondary_business_partner as secondaryBusinessPartner, managers as businessPartnerManagers, directors as businessPartnerDirectors FROM assessment.business_partner where application_id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		BusinessPartnerTO result = template.queryForObject(query, param,
				BeanPropertyRowMapper.newInstance(BusinessPartnerTO.class));
		return result;
	}

	public void storeBusinessPartnerDetails(BusinessPartnerTO businessPartnerTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.business_partner values(DEFAULT,:applicationId,:primaryBusinessPartner,:secondaryBusinessPartner,:businessPartnerManagers,:businessPartnerDirectors)";

		/*
		 * Map params = new HashMap(); params.put("applicationId",
		 * businessPartnerTO.getApplicationId()); params.put("primaryBusinessPartner",
		 * businessPartnerTO.getPrimaryBusinessPartner());
		 * params.put("secondaryBusinessPartner",
		 * businessPartnerTO.getSecondaryBusinessPartner());
		 * params.put("businessPartnerManagers",
		 * businessPartnerTO.getBusinessPartnerManagers());
		 * params.put("businessPartnerDirectors",
		 * businessPartnerTO.getBusinessPartnerDirectors()); template.update(sql,
		 * params);
		 */

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("applicationId", businessPartnerTO.getApplicationId())
				.addValue("primaryBusinessPartner", businessPartnerTO.getPrimaryBusinessPartner())
				.addValue("secondaryBusinessPartner", businessPartnerTO.getSecondaryBusinessPartner())
				.addValue("businessPartnerManagers", businessPartnerTO.getBusinessPartnerManagers())
				.addValue("businessPartnerDirectors", businessPartnerTO.getBusinessPartnerDirectors());
		template.update(sql, params);
	}

	public int updateBusinessPartnerDetails(BusinessPartnerTO businessPartnerTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "update assessment.business_partner set application_id=:applicationId,primary_business_partner=:primaryBusinessPartner,secondary_business_partner=:secondaryBusinessPartner, managers = :businessPartnerManagers,directors=:businessPartnerDirectors where id=:businessPartnerId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("applicationId", businessPartnerTO.getApplicationId())
				.addValue("primaryBusinessPartner", businessPartnerTO.getPrimaryBusinessPartner())
				.addValue("secondaryBusinessPartner", businessPartnerTO.getSecondaryBusinessPartner())
				.addValue("businessPartnerManagers", businessPartnerTO.getBusinessPartnerManagers())
				.addValue("businessPartnerDirectors", businessPartnerTO.getBusinessPartnerDirectors())
				.addValue("businessPartnerId", businessPartnerTO.getBusinessPartnerId());
		return template.update(sql, parameters);
	}
}
