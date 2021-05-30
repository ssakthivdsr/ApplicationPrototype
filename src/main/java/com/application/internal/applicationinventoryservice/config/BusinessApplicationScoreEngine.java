package com.application.internal.applicationinventoryservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

//@Configuration
@Component
public class BusinessApplicationScoreEngine {
	
	private Map<String,String> userTypes;
	private Map<String,String> userTypesTransactionVolume;
	private Map<String,String> diffInternetAndAgent;
	private Map<String,String> uwAutomated;
	private Map<String,String> uwAutomatedTransactionVolumeSTP;
	private Map<String,String> transactionTypeOverriden;
	private Map<String,String> projectWBPresence;
	private Map<String,String> productLaunchTime;
	private Map<String,String> pasProductCount;
	private Map<String,String> pasProductVolume;
	private Map<String,String> pasProductWrittenPremium;
	private Map<String,String> productVariants;
	private Map<String,String> systemEndorsement;
	private Map<String,String> backToPreviousTerms;
	private Map<String,String> ratingAutomated;
	private Map<String,String> ratingAutomatedIntegrated;
	private Map<String,String> internalInterfacingAppCount;
	private Map<String,String> externalInterfacingAppCount;
	private Map<String,String> numberOfTransDiffBetweenChannels;
	private Map<String,String> pasUsedStateCount;
	private Map<String,String> pasUsedStateCountForLessSates;
	private Map<String,String> npsScore;
	private Map<String,String> csScore;
	
	public BusinessApplicationScoreEngine() {
		userTypes = userTypes();
		userTypesTransactionVolume = userTypesTransactionVolume();
		diffInternetAndAgent = diffInternetAndAgent();
		uwAutomated = uwAutomated();
		uwAutomatedTransactionVolumeSTP = uwAutomatedTransactionVolumeSTP();
		transactionTypeOverriden = transactionTypeOverriden();
		projectWBPresence = projectWBPresence();
		productLaunchTime = productLaunchTime();
		pasProductCount = pasProductCount();
		pasProductVolume = pasProductVolume();
		pasProductWrittenPremium = pasProductWrittenPremium();
		productVariants = pasProductWrittenPremium();
		systemEndorsement = systemEndorsement();
		backToPreviousTerms = backToPreviousTerms();
		ratingAutomated = ratingAutomated();
		ratingAutomatedIntegrated = ratingAutomatedIntegrated();
		internalInterfacingAppCount = internalInterfacingAppCount();
		externalInterfacingAppCount = externalInterfacingAppCount();
		numberOfTransDiffBetweenChannels = numberOfTransDiffBetweenChannels();
		pasUsedStateCount = pasUsedStateCount();
		pasUsedStateCountForLessSates = pasUsedStateCountForLessSates();
		npsScore = npsScore() ;
		csScore = csScore();
	}
	
	
	public Map<String,String> userTypes() {
		Map<String,String> userTypes = new HashMap<>();
		userTypes.put("1", "1");
		userTypes.put("2", "4");
		userTypes.put("3", "10");
		userTypes.put("4", "20");
		userTypes.put(">4", "35");
		return userTypes;
	}
	
	public Map<String,String> userTypesTransactionVolume() {
		Map<String,String> userTypesTransactionVolume = new HashMap<>();
		userTypesTransactionVolume.put("<5 Million", "1");
		userTypesTransactionVolume.put("5-10 Million", "4");
		userTypesTransactionVolume.put("10-25 Million", "10");
		userTypesTransactionVolume.put("25-50 Million", "20");
		userTypesTransactionVolume.put("50-100 Million", "35");
		userTypesTransactionVolume.put(">100 Million", "35");
		return userTypesTransactionVolume;
	}
	
	public Map<String,String> diffInternetAndAgent() {
		Map<String,String> diffInternetAndAgent = new HashMap<>();
		diffInternetAndAgent.put("yes", "15");
		diffInternetAndAgent.put("no", "3");
		return diffInternetAndAgent;
	}
	
	public Map<String,String> uwAutomated() {
		Map<String,String> uwAutomated = new HashMap<>();
		uwAutomated.put("manual", "3");
		uwAutomated.put("automated", "15");
		return uwAutomated;
	}
	
