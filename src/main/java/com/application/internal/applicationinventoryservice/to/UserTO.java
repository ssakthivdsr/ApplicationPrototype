package com.application.internal.applicationinventoryservice.to;

import org.postgresql.util.PGobject;

public class UserTO {
	int id;
	int applicationId;
	String userType;
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

	public void setuserType(String userType) {
		this.userType = userType;
	}

	public String getuserType() {

		return userType;
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
