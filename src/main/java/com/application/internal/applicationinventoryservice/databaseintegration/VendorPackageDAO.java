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

		/*
		 * Map params = new HashMap(); params.put("applicationId",
		 * vendorPackageTO.getApplicationId());
		 * params.put("engAssociatedManagedServices",
		 * vendorPackageTO.getEngAssociatedManagedServices()); params.put("packageType",
		 * vendorPackageTO.getPackageType()); params.put("name",
		 * vendorPackageTO.getName()); params.put("engAssociatedWithVendorPackage",
		 * vendorPackageTO.getEngAssociatedWithVendorPackage());
		 * params.put("hostedLocation", vendorPackageTO.getHostedLocation());
		 * params.put("hostedName", vendorPackageTO.getHostedName());
		 * params.put("engAssociatedWithEsternallyHostedVendor",
		 * vendorPackageTO.getEngAssociatedWithEsternallyHostedVendor());
		 * params.put("isLatestSwVersion", vendorPackageTO.getIsLatestSwVersion());
		 * params.put("packageVersion", vendorPackageTO.getPackageVersion());
		 * params.put("frequencyOfUpdates", vendorPackageTO.getFrequencyOfUpdates());
		 * params.put("frequencyOfPatches", vendorPackageTO.getFrequencyOfPatches());
		 * params.put("degreeOfCustomization",
		 * vendorPackageTO.getDegreeOfCustomization()); template.update(sql, params);
		 */

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("vendorPackageId", vendorPackageTO.getVendorPackageId())
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

	public int updateVendorPackageDetails(VendorPackageTO vendorPackageTO) throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "update assessment.vendor_package_details set application_id =:applicationId, eng_associated_managed_services =:engAssociatedManagedServices, package_type =:packageType, name =:name, eng_associated_with_vendor_package =:engAssociatedWithVendorPackage, degree_of_customization =:degreeOfCustomization, hosted_location =:hostedLocation, hosted_name =:hostedName, eng_associated_with_esternally_hosted_vendor =:engAssociatedWithEsternallyHostedVendor, is_latest_sw_version =:isLatestSwVersion, package_version =:packageVersion, frequency_of_updates =:frequencyOfUpdates, frequency_of_patches =:frequencyOfPatches where id =:vendorPackageId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("vendorPackageId", vendorPackageTO.getVendorPackageId())
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
		return template.update(sql, parameters);
	}

}
