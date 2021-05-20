package com.application.internal.applicationinventoryservice.to;

public class ApplicationLifecycleTO {

	private int id;
	private int applicationId;
	public ApplicationLifecycleQuestionAnswerTO[] questionAnswer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationLifecycleQuestionAnswerTO[] getQuestionAndAnswer() {
		return questionAnswer;
	}

	public void setQuestionAndAnswer(ApplicationLifecycleQuestionAnswerTO[] questionAndAnswer) {
		this.questionAnswer = questionAndAnswer;
	}
	
}
