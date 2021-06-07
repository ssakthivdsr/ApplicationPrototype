package com.application.internal.applicationinventoryservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.Configuration;
@Component
public class ServiceManagementScoreEngine {

	private Map<String, String> ageOfCode;
	private Map<String, String> codeMovementIntoProduction;
	private Map<String, String> buisnessImpact;
	private Map<String, String> ratioOfOutrages;
	private Map<String, String> numberOfProblems;
	private Map<String, String> percentageOfResolvledProactiveIncidents;
	private Map<String, String> percentageOfSupressedProactiveIncidents;
	private Map<String, String> percentageOfReactiveIncidentsReferingKnowledgeItems;
	private Map<String, String> percentageOfProactiveIncidentsReferingKnowledgeItems;
	private Map<String, String> percentageOfSev1ReactiveIncidentsThatBreachedSLA;
	private Map<String, String> percentageOfSev2ReactiveIncidentsThatBreachedSLA;
	private Map<String, String> percentageOfSev3ReactiveIncidentsThatBreachedSLA;
	private Map<String, String> percentageOfSev1ProactiveIncidentsThatBreachedSLA;
	private Map<String, String> percentageOfSev2ProactiveIncidentsThatBreachedSLA;
	private Map<String, String> percentageOfSev3ProactiveIncidentsThatBreachedSLA;
	private Map<String, String> RatioOfApplicationAvailability;
	private Map<String, String> SupportWindow;
	private Map<String, String> MaintananceDocuments;

	public ServiceManagementScoreEngine() {
		ageOfCode = ageOfCode();
		codeMovementIntoProduction = codeMovementIntoProduction();
		buisnessImpact = buisnessImpact();
		ratioOfOutrages = ratioOfOutrages();
		numberOfProblems = numberOfProblems();
		percentageOfResolvledProactiveIncidents = percentageOfResolvledProactiveIncidents();
		percentageOfSupressedProactiveIncidents = percentageOfSupressedProactiveIncidents();
		percentageOfReactiveIncidentsReferingKnowledgeItems = percentageOfReactiveIncidentsReferingKnowledgeItems();
		percentageOfProactiveIncidentsReferingKnowledgeItems = percentageOfProactiveIncidentsReferingKnowledgeItems();
		percentageOfSev1ReactiveIncidentsThatBreachedSLA = percentageOfSev1ReactiveIncidentsThatBreachedSLA();
		percentageOfSev2ReactiveIncidentsThatBreachedSLA = percentageOfSev2ReactiveIncidentsThatBreachedSLA();
		percentageOfSev3ReactiveIncidentsThatBreachedSLA = percentageOfSev3ReactiveIncidentsThatBreachedSLA();
		percentageOfSev1ProactiveIncidentsThatBreachedSLA = percentageOfSev1ProactiveIncidentsThatBreachedSLA();
		percentageOfSev2ProactiveIncidentsThatBreachedSLA = percentageOfSev2ProactiveIncidentsThatBreachedSLA();
		percentageOfSev3ProactiveIncidentsThatBreachedSLA = percentageOfSev3ProactiveIncidentsThatBreachedSLA();
		RatioOfApplicationAvailability = RatioOfApplicationAvailability();
		SupportWindow = SupportWindow();
		MaintananceDocuments = MaintananceDocuments();

	}

	public Map<String, String> ageOfCode() {

		Map<String, String> ageOfCode = new HashMap<>();
		ageOfCode.put("Less than 2 years", "15");
		ageOfCode.put("2-5 years", "10");
		ageOfCode.put("5-10 years", "6");
		ageOfCode.put("10-20 years", "3");
		ageOfCode.put("> 20 years", "1");
		return ageOfCode;
	}

	public Map<String, String> codeMovementIntoProduction() {

		Map<String, String> codeMovementIntoProduction = new HashMap<>();
		codeMovementIntoProduction.put("About 1-2 code changes into production every quarter", "15");
		codeMovementIntoProduction.put("About 5 code changes into production every quarter", "10");
		codeMovementIntoProduction.put("About 1-2 code changes into production every month", "6");
		codeMovementIntoProduction.put("About 3-4 code changes into production every month", "3");
		codeMovementIntoProduction.put("About 1-2 code changes into production every week", "1");
		return codeMovementIntoProduction;
	}

