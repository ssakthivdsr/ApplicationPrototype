package com.application.internal.applicationinventoryservice.to;

public class ApplicationTO {
	private int applicationId;
	private int departmentId;
    private String applicationName;
    private String nameOfTheComponentManager;
    private String smeProvidedByManagers;
    private String nameOfPrimaryTechSME;
    private String nameOfPrimaryBA;
    private String applicationDescription;
    private String lineOfBusiness;
    private String functionality;
    
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getNameOfTheComponentManager() {
		return nameOfTheComponentManager;
	}
	public void setNameOfTheComponentManager(String nameOfTheComponentManager) {
		this.nameOfTheComponentManager = nameOfTheComponentManager;
	}
	public String getSmeProvidedByManagers() {
		return smeProvidedByManagers;
	}
	public void setSmeProvidedByManagers(String smeProvidedByManagers) {
		this.smeProvidedByManagers = smeProvidedByManagers;
	}
	public String getNameOfPrimaryTechSME() {
		return nameOfPrimaryTechSME;
	}
	public void setNameOfPrimaryTechSME(String nameOfPrimaryTechSME) {
		this.nameOfPrimaryTechSME = nameOfPrimaryTechSME;
	}
	public String getNameOfPrimaryBA() {
		return nameOfPrimaryBA;
	}
	public void setNameOfPrimaryBA(String nameOfPrimaryBA) {
		this.nameOfPrimaryBA = nameOfPrimaryBA;
	}
	public String getApplicationDescription() {
		return applicationDescription;
	}
	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
}