	public Map<String,String> uwAutomatedTransactionVolumeSTP() {
		Map<String,String> uwAutomatedTransactionVolumeSTP = new HashMap<>();
		uwAutomatedTransactionVolumeSTP.put("<10%", "56");
		uwAutomatedTransactionVolumeSTP.put("10-20%", "35");
		uwAutomatedTransactionVolumeSTP.put("20-35%", "20");
		uwAutomatedTransactionVolumeSTP.put(">50%", "10");
		return uwAutomatedTransactionVolumeSTP;
	}
	
	public Map<String,String> transactionTypeOverriden() {
		Map<String,String> transactionTypeOverriden = new HashMap<>();
		transactionTypeOverriden.put("<10%", "56");
		transactionTypeOverriden.put("10-20%", "35");
		transactionTypeOverriden.put("20-35%", "20");
		transactionTypeOverriden.put(">50%", "10");
		return transactionTypeOverriden;
	}
	
	public Map<String,String> projectWBPresence() {
		Map<String,String> projectWBPresence = new HashMap<>();
		projectWBPresence.put("yes", "35");
		projectWBPresence.put("no", "1");
		return projectWBPresence;
	}
	
	public Map<String,String> productLaunchTime() {
		Map<String,String> productLaunchTime = new HashMap<>();
		productLaunchTime.put("<3 months", "1");
		productLaunchTime.put("3-6 months", "4");
		productLaunchTime.put("6-12 months", "10");
		productLaunchTime.put(">12 months", "20");
		//productLaunchTime.put("?????????????", "35");
		return productLaunchTime;
	}
	
	
	public Map<String,String> pasProductCount() {
		Map<String,String> userTypes = new HashMap<>();
		userTypes.put("1", "1");
		userTypes.put("2", "4");
		userTypes.put("3", "10");
		userTypes.put("4", "20");
		userTypes.put(">4", "35");
		return userTypes;
	}
	
	public Map<String,String> pasProductVolume() {
		Map<String,String> pasProductVolume = new HashMap<>();
		pasProductVolume.put("1", "1");
		pasProductVolume.put("2", "4");
		pasProductVolume.put("3", "10");
		pasProductVolume.put("4", "20");
		pasProductVolume.put(">4", "35");
		return pasProductVolume;
	}
	
	public Map<String,String> pasProductWrittenPremium() {
		Map<String,String> pasProductWrittenPremium = new HashMap<>();
		pasProductWrittenPremium.put("<500 MUSD", "1");
		pasProductWrittenPremium.put("500 MUSD - 1 BUSD", "4");
		pasProductWrittenPremium.put("1 - 5 BUSD", "10");
		pasProductWrittenPremium.put("5 - 10 BUSD", "20");
		pasProductWrittenPremium.put(">10 BUSD", "35");
		return pasProductWrittenPremium;
	}
	
	public Map<String,String> productVariants() {
		Map<String,String> productVariants = new HashMap<>();
		productVariants.put("<10 Products", "1");
		productVariants.put("10-20 Products", "4");
		productVariants.put(">20 Products", "10");
//		productVariants.put("???", "10");
//		productVariants.put("???", "10");
		return productVariants;
	}
	
	
	public Map<String,String> systemEndorsement() {
		Map<String,String> systemEndorsement = new HashMap<>();
		systemEndorsement.put("1", "1");
		systemEndorsement.put("2", "4");
		systemEndorsement.put("3", "10");
		systemEndorsement.put("4", "20");
		systemEndorsement.put(">4", "35");
		return systemEndorsement;
	}
	
	
	public Map<String,String> backToPreviousTerms() {
		Map<String,String> backToPreviousTerms = new HashMap<>();
		backToPreviousTerms.put("1", "1");
		backToPreviousTerms.put("2", "4");
		backToPreviousTerms.put("3", "10");
		backToPreviousTerms.put("4", "20");
		backToPreviousTerms.put(">4", "35");
		return backToPreviousTerms;
	}
	
	
	public Map<String,String> ratingAutomated() {
		Map<String,String> ratingAutomated = new HashMap<>();
		ratingAutomated.put("manual", "1");
		ratingAutomated.put("automated", "35");
		return ratingAutomated;
	}
	
	
	public Map<String,String> ratingAutomatedIntegrated() {
		Map<String,String> ratingAutomated = new HashMap<>();
		ratingAutomated.put("manual", "1");
		ratingAutomated.put("automated", "35");
		return ratingAutomated;
	}
	
	
	public Map<String,String> internalInterfacingAppCount() {
		Map<String,String> internalInterfacingAppCount = new HashMap<>();
		internalInterfacingAppCount.put("<5 Interfaces", "1");
		internalInterfacingAppCount.put("5-10 Interfaces", "4");
		internalInterfacingAppCount.put("> 10 Interfaces", "10");
		return internalInterfacingAppCount;
	}
	
