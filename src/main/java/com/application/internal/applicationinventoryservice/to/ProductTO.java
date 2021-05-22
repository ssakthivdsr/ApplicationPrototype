package com.application.internal.applicationinventoryservice.to;

import org.postgresql.util.PGobject;

public class ProductTO {

	int id;
	int applicationId;
	String productType;
	PGobject volume;
	PGobject writtenPremiumOfProducts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getapplicationId() {
		return applicationId;
	}

	public void setapplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getproductType() {
		return productType;
	}

	public void setproductType(String productType) {
		this.productType = productType;
	}

	public PGobject getVolume() {
		return volume;
	}

	public void setVolume(PGobject volume) {
		this.volume = volume;
	}

	public PGobject getwrittenPremiumOfProducts() {
		return writtenPremiumOfProducts;
	}

	public void setwrittenPremiumOfProducts(PGobject writtenPremiumOfProducts) {
		this.writtenPremiumOfProducts = writtenPremiumOfProducts;
	}

}
