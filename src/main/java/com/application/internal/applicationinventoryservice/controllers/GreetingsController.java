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
import com.application.internal.applicationinventoryservice.databaseintegration.BusinessPartnerDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.DepartmentDAO;
import com.application.internal.applicationinventoryservice.databaseintegration.RegulatoryDAO;
import com.application.internal.applicationinventoryservice.to.ApplicationTO;
import com.application.internal.applicationinventoryservice.to.BusinessPartnerTO;
import com.application.internal.applicationinventoryservice.to.DepartmentTO;
import com.application.internal.applicationinventoryservice.to.RegulatoryTO;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private ApplicationDAO applicationDAO;

	@Autowired
	private BusinessPartnerDAO businessPartnerDAO;

	@Autowired
	private RegulatoryDAO regulatoryDAO;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 * @throws Exception
	 */

	@RequestMapping(value = "/retrieveDepartmentById/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody DepartmentTO retrieveDepartmentData(@PathVariable("id") String id) throws Exception {
		return retrieveDepartmentValueByID(id);
	}

	private DepartmentTO retrieveDepartmentValueByID(String id) throws Exception {
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

	@RequestMapping(value = "/retrieveApplicationById/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody ApplicationTO retrieveApplicationData(@PathVariable("id") String id) throws Exception {
		return retrieveApplicationValueById(id);
	}

	private ApplicationTO retrieveApplicationValueById(String id) throws Exception {
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

	@RequestMapping(value = "/retrieveBusinessPartnerByApplicationId/{application_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody BusinessPartnerTO retrieveBusinessPartnerData(@PathVariable("application_id") String id)
			throws Exception {
		return retrieveBusinessPartnerValueByID(id);
	}

	private BusinessPartnerTO retrieveBusinessPartnerValueByID(String id) throws Exception {
		return businessPartnerDAO.retrieveBusinessPartnerData(Integer.parseInt(id));
	}

	@PostMapping("/storeBusinessPartnerDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeBusinessPartnerDetails(@RequestBody BusinessPartnerTO businessPartnerTO) throws Exception {
		businessPartnerDAO.storeBusinessPartnerDetails(businessPartnerTO);
	}

	@RequestMapping(value = "/retrieveRegulatoryByApplicationId/{application_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public @ResponseBody List<RegulatoryTO> retrieveRegulatoryData(@PathVariable("application_id") String id)
			throws Exception {
		return retrieveRegulatoryValueByID(id);
	}

	private List<RegulatoryTO> retrieveRegulatoryValueByID(String id) throws Exception {
		return regulatoryDAO.retrieveRegulatoryData(Integer.parseInt(id));
	}

	@PostMapping("/storeRegulatoryDetails")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public void storeRegulatoryDetails(@RequestBody RegulatoryTO regulatoryTO) throws Exception {
		regulatoryDAO.storeRegulatoryDetails(regulatoryTO);
	}
}