	public Map<String, String> buisnessImpact() {

		Map<String, String> buisnessImpact = new HashMap<>();
		buisnessImpact.put("Not critical to day to day functioning", "1");
		buisnessImpact.put("Manageable with no visibility to external world", "3");
		buisnessImpact.put("Affects business image", "6");
		buisnessImpact.put("Critical to top mgmt and customers of client", "10");
		buisnessImpact.put("Affects business financially on downtime", "15");
		return buisnessImpact;
	}

	public Map<String, String> ratioOfOutrages() {

		Map<String, String> ratioOfOutrages = new HashMap<>();
		ratioOfOutrages.put("Less than 0.95", "15");
		ratioOfOutrages.put("0.95-0.99", "10");
		ratioOfOutrages.put("1", "6");
		ratioOfOutrages.put("1.01-1.05", "3");
		ratioOfOutrages.put(">1.05", "1");
		return ratioOfOutrages;
	}

	public Map<String, String> numberOfProblems() {

		Map<String, String> numberOfProblems = new HashMap<>();
		numberOfProblems.put("No code changes due to bugs in the last 6 months", "15");
		numberOfProblems.put("No code changes due to bugs in the last 3 months", "10");
		numberOfProblems.put("Code changes once a month", "6");
		numberOfProblems.put("Code changes alternate week", "3");
		numberOfProblems.put("Problems repeat every month with code changes every week", "1");
		return numberOfProblems;
	}

	public Map<String, String> percentageOfResolvledProactiveIncidents() {

		Map<String, String> percentageOfResolvledProactiveIncidents = new HashMap<>();
		percentageOfResolvledProactiveIncidents.put("Less than 50%", "1");
		percentageOfResolvledProactiveIncidents.put("50-60%", "3");
		percentageOfResolvledProactiveIncidents.put("60-70%", "6");
		percentageOfResolvledProactiveIncidents.put("70-80%", "10");
		percentageOfResolvledProactiveIncidents.put(">80%", "15");
		return percentageOfResolvledProactiveIncidents;
	}

	public Map<String, String> percentageOfSupressedProactiveIncidents() {

		Map<String, String> percentageOfSupressedProactiveIncidents = new HashMap<>();
		percentageOfSupressedProactiveIncidents.put("Less than 50%", "1");
		percentageOfSupressedProactiveIncidents.put("50-60%", "3");
		percentageOfSupressedProactiveIncidents.put("60-70%", "6");
		percentageOfSupressedProactiveIncidents.put("70-80%", "10");
		percentageOfSupressedProactiveIncidents.put(">80%", "15");
		return percentageOfSupressedProactiveIncidents;
	}

	public Map<String, String> percentageOfReactiveIncidentsReferingKnowledgeItems() {

		Map<String, String> percentageOfReactiveIncidentsReferingKnowledgeItems = new HashMap<>();
		percentageOfReactiveIncidentsReferingKnowledgeItems.put("Less than 50%", "1");
		percentageOfReactiveIncidentsReferingKnowledgeItems.put("50-60%", "3");
		percentageOfReactiveIncidentsReferingKnowledgeItems.put("60-70%", "6");
		percentageOfReactiveIncidentsReferingKnowledgeItems.put("70-80%", "10");
		percentageOfReactiveIncidentsReferingKnowledgeItems.put(">80%", "15");
		return percentageOfReactiveIncidentsReferingKnowledgeItems;
	}

	public Map<String, String> percentageOfProactiveIncidentsReferingKnowledgeItems() {

		Map<String, String> percentageOfProactiveIncidentsReferingKnowledgeItems = new HashMap<>();
		percentageOfProactiveIncidentsReferingKnowledgeItems.put("Less than 50%", "1");
		percentageOfProactiveIncidentsReferingKnowledgeItems.put("50-60%", "3");
		percentageOfProactiveIncidentsReferingKnowledgeItems.put("60-70%", "6");
		percentageOfProactiveIncidentsReferingKnowledgeItems.put("70-80%", "10");
		percentageOfProactiveIncidentsReferingKnowledgeItems.put(">80%", "15");
		return percentageOfProactiveIncidentsReferingKnowledgeItems;
	}

