package com.application.internal.applicationinventoryservice.to;

import org.postgresql.util.PGobject;

public class ChannelTO {
	int id;
	int applicationId;
	String channelType;
	PGobject volume;

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

	public String getchannelType() {
		return channelType;
	}

	public void setchannelType(String channelType) {
		this.channelType = channelType;
	}

	public PGobject getVolume() {
		return volume;
	}

	public void setVolume(PGobject volume) {
		this.volume = volume;
	}

}
