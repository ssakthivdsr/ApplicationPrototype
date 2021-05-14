package com.application.internal.applicationinventoryservice.to;

public class BusinessPartnerTO {
	private int businessPartnerId;
	private int applicationId;
	private String primaryBusinessPartner;
	private String secondaryBusinessPartner;
	private String businessPartnerManagers;
	private String businessPartnerDirectors;
	
	public int getBusinessPartnerId() {
		return businessPartnerId;
	}
	public void setBusinessPartnerId(int businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getPrimaryBusinessPartner() {
		return primaryBusinessPartner;
	}
	public void setPrimaryBusinessPartner(String primaryBusinessPartner) {
		this.primaryBusinessPartner = primaryBusinessPartner;
	}
	public String getSecondaryBusinessPartner() {
		return secondaryBusinessPartner;
	}
	public void setSecondaryBusinessPartner(String secondaryBusinessPartner) {
		this.secondaryBusinessPartner = secondaryBusinessPartner;
	}
	public String getBusinessPartnerManagers() {
		return businessPartnerManagers;
	}
	public void setBusinessPartnerManagers(String businessPartnerManagers) {
		this.businessPartnerManagers = businessPartnerManagers;
	}
	public String getBusinessPartnerDirectors() {
		return businessPartnerDirectors;
	}
	public void setBusinessPartnerDirectors(String businessPartnerDirectors) {
		this.businessPartnerDirectors = businessPartnerDirectors;
	}
}
