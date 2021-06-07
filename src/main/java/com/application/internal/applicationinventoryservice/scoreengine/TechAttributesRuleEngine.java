package com.application.internal.applicationinventoryservice.scoreengine;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.internal.applicationinventoryservice.config.ServiceManagementScoreEngine;
import com.application.internal.applicationinventoryservice.to.ServiceManagementTO;

@Component
public class TechAttributesRuleEngine {
	@Autowired
	private ServiceManagementScoreEngine techScoreEngine;

	public int techAttributeTotal(List<ServiceManagementTO> ServiceManagementTO) throws SQLException {
		int techAttributeTotal = 0;
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.ageOfCode(), ServiceManagementTO.get(0).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.codeMovementIntoProduction(), ServiceManagementTO.get(3).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.buisnessImpact(), ServiceManagementTO.get(4).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.ratioOfOutrages(), ServiceManagementTO.get(6).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.numberOfProblems(), ServiceManagementTO.get(7).getAnswer());
		techAttributeTotal = techAttributeTotal + retrieveScore(
				techScoreEngine.percentageOfResolvledProactiveIncidents(), ServiceManagementTO.get(20).getAnswer());
		techAttributeTotal = techAttributeTotal + retrieveScore(
				techScoreEngine.percentageOfSupressedProactiveIncidents(), ServiceManagementTO.get(21).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfReactiveIncidentsReferingKnowledgeItems(),
						ServiceManagementTO.get(22).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfProactiveIncidentsReferingKnowledgeItems(),
						ServiceManagementTO.get(23).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfSev1ReactiveIncidentsThatBreachedSLA(),
						ServiceManagementTO.get(31).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfSev2ReactiveIncidentsThatBreachedSLA(),
						ServiceManagementTO.get(32).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfSev3ReactiveIncidentsThatBreachedSLA(),
						ServiceManagementTO.get(33).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfSev1ProactiveIncidentsThatBreachedSLA(),
						ServiceManagementTO.get(34).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfSev2ProactiveIncidentsThatBreachedSLA(),
						ServiceManagementTO.get(35).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.percentageOfSev3ProactiveIncidentsThatBreachedSLA(),
						ServiceManagementTO.get(36).getAnswer());
		techAttributeTotal = techAttributeTotal + retrieveScore(techScoreEngine.RatioOfApplicationAvailability(),
				ServiceManagementTO.get(37).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.SupportWindow(), ServiceManagementTO.get(40).getAnswer());
		techAttributeTotal = techAttributeTotal
				+ retrieveScore(techScoreEngine.MaintananceDocuments(), ServiceManagementTO.get(41).getAnswer());
		return techAttributeTotal;
	}

	private int retrieveScore(Map<String, String> scoreMap, String selectedAnswer) throws SQLException {
		if (scoreMap.get(selectedAnswer) != null) {
			return Integer.parseInt(scoreMap.get(selectedAnswer));
		} else
			return 0;
	}
}
