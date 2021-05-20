package com.application.internal.applicationinventoryservice.to;

public class ServiceManagementRetrieveTO {
	private int serviceManagementId;
	private int applicationId;
	private int questionId;
	private String answer;

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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
