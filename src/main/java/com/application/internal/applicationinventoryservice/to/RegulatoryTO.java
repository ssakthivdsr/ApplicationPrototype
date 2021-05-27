package com.application.internal.applicationinventoryservice.to;

import java.util.List;

public class RegulatoryTO {
	private int regulatoryId;
	private int applicationId;
	private List<String> regulatoryValue;
	
	public int getRegulatoryId() {
		return regulatoryId;
	}
	public void setRegulatoryId(int regulatoryId) {
		this.regulatoryId = regulatoryId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public List<String> getRegulatoryValue() {
		return regulatoryValue;
	}
	public void setRegulatoryValue(List<String> regulatoryValue) {
		this.regulatoryValue = regulatoryValue;
	}
}
