package com.application.internal.applicationinventoryservice.to;

public class ServiceManagementTO {
	private int serviceManagementId;
	private int applicationId;
	private int questionId;
	private String ServiceManagementAnswer;
	
	public int getServiceManagementId() {
		return serviceManagementId;
	}
	public void setServiceManagementId(int serviceManagementId) {
		this.serviceManagementId = serviceManagementId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getServiceManagementAnswer() {
		return ServiceManagementAnswer;
	}
	public void setServiceManagementAnswer(String serviceManagementAnswer) {
		ServiceManagementAnswer = serviceManagementAnswer;
	}
}