	public Map<String,String> externalInterfacingAppCount() {
		Map<String,String> externalInterfacingAppCount = new HashMap<>();
		externalInterfacingAppCount.put("<5 Interfaces", "1");
		externalInterfacingAppCount.put("5-10 Interfaces", "4");
		externalInterfacingAppCount.put("> 10 Interfaces", "10");
		return externalInterfacingAppCount;
	}
	
	
	public Map<String,String> numberOfTransDiffBetweenChannels() {
		Map<String,String> NumberOfTransDiffBetweenChannels = new HashMap<>();
		NumberOfTransDiffBetweenChannels.put("1", "1");
		NumberOfTransDiffBetweenChannels.put("2", "4");
		NumberOfTransDiffBetweenChannels.put("3", "10");
		NumberOfTransDiffBetweenChannels.put("4", "20");
		NumberOfTransDiffBetweenChannels.put(">4", "35");
		return NumberOfTransDiffBetweenChannels;
	}
	
	
	public Map<String,String> pasUsedStateCount() {
		Map<String,String> pasUsedStateCount = new HashMap<>();
		pasUsedStateCount.put("<10 States", "1");
		pasUsedStateCount.put("10-20 States", "4");
		pasUsedStateCount.put("20-30 States", "10");
		pasUsedStateCount.put("30-40 States", "20");
		pasUsedStateCount.put(">40 States", "20");
		return pasUsedStateCount;
	}
	
	
	public Map<String,String> pasUsedStateCountForLessSates() {
		Map<String,String> pasUsedStateCountForLessSates = new HashMap<>();
		pasUsedStateCountForLessSates.put("<10 States", "1");
		pasUsedStateCountForLessSates.put("10-20 States", "4");
		pasUsedStateCountForLessSates.put("20-30 States", "10");
		pasUsedStateCountForLessSates.put("30-40 States", "20");
		pasUsedStateCountForLessSates.put(">40 States", "20");
		return pasUsedStateCountForLessSates;
	}
	
	
	public Map<String,String> npsScore() {
		Map<String,String> npsScore = new HashMap<>();
		npsScore.put("0-30", "1");
		npsScore.put("30-70", "10");
		npsScore.put("70-100", "21");
		return npsScore;
	}
	
	
	public Map<String,String> csScore() {
		Map<String,String> csScore = new HashMap<>();
		csScore.put("<75%", "1");
		csScore.put("75-80%", "4");
		csScore.put("80-85%", "10");
		csScore.put("85-90%", "20");
		csScore.put(">90%", "35");
		return csScore;
	}


	public Map<String, String> getUserTypes() {
		return userTypes;
	}


	public void setUserTypes(Map<String, String> userTypes) {
		this.userTypes = userTypes;
	}


	public Map<String, String> getUserTypesTransactionVolume() {
		return userTypesTransactionVolume;
	}


	public void setUserTypesTransactionVolume(Map<String, String> userTypesTransactionVolume) {
		this.userTypesTransactionVolume = userTypesTransactionVolume;
	}


	public Map<String, String> getDiffInternetAndAgent() {
		return diffInternetAndAgent;
	}


	public void setDiffInternetAndAgent(Map<String, String> diffInternetAndAgent) {
		this.diffInternetAndAgent = diffInternetAndAgent;
	}


	public Map<String, String> getUwAutomated() {
		return uwAutomated;
	}


	public void setUwAutomated(Map<String, String> uwAutomated) {
		this.uwAutomated = uwAutomated;
	}


	public Map<String, String> getUwAutomatedTransactionVolumeSTP() {
		return uwAutomatedTransactionVolumeSTP;
	}


	public void setUwAutomatedTransactionVolumeSTP(Map<String, String> uwAutomatedTransactionVolumeSTP) {
		this.uwAutomatedTransactionVolumeSTP = uwAutomatedTransactionVolumeSTP;
	}


	public Map<String, String> getTransactionTypeOverriden() {
		return transactionTypeOverriden;
	}


