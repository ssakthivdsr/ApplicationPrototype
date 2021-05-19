package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
		String query = "SELECT id as vendorPackageId,application_id AS applicationId,eng_associated_managed_services AS engAssociatedManagedServices,package_type AS packageType,name AS name,eng_associated_with_vendor_package AS engAssociatedWithVendorPackage,degree_of_customization AS degreeOfCustomization,hosted_location AS hostedLocation,hosted_name AS hostedName,eng_associated_with_esternally_hosted_vendor AS engAssociatedWithEsternallyHostedVendor,is_latest_sw_version AS isLatestSwVersion,package_version AS packageVersion,frequency_of_updates AS frequencyOfUpdates,frequency_of_patches AS frequencyOfPatches FROM assessment.vendor_package_details  where application_id=:id";
		SqlParameterSource param = new MapSqlParameterSource("id", applicationId);
		VendorPackageTO result = template.queryForObject(query, param,
				BeanPropertyRowMapper.newInstance(VendorPackageTO.class));
		return result;
	}

	public void storeVendorPackageDetails(VendorPackageTO vendorPackageTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "insert into assessment.vendor_package_details values(DEFAULT,:applicationId,:engAssociatedManagedServices,:packageType,:name,:engAssociatedWithVendorPackage,:degreeOfCustomization,:hostedLocation,:hostedName,:engAssociatedWithEsternallyHostedVendor,:isLatestSwVersion,:packageVersion,:frequencyOfUpdates,:frequencyOfPatches)";
		Map params = new HashMap();
		params.put("applicationId", vendorPackageTO.getApplicationId());
		params.put("engAssociatedManagedServices", vendorPackageTO.getEngAssociatedManagedServices());
		params.put("packageType", vendorPackageTO.getPackageType());
		params.put("name", vendorPackageTO.getName());
		params.put("engAssociatedWithVendorPackage", vendorPackageTO.getEngAssociatedWithVendorPackage());
		params.put("hostedLocation", vendorPackageTO.getHostedLocation());
		params.put("hostedName", vendorPackageTO.getHostedName());
		params.put("engAssociatedWithEsternallyHostedVendor",
				vendorPackageTO.getEngAssociatedWithEsternallyHostedVendor());
		params.put("isLatestSwVersion", vendorPackageTO.getIsLatestSwVersion());
		params.put("packageVersion", vendorPackageTO.getPackageVersion());
		params.put("frequencyOfUpdates", vendorPackageTO.getFrequencyOfUpdates());
		params.put("frequencyOfPatches", vendorPackageTO.getFrequencyOfPatches());
		params.put("degreeOfCustomization", vendorPackageTO.getDegreeOfCustomization());
		template.update(sql, params);
	}

}