	public Map<String, String> percentageOfSev1ReactiveIncidentsThatBreachedSLA() {

		Map<String, String> percentageOfSev1ReactiveIncidentsThatBreachedSLA = new HashMap<>();
		percentageOfSev1ReactiveIncidentsThatBreachedSLA.put("Less than 1%", "15");
		percentageOfSev1ReactiveIncidentsThatBreachedSLA.put("1-3%", "10");
		percentageOfSev1ReactiveIncidentsThatBreachedSLA.put("3-6%", "6");
		percentageOfSev1ReactiveIncidentsThatBreachedSLA.put("6-10%", "3");
		percentageOfSev1ReactiveIncidentsThatBreachedSLA.put(">10%", "1");
		return percentageOfSev1ReactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> percentageOfSev2ReactiveIncidentsThatBreachedSLA() {

		Map<String, String> percentageOfSev2ReactiveIncidentsThatBreachedSLA = new HashMap<>();
		percentageOfSev2ReactiveIncidentsThatBreachedSLA.put("Less than 3%", "15");
		percentageOfSev2ReactiveIncidentsThatBreachedSLA.put("3-5%", "10");
		percentageOfSev2ReactiveIncidentsThatBreachedSLA.put("5-10%", "6");
		percentageOfSev2ReactiveIncidentsThatBreachedSLA.put("10-15%", "3");
		percentageOfSev2ReactiveIncidentsThatBreachedSLA.put(">15%", "1");
		return percentageOfSev2ReactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> percentageOfSev3ReactiveIncidentsThatBreachedSLA() {

		Map<String, String> percentageOfSev3ReactiveIncidentsThatBreachedSLA = new HashMap<>();
		percentageOfSev3ReactiveIncidentsThatBreachedSLA.put("Less than 5%", "15");
		percentageOfSev3ReactiveIncidentsThatBreachedSLA.put("5-8%", "10");
		percentageOfSev3ReactiveIncidentsThatBreachedSLA.put("8-12%", "6");
		percentageOfSev3ReactiveIncidentsThatBreachedSLA.put("12-20%", "3");
		percentageOfSev3ReactiveIncidentsThatBreachedSLA.put(">20%", "1");
		return percentageOfSev3ReactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> percentageOfSev1ProactiveIncidentsThatBreachedSLA() {

		Map<String, String> percentageOfSev1ProactiveIncidentsThatBreachedSLA = new HashMap<>();
		percentageOfSev1ProactiveIncidentsThatBreachedSLA.put("Less than 1%", "15");
		percentageOfSev1ProactiveIncidentsThatBreachedSLA.put("1-3%", "10");
		percentageOfSev1ProactiveIncidentsThatBreachedSLA.put("3-6%", "6");
		percentageOfSev1ProactiveIncidentsThatBreachedSLA.put("6-10%", "3");
		percentageOfSev1ProactiveIncidentsThatBreachedSLA.put(">10%", "1");
		return percentageOfSev1ProactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> percentageOfSev2ProactiveIncidentsThatBreachedSLA() {

		Map<String, String> percentageOfSev2ProactiveIncidentsThatBreachedSLA = new HashMap<>();
		percentageOfSev2ProactiveIncidentsThatBreachedSLA.put("Less than 3%", "15");
		percentageOfSev2ProactiveIncidentsThatBreachedSLA.put("3-5%", "10");
		percentageOfSev2ProactiveIncidentsThatBreachedSLA.put("5-10%", "6");
		percentageOfSev2ProactiveIncidentsThatBreachedSLA.put("10-15%", "3");
		percentageOfSev2ProactiveIncidentsThatBreachedSLA.put(">15%", "1");
		return percentageOfSev2ProactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> percentageOfSev3ProactiveIncidentsThatBreachedSLA() {

		Map<String, String> percentageOfSev3ProactiveIncidentsThatBreachedSLA = new HashMap<>();
		percentageOfSev3ProactiveIncidentsThatBreachedSLA.put("Less than 5%", "15");
		percentageOfSev3ProactiveIncidentsThatBreachedSLA.put("5-8%", "10");
		percentageOfSev3ProactiveIncidentsThatBreachedSLA.put("8-12%", "6");
		percentageOfSev3ProactiveIncidentsThatBreachedSLA.put("12-20%", "3");
		percentageOfSev3ProactiveIncidentsThatBreachedSLA.put(">20%", "1");
		return percentageOfSev3ProactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> RatioOfApplicationAvailability() {

		Map<String, String> RatioOfApplicationAvailability = new HashMap<>();
		RatioOfApplicationAvailability.put("Less than 0.99", "15");
		RatioOfApplicationAvailability.put("0.99", "10");
		RatioOfApplicationAvailability.put("1", "6");
		RatioOfApplicationAvailability.put("1.01", "3");
		RatioOfApplicationAvailability.put(">1.01", "1");
		return RatioOfApplicationAvailability;
	}

	public Map<String, String> SupportWindow() {

		Map<String, String> SupportWindow = new HashMap<>();
		SupportWindow.put("5 work days a week (8 hours)", "1");
		SupportWindow.put("7 work days a week (8 hours)", "3");
		SupportWindow.put("16X5 days a week", "6");
		SupportWindow.put("24X6 days a week", "10");
		SupportWindow.put("7 work days a week (24 hours)", "15");
		return SupportWindow;
	}

	public Map<String, String> MaintananceDocuments() {

		Map<String, String> MaintananceDocuments = new HashMap<>();
		MaintananceDocuments.put("Maintanance (last 2 years) formally documented", "15");
		MaintananceDocuments.put("Maintanance (last 1 year) formally documented", "10");
		MaintananceDocuments.put("Maintanance (last 6 months) formally documented", "6");
		MaintananceDocuments.put("Some change requests documented but some are not", "3");
		MaintananceDocuments.put("No maintance document available for code", "1");
		return MaintananceDocuments;
	}

	public Map<String, String> getAgeOfCode() {
		return ageOfCode;
	}

	public void setAgeOfCode(Map<String, String> ageOfCode) {
		this.ageOfCode = ageOfCode;
	}

	public Map<String, String> getCodeMovementIntoProduction() {
		return codeMovementIntoProduction;
	}

	public void setCodeMovementIntoProduction(Map<String, String> codeMovementIntoProduction) {
		this.codeMovementIntoProduction = codeMovementIntoProduction;
	}

	public Map<String, String> getBuisnessImpact() {
		return buisnessImpact;
	}

	public void setBuisnessImpact(Map<String, String> buisnessImpact) {
		this.buisnessImpact = buisnessImpact;
	}

	public Map<String, String> getRatioOfOutrages() {
		return ratioOfOutrages;
	}

	public void setRatioOfOutrages(Map<String, String> ratioOfOutrages) {
		this.ratioOfOutrages = ratioOfOutrages;
	}

	public Map<String, String> getNumberOfProblems() {
		return numberOfProblems;
	}

	public void setNumberOfProblems(Map<String, String> numberOfProblems) {
		this.numberOfProblems = numberOfProblems;
	}

	public Map<String, String> getPercentageOfResolvledProactiveIncidents() {
		return percentageOfResolvledProactiveIncidents;
	}

	public void setPercentageOfResolvledProactiveIncidents(
			Map<String, String> percentageOfResolvledProactiveIncidents) {
		this.percentageOfResolvledProactiveIncidents = percentageOfResolvledProactiveIncidents;
	}

	public Map<String, String> getPercentageOfSupressedProactiveIncidents() {
		return percentageOfSupressedProactiveIncidents;
	}

	public void setPercentageOfSupressedProactiveIncidents(
			Map<String, String> percentageOfSupressedProactiveIncidents) {
		this.percentageOfSupressedProactiveIncidents = percentageOfSupressedProactiveIncidents;
	}

	public Map<String, String> getPercentageOfReactiveIncidentsReferingKnowledgeItems() {
		return percentageOfReactiveIncidentsReferingKnowledgeItems;
	}

	public void setPercentageOfReactiveIncidentsReferingKnowledgeItems(
			Map<String, String> percentageOfReactiveIncidentsReferingKnowledgeItems) {
		this.percentageOfReactiveIncidentsReferingKnowledgeItems = percentageOfReactiveIncidentsReferingKnowledgeItems;
	}

	public Map<String, String> getPercentageOfProactiveIncidentsReferingKnowledgeItems() {
		return percentageOfProactiveIncidentsReferingKnowledgeItems;
	}

	public void setPercentageOfProactiveIncidentsReferingKnowledgeItems(
			Map<String, String> percentageOfProactiveIncidentsReferingKnowledgeItems) {
		this.percentageOfProactiveIncidentsReferingKnowledgeItems = percentageOfProactiveIncidentsReferingKnowledgeItems;
	}

	public Map<String, String> getPercentageOfSev1ReactiveIncidentsThatBreachedSLA() {
		return percentageOfSev1ReactiveIncidentsThatBreachedSLA;
	}

	public void setPercentageOfSev1ReactiveIncidentsThatBreachedSLA(
			Map<String, String> percentageOfSev1ReactiveIncidentsThatBreachedSLA) {
		this.percentageOfSev1ReactiveIncidentsThatBreachedSLA = percentageOfSev1ReactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> getPercentageOfSev2ReactiveIncidentsThatBreachedSLA() {
		return percentageOfSev2ReactiveIncidentsThatBreachedSLA;
	}

	public void setPercentageOfSev2ReactiveIncidentsThatBreachedSLA(
			Map<String, String> percentageOfSev2ReactiveIncidentsThatBreachedSLA) {
		this.percentageOfSev2ReactiveIncidentsThatBreachedSLA = percentageOfSev2ReactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> getPercentageOfSev3ReactiveIncidentsThatBreachedSLA() {
		return percentageOfSev3ReactiveIncidentsThatBreachedSLA;
	}

	public void setPercentageOfSev3ReactiveIncidentsThatBreachedSLA(
			Map<String, String> percentageOfSev3ReactiveIncidentsThatBreachedSLA) {
		this.percentageOfSev3ReactiveIncidentsThatBreachedSLA = percentageOfSev3ReactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> getPercentageOfSev1ProactiveIncidentsThatBreachedSLA() {
		return percentageOfSev1ProactiveIncidentsThatBreachedSLA;
	}

	public void setPercentageOfSev1ProactiveIncidentsThatBreachedSLA(
			Map<String, String> percentageOfSev1ProactiveIncidentsThatBreachedSLA) {
		this.percentageOfSev1ProactiveIncidentsThatBreachedSLA = percentageOfSev1ProactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> getPercentageOfSev2ProactiveIncidentsThatBreachedSLA() {
		return percentageOfSev2ProactiveIncidentsThatBreachedSLA;
	}

	public void setPercentageOfSev2ProactiveIncidentsThatBreachedSLA(
			Map<String, String> percentageOfSev2ProactiveIncidentsThatBreachedSLA) {
		this.percentageOfSev2ProactiveIncidentsThatBreachedSLA = percentageOfSev2ProactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> getPercentageOfSev3ProactiveIncidentsThatBreachedSLA() {
		return percentageOfSev3ProactiveIncidentsThatBreachedSLA;
	}

	public void setPercentageOfSev3ProactiveIncidentsThatBreachedSLA(
			Map<String, String> percentageOfSev3ProactiveIncidentsThatBreachedSLA) {
		this.percentageOfSev3ProactiveIncidentsThatBreachedSLA = percentageOfSev3ProactiveIncidentsThatBreachedSLA;
	}

	public Map<String, String> getRatioOfApplicationAvailability() {
		return RatioOfApplicationAvailability;
	}

	public void setRatioOfApplicationAvailability(Map<String, String> ratioOfApplicationAvailability) {
		RatioOfApplicationAvailability = ratioOfApplicationAvailability;
	}

	public Map<String, String> getSupportWindow() {
		return SupportWindow;
	}

	public void setSupportWindow(Map<String, String> supportWindow) {
		SupportWindow = supportWindow;
	}

	public Map<String, String> getMaintananceDocuments() {
		return MaintananceDocuments;
	}

	public void setMaintananceDocuments(Map<String, String> maintananceDocuments) {
		MaintananceDocuments = maintananceDocuments;
	}

}
