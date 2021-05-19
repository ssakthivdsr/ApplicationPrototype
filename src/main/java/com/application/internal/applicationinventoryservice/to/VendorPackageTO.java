package com.application.internal.applicationinventoryservice.to;

public class VendorPackageTO {
	private int vendorPackageId;
	private int applicationId;
	private String engAssociatedManagedServices;
	private String packageType;
	private String name;
	private String engAssociatedWithVendorPackage;
	private String degreeOfCustomization;
	private String hostedLocation;
	private String hostedName;
	private String engAssociatedWithEsternallyHostedVendor;
	private Boolean isLatestSwVersion;
	private String packageVersion;
	private int frequencyOfUpdates;
	private int frequencyOfPatches;

	public int getVendorPackageId() {
		return vendorPackageId;
	}

	public void setVendorPackageId(int vendorPackageId) {
		this.vendorPackageId = vendorPackageId;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getEngAssociatedManagedServices() {
		return engAssociatedManagedServices;
	}

	public void setEngAssociatedManagedServices(String engAssociatedManagedServices) {
		this.engAssociatedManagedServices = engAssociatedManagedServices;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngAssociatedWithVendorPackage() {
		return engAssociatedWithVendorPackage;
	}

	public void setEngAssociatedWithVendorPackage(String engAssociatedWithVendorPackage) {
		this.engAssociatedWithVendorPackage = engAssociatedWithVendorPackage;
	}

	public String getDegreeOfCustomization() {
		return degreeOfCustomization;
	}

	public void setDegreeOfCustomization(String degreeOfCustomization) {
		this.degreeOfCustomization = degreeOfCustomization;
	}

	public String getHostedLocation() {
		return hostedLocation;
	}

	public void setHostedLocation(String hostedLocation) {
		this.hostedLocation = hostedLocation;
	}

	public String getHostedName() {
		return hostedName;
	}

	public void setHostedName(String hostedName) {
		this.hostedName = hostedName;
	}

	public String getEngAssociatedWithEsternallyHostedVendor() {
		return engAssociatedWithEsternallyHostedVendor;
	}

	public void setEngAssociatedWithEsternallyHostedVendor(String engAssociatedWithEsternallyHostedVendor) {
		this.engAssociatedWithEsternallyHostedVendor = engAssociatedWithEsternallyHostedVendor;
	}

	public Boolean getIsLatestSwVersion() {
		return isLatestSwVersion;
	}

	public void setIsLatestSwVersion(Boolean isLatestSwVersion) {
		this.isLatestSwVersion = isLatestSwVersion;
	}

	public String getPackageVersion() {
		return packageVersion;
	}

	public void setPackageVersion(String packageVersion) {
		this.packageVersion = packageVersion;
	}

	public int getFrequencyOfUpdates() {
		return frequencyOfUpdates;
	}

	public void setFrequencyOfUpdates(int frequencyOfUpdates) {
		this.frequencyOfUpdates = frequencyOfUpdates;
	}

	public int getFrequencyOfPatches() {
		return frequencyOfPatches;
	}

	public void setFrequencyOfPatches(int frequencyOfPatches) {
		this.frequencyOfPatches = frequencyOfPatches;
	}

}
