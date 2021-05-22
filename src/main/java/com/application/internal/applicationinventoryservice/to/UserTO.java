package com.application.internal.applicationinventoryservice.to;

import org.postgresql.util.PGobject;

public class UserTO {
	public int id;
	public int applicationId;
	public String userType;
	public PGobject volume;

	public PGobject getVolume() {
		return volume;
	}

	public void setVolume(PGobject volume) {
		this.volume = volume;
	}

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

}
