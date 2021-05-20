package com.application.internal.applicationinventoryservice.to;

public class ServiceManagementTO {
	private int serviceManagementId;
	private int applicationId;
	public ServiceManagementQuestionAnswerTO[] questionAnswer;

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

	public ServiceManagementQuestionAnswerTO[] getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(ServiceManagementQuestionAnswerTO[] questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

}