	public void setTransactionTypeOverriden(Map<String, String> transactionTypeOverriden) {
		this.transactionTypeOverriden = transactionTypeOverriden;
	}


	public Map<String, String> getProjectWBPresence() {
		return projectWBPresence;
	}


	public void setProjectWBPresence(Map<String, String> projectWBPresence) {
		this.projectWBPresence = projectWBPresence;
	}


	public Map<String, String> getProductLaunchTime() {
		return productLaunchTime;
	}


	public void setProductLaunchTime(Map<String, String> productLaunchTime) {
		this.productLaunchTime = productLaunchTime;
	}


	public Map<String, String> getPasProductCount() {
		return pasProductCount;
	}


	public void setPasProductCount(Map<String, String> pasProductCount) {
		this.pasProductCount = pasProductCount;
	}


	public Map<String, String> getPasProductVolume() {
		return pasProductVolume;
	}


	public void setPasProductVolume(Map<String, String> pasProductVolume) {
		this.pasProductVolume = pasProductVolume;
	}


	public Map<String, String> getPasProductWrittenPremium() {
		return pasProductWrittenPremium;
	}


	public void setPasProductWrittenPremium(Map<String, String> pasProductWrittenPremium) {
		this.pasProductWrittenPremium = pasProductWrittenPremium;
	}


	public Map<String, String> getProductVariants() {
		return productVariants;
	}


	public void setProductVariants(Map<String, String> productVariants) {
		this.productVariants = productVariants;
	}


	public Map<String, String> getSystemEndorsement() {
		return systemEndorsement;
	}


	public void setSystemEndorsement(Map<String, String> systemEndorsement) {
		this.systemEndorsement = systemEndorsement;
	}


	public Map<String, String> getBackToPreviousTerms() {
		return backToPreviousTerms;
	}


	public void setBackToPreviousTerms(Map<String, String> backToPreviousTerms) {
		this.backToPreviousTerms = backToPreviousTerms;
	}


	public Map<String, String> getRatingAutomated() {
		return ratingAutomated;
	}


	public void setRatingAutomated(Map<String, String> ratingAutomated) {
		this.ratingAutomated = ratingAutomated;
	}


	public Map<String, String> getRatingAutomatedIntegrated() {
		return ratingAutomatedIntegrated;
	}


	public void setRatingAutomatedIntegrated(Map<String, String> ratingAutomatedIntegrated) {
		this.ratingAutomatedIntegrated = ratingAutomatedIntegrated;
	}


	public Map<String, String> getInternalInterfacingAppCount() {
		return internalInterfacingAppCount;
	}


	public void setInternalInterfacingAppCount(Map<String, String> internalInterfacingAppCount) {
		this.internalInterfacingAppCount = internalInterfacingAppCount;
	}


	public Map<String, String> getExternalInterfacingAppCount() {
		return externalInterfacingAppCount;
	}


	public void setExternalInterfacingAppCount(Map<String, String> externalInterfacingAppCount) {
		this.externalInterfacingAppCount = externalInterfacingAppCount;
	}


	public Map<String, String> getNumberOfTransDiffBetweenChannels() {
		return numberOfTransDiffBetweenChannels;
	}


	public void setNumberOfTransDiffBetweenChannels(Map<String, String> numberOfTransDiffBetweenChannels) {
		this.numberOfTransDiffBetweenChannels = numberOfTransDiffBetweenChannels;
	}


	public Map<String, String> getPasUsedStateCount() {
		return pasUsedStateCount;
	}


	public void setPasUsedStateCount(Map<String, String> pasUsedStateCount) {
		this.pasUsedStateCount = pasUsedStateCount;
	}


	public Map<String, String> getPasUsedStateCountForLessSates() {
		return pasUsedStateCountForLessSates;
	}


	public void setPasUsedStateCountForLessSates(Map<String, String> pasUsedStateCountForLessSates) {
		this.pasUsedStateCountForLessSates = pasUsedStateCountForLessSates;
	}


	public Map<String, String> getNpsScore() {
		return npsScore;
	}


	public void setNpsScore(Map<String, String> npsScore) {
		this.npsScore = npsScore;
	}


	public Map<String, String> getCsScore() {
		return csScore;
	}


	public void setCsScore(Map<String, String> csScore) {
		this.csScore = csScore;
	}
	
	
	

}
