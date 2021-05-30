package com.application.internal.applicationinventoryservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.internal.applicationinventoryservice.databaseintegration.ApplicationDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.ApplicationLifecycleDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.BusinessApplicationDetailsDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.BusinessPartnerDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.DepartmentDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.RegulatoryDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.ServiceManagementDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.VendorPackageDAO;
import com.application.internal.applicationinventoryservice.to.ApplicationLifecycleRetrieveTO;
import com.application.internal.applicationinventoryservice.to.ApplicationLifecycleTO;
import com.application.internal.applicationinventoryservice.to.ApplicationTO;
import com.application.internal.applicationinventoryservice.to.BusinessApplicationDetailsTO;
import com.application.internal.applicationinventoryservice.to.BusinessPartnerTO;
import com.application.internal.applicationinventoryservice.to.DepartmentTO;
import com.application.internal.applicationinventoryservice.to.RegulatoryTO;
import com.application.internal.applicationinventoryservice.to.ServiceManagementRetrieveTO;
import com.application.internal.applicationinventoryservice.to.ServiceManagementTO;
import com.application.internal.applicationinventoryservice.to.VendorPackageTO;

@RestController
public class GreetingsController {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private ApplicationDAO applicationDAO;

	@Autowired
	private BusinessPartnerDAO businessPartnerDAO;

	@Autowired
	private BusinessApplicationDetailsDAO businessApplicationDetailsDAO;

	@Autowired
	private ApplicationLifecycleDAO applicationLifecycleDAO;

	@Autowired
	private RegulatoryDAO regulatoryDAO;

	@Autowired
	private ServiceManagementDAO serviceManagementDAO;

	@Autowired
	private VendorPackageDAO vendorPackageDAO;

	@RequestMapping(value = "/retrieveDepartmentById/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody DepartmentTO retrieveDepartmentData(@PathVariable("id") String id) throws Exception {
		return departmentDAO.retrieveDepartmentData(Integer.parseInt(id));
	}

