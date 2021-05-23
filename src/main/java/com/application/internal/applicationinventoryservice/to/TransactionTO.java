package com.application.internal.applicationinventoryservice.to;

import org.postgresql.util.PGobject;

public class TransactionTO {
	int id;
	int applicationId;
	String transactionType;
	PGobject volume;
	TransactionObject volumeObject;

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

	public String gettransactionType() {
		return transactionType;
	}

	public void settransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public PGobject getVolume() {
		return volume;
	}

	public void setVolume(PGobject volume) {
		this.volume = volume;
	}

	public TransactionObject getVolumeObject() {
		return volumeObject;
	}

	public void setVolumeObject(TransactionObject volumeObject) {
		this.volumeObject = volumeObject;
	}

}
