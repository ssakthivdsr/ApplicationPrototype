package com.application.internal.applicationinventoryservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.internal.applicationinventoryservice.databaseintegration.DepartmentDAO;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private DepartmentDAO departmentDAO;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     * @throws Exception 
     */
    @RequestMapping(value = "/retrieveDepartmentData/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retrieveDepartmentData(@PathVariable("name") String name) throws Exception {
        return "Department details for departmentId:" + name + " is : " + retrieveValueFromDate(name);
    }
    
    private String retrieveValueFromDate(String id) throws Exception {
    	return departmentDAO.retrieveDepartmentData(id);
    }
    
    @RequestMapping(value = "/storeDepartmentData/{departmentname}/{departmentowner}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void storeDepartmentData(@PathVariable("departmentname") String departmentName,@PathVariable("departmentowner") String departmentOwner) throws Exception {
    	departmentDAO.storeDepartmentData(departmentName, departmentOwner);
    }
    
    
}