	@RequestMapping(value = "/retrieveAllDepartmentDetails", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody List<DepartmentTO> retrieveAllDepartmentDetails() throws Exception {
		return departmentDAO.retrieveAllDepartmentDetails();
	}

	@RequestMapping(value = "/storeDepartmentData/{name}/{owner}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeDepartmentData(@PathVariable("name") String departmentName,
			@PathVariable("owner") String departmentOwner) throws Exception {
		departmentDAO.storeDepartmentData(departmentName, departmentOwner);
	}

	@PostMapping("/storeDepartmentDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeDepartmentDetails(@RequestBody DepartmentTO departmentTO) throws Exception {
		departmentDAO.storeDepartmentDetails(departmentTO);
	}
	
	@PostMapping("/updateDepartmentDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody int updateDepartmentDetails(@RequestBody DepartmentTO departmentTO) throws Exception {
		return departmentDAO.updateDepartmentDetails(departmentTO);
	}

	@RequestMapping(value = "/retrieveApplicationById/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody ApplicationTO retrieveApplicationData(@PathVariable("id") String id) throws Exception {
		return applicationDAO.retrieveApplicationData(Integer.parseInt(id));
	}

	@RequestMapping(value = "/retrieveAllApplicationDetails", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody List<ApplicationTO> retrieveAllApplicationDetails() throws Exception {
		return applicationDAO.retrieveAllApplicationDetails();
	}

	@PostMapping("/storeApplicationDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody int storeApplicationDetails(@RequestBody ApplicationTO applicationTO) throws Exception {
		return applicationDAO.storeApplicationDetails(applicationTO);
	}

	@PostMapping("/updateApplicationDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public int updateApplicationDetails(@RequestBody ApplicationTO applicationTO) throws Exception {
		return applicationDAO.updateApplicationDetails(applicationTO);
	}

	@RequestMapping(value = "/retrieveBusinessPartnerByApplicationId/{applicationId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody BusinessPartnerTO retrieveBusinessPartnerData(@PathVariable("applicationId") String id)
			throws Exception {
		return businessPartnerDAO.retrieveBusinessPartnerData(Integer.parseInt(id));
	}

	@PostMapping("/storeBusinessPartnerDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeBusinessPartnerDetails(@RequestBody BusinessPartnerTO businessPartnerTO) throws Exception {
		businessPartnerDAO.storeBusinessPartnerDetails(businessPartnerTO);
	}

	@PostMapping("/updateBusinessPartnerDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public int updateBusinessPartnerDetails(@RequestBody BusinessPartnerTO businessPartnerTO) throws Exception {
		return businessPartnerDAO.updateBusinessPartnerDetails(businessPartnerTO);
	}

	@RequestMapping(value = "/retrieveBusinessApplicationByApplicationId/{applicationId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody BusinessApplicationDetailsTO retrieveBusinessApplicationData(
			@PathVariable("applicationId") String applicationId) throws Exception {
		return businessApplicationDetailsDAO.retrieveBusinessApplicationData(Integer.parseInt(applicationId));
	}

	@PostMapping(value = "/storeBusinessApplicationDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeBusinessApplicationDetails(@RequestBody BusinessApplicationDetailsTO businessApplicationDetailsTO)
			throws Exception {
		businessApplicationDetailsDAO.storeUpdateBusinessApplicationData(businessApplicationDetailsTO);
	}

	@PostMapping(value = "/updateBusinessApplicationDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void updateBusinessApplicationDetails(@RequestBody BusinessApplicationDetailsTO businessApplicationDetailsTO)
			throws Exception {
		businessApplicationDetailsDAO.storeUpdateBusinessApplicationData(businessApplicationDetailsTO);
	}

	@RequestMapping(value = "/retrieveApplicationLifecycleByApplicationId/{applicationId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody List<ApplicationLifecycleRetrieveTO> retrieveApplicationLifecycleByApplicationId(
			@PathVariable("applicationId") String id) throws Exception {
		return applicationLifecycleDAO.retrieveApplicationLifecycleByApplicationId(Integer.parseInt(id));
	}

	@PostMapping("/storeApplicationLifecycleDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeApplicationLifecycleDetails(@RequestBody ApplicationLifecycleTO applicationLifecycleTO)
			throws Exception {
		applicationLifecycleDAO.storeApplicationLifecycleDetails(applicationLifecycleTO);
	}
	
	@PostMapping("/updateApplicationLifecycleDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void updateApplicationLifecycleDetails(@RequestBody List<ApplicationLifecycleRetrieveTO> ApplicationLifecycleRetrieveTO) throws Exception {
		applicationLifecycleDAO.updateApplicationLifecycleDetails(ApplicationLifecycleRetrieveTO);
	}

	@RequestMapping(value = "/retrieveRegulatoryByApplicationId/{applicationId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody List<RegulatoryTO> retrieveRegulatoryData(@PathVariable("applicationId") String id)
			throws Exception {
		return regulatoryDAO.retrieveRegulatoryData(Integer.parseInt(id));
	}

	@PostMapping("/storeRegulatoryDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeRegulatoryDetails(@RequestBody RegulatoryTO regulatoryTO) throws Exception {
		regulatoryDAO.storeAndUpdateRegulatoryDetails(regulatoryTO);
	}
	
	@PostMapping("/updateRegulatoryDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void updateRegulatoryDetails(@RequestBody RegulatoryTO regulatoryTO) throws Exception {
		regulatoryDAO.storeAndUpdateRegulatoryDetails(regulatoryTO);
	}

	@RequestMapping(value = "/retrieveServiceManagementByApplicationId/{applicationId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody List<ServiceManagementTO> retrieveServiceManagementByApplicationId(
			@PathVariable("applicationId") String id) throws Exception {
		return serviceManagementDAO.retrieveServiceManagementByApplicationId(Integer.parseInt(id));
	}

	@PostMapping("/storeAndupdateServiceManagementDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeAndupdateServiceManagementDetails(
			@RequestBody List<ServiceManagementTO> ServiceManagementTO) throws Exception {
		serviceManagementDAO.storeAndupdateServiceManagementDetails(ServiceManagementTO);
	}

	@RequestMapping(value = "/retrieveVendorPackageByApplicationId/{applicationId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody VendorPackageTO retrieveVendorPackageData(@PathVariable("applicationId") String applicationId)
			throws Exception {
		return vendorPackageDAO.retrieveVendorPackageData(Integer.parseInt(applicationId));
	}

	@PostMapping("/storeVendorPackageDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeVendorPackageDetails(@RequestBody VendorPackageTO vendorPackageTO) throws Exception {
		vendorPackageDAO.storeVendorPackageDetails(vendorPackageTO);
	}

}
