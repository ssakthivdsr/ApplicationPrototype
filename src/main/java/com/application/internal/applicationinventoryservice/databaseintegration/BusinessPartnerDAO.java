package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		try {
		String query = "SELECT id as businessPartnerId, application_id as applicationId, primary_business_partner as primaryBusinessPartner, secondary_business_partner as secondaryBusinessPartner, managers as businessPartnerManagers, directors as businessPartnerDirectors FROM assessment.business_partner where application_id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		BusinessPartnerTO result = template.queryForObject(query, param,
				BeanPropertyRowMapper.newInstance(BusinessPartnerTO.class));
		return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void storeBusinessPartnerDetails(BusinessPartnerTO businessPartnerTO) throws SQLException {
		int count = 0;
		String sql;
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String queryCount = "select count (*) as count FROM assessment.business_partner where application_id= :applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", businessPartnerTO.getApplicationId());
		count = template.queryForObject(queryCount, param, Integer.class);
		if (count > 0) {
			sql = "update assessment.business_partner set primary_business_partner =:primaryBusinessPartner,secondary_business_partner =:secondaryBusinessPartner,managers =:businessPartnerManagers,directors=:businessPartnerDirectors where application_id =:applicationId ";

		} else {
			sql = "insert into assessment.business_partner values(DEFAULT,:applicationId,:primaryBusinessPartner,:secondaryBusinessPartner,:businessPartnerManagers,:businessPartnerDirectors)";

		}

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("primaryBusinessPartner", businessPartnerTO.getPrimaryBusinessPartner())
				.addValue("secondaryBusinessPartner", businessPartnerTO.getSecondaryBusinessPartner())
				.addValue("businessPartnerManagers", businessPartnerTO.getBusinessPartnerManagers())
				.addValue("businessPartnerDirectors", businessPartnerTO.getBusinessPartnerDirectors())
				.addValue("applicationId", businessPartnerTO.getApplicationId());
		template.update(sql, params);
	}
}
