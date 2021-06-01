package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.application.internal.applicationinventoryservice.to.VendorPackageTO;

@Component
public class VendorPackageDAO {

	@Autowired
	private DataSource dataSource;

	public VendorPackageTO retrieveVendorPackageData(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		try {
			String query = "SELECT id as vendorPackageId,application_id AS applicationId,eng_associated_managed_services AS engAssociatedManagedServices,package_type AS packageType,name AS name,eng_associated_with_vendor_package AS engAssociatedWithVendorPackage,degree_of_customization AS degreeOfCustomization,hosted_location AS hostedLocation,hosted_name AS hostedName,eng_associated_with_esternally_hosted_vendor AS engAssociatedWithEsternallyHostedVendor,is_latest_sw_version AS isLatestSwVersion,package_version AS packageVersion,frequency_of_updates AS frequencyOfUpdates,frequency_of_patches AS frequencyOfPatches FROM assessment.vendor_package_details  where application_id=:id";
			SqlParameterSource param = new MapSqlParameterSource("id", applicationId);
			VendorPackageTO result = template.queryForObject(query, param,
					BeanPropertyRowMapper.newInstance(VendorPackageTO.class));
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void storeVendorPackageDetails(VendorPackageTO vendorPackageTO) throws SQLException {
		int Value = 0;
		String sql;
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String queryCount = "select count (*) as count FROM assessment.vendor_package_details where application_id= :applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", vendorPackageTO.getApplicationId());
		Value = template.queryForObject(queryCount, param, Integer.class);
		if (Value > 0) {
			sql = "update assessment.vendor_package_details set eng_associated_managed_services=:engAssociatedManagedServices, package_type=:packageType, name=:name, eng_associated_with_vendor_package=:engAssociatedWithVendorPackage, degree_of_customization=:degreeOfCustomization, hosted_location=:hostedLocation, hosted_name=:hostedName, eng_associated_with_esternally_hosted_vendor=:engAssociatedWithEsternallyHostedVendor, is_latest_sw_version=:isLatestSwVersion, package_version=:packageVersion, frequency_of_updates=:frequencyOfUpdates, frequency_of_patches=:frequencyOfPatches where application_id=:applicationId ";
		} else {
			sql = "insert into assessment.vendor_package_details values(DEFAULT,:applicationId,:engAssociatedManagedServices,:packageType,:name,:engAssociatedWithVendorPackage,:degreeOfCustomization,:hostedLocation,:hostedName,:engAssociatedWithEsternallyHostedVendor,:isLatestSwVersion,:packageVersion,:frequencyOfUpdates,:frequencyOfPatches)";
		}
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("applicationId", vendorPackageTO.getApplicationId())
				.addValue("engAssociatedManagedServices", vendorPackageTO.getEngAssociatedManagedServices())
				.addValue("packageType", vendorPackageTO.getPackageType()).addValue("name", vendorPackageTO.getName())
				.addValue("engAssociatedWithVendorPackage", vendorPackageTO.getEngAssociatedWithVendorPackage())
				.addValue("hostedLocation", vendorPackageTO.getHostedLocation())
				.addValue("hostedName", vendorPackageTO.getHostedName())
				.addValue("engAssociatedWithEsternallyHostedVendor",
						vendorPackageTO.getEngAssociatedWithEsternallyHostedVendor())
				.addValue("isLatestSwVersion", vendorPackageTO.getIsLatestSwVersion())
				.addValue("packageVersion", vendorPackageTO.getPackageVersion())
				.addValue("frequencyOfUpdates", vendorPackageTO.getFrequencyOfUpdates())
				.addValue("frequencyOfPatches", vendorPackageTO.getFrequencyOfPatches())
				.addValue("degreeOfCustomization", vendorPackageTO.getDegreeOfCustomization());
		template.update(sql, params);
	}

}
