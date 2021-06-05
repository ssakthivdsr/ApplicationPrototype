package com.application.internal.applicationinventoryservice.scoreengine;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.application.internal.applicationinventoryservice.config.BusinessApplicationScoreEngine;
import com.application.internal.applicationinventoryservice.to.BusinessApplicationDetailsTO;

@Component
public class BusinessApplicationRuleEngine {

	private int businessValue;
	private int agility;
	private int businessTotal;

	private BusinessApplicationScoreEngine scoreEngine = new BusinessApplicationScoreEngine();

	public void setBusinessApplicationScore(BusinessApplicationDetailsTO baTO) {
		int transactionTypesScore = retrieveTransactionTypesScore(baTO);
		int transactionVolumeScore = retrieveTransactionVolumeScore(baTO);
		int userTypesScore = retrieveUserTypesScore(baTO);
		int userTransactionVolumeScore = retrieveUserTransactionVolumeScore(baTO);
		int diffInternetAndAgentScore = retrieveScore(scoreEngine.getDiffInternetAndAgent(),
				baTO.getBusinessApplicationQuestionAnswer().get(0).getAnswer());
		int uwAutomatedScore = retrieveScore(scoreEngine.getUwAutomated(),
				baTO.getBusinessApplicationQuestionAnswer().get(1).getAnswer());
		int uwAutomatedTransactionVolumeSTPScore = retrieveScore(scoreEngine.getUwAutomatedTransactionVolumeSTP(),
				baTO.getBusinessApplicationQuestionAnswer().get(2).getAnswer());
		int transactionTypeOverridenScore = retrieveScore(scoreEngine.getTransactionTypeOverriden(),
				baTO.getBusinessApplicationQuestionAnswer().get(3).getAnswer());
		int projectWBPresenceScore = retrieveScore(scoreEngine.getProjectWBPresence(),
				baTO.getBusinessApplicationQuestionAnswer().get(4).getAnswer());
		int productLaunchTimeScore = retrieveScore(scoreEngine.getProductLaunchTime(),
				baTO.getBusinessApplicationQuestionAnswer().get(5).getAnswer());
		int pasProductTypeScore = retrievePasProductTypeScore(baTO);
		int pasProductVolumeScore = retrievePasProductVolumeScore(baTO);
		int pasProductWrittenPremiumScore = retrievepasProductWrittenPremiumScore(baTO);
		int productVariantsScore = retrieveScore(scoreEngine.getProductVariants(),
				baTO.getBusinessApplicationQuestionAnswer().get(7).getAnswer());
		int systemEndorsementScore = retrieveScore(scoreEngine.getSystemEndorsement(),
				baTO.getBusinessApplicationQuestionAnswer().get(8).getAnswer());
		int backToPreviousTermsScore = retrieveScore(scoreEngine.getBackToPreviousTerms(),
				baTO.getBusinessApplicationQuestionAnswer().get(9).getAnswer());
		int ratingAutomatedScore = retrieveScore(scoreEngine.getRatingAutomated(),
				baTO.getBusinessApplicationQuestionAnswer().get(10).getAnswer());
		int ratingAutomatedIntegratedScore = retrieveScore(scoreEngine.getRatingAutomatedIntegrated(),
				baTO.getBusinessApplicationQuestionAnswer().get(11).getAnswer());
		int internalInterfacingAppCountScore = retrieveScore(scoreEngine.getInternalInterfacingAppCount(),
				baTO.getBusinessApplicationQuestionAnswer().get(12).getAnswer());
		int externalInterfacingAppCountScore = retrieveScore(scoreEngine.getExternalInterfacingAppCount(),
				baTO.getBusinessApplicationQuestionAnswer().get(13).getAnswer());
		int numberOfTransDiffBetweenChannelsScore = retrieveScore(scoreEngine.getNumberOfTransDiffBetweenChannels(),
				baTO.getBusinessApplicationQuestionAnswer().get(14).getAnswer());
		int pasUsedStateCountScore = retrieveScore(scoreEngine.getPasUsedStateCount(),
				baTO.getBusinessApplicationQuestionAnswer().get(15).getAnswer());
		int pasUsedStateCountForLessSatesScore = retrieveScore(scoreEngine.getPasUsedStateCountForLessSates(),
				baTO.getBusinessApplicationQuestionAnswer().get(16).getAnswer());
		int npsScoreScore = retrieveScore(scoreEngine.getNpsScore(),
				baTO.getBusinessApplicationQuestionAnswer().get(17).getAnswer());
		int csScoreScore = retrieveScore(scoreEngine.getCsScore(),
				baTO.getBusinessApplicationQuestionAnswer().get(18).getAnswer());

		this.businessTotal = transactionTypesScore + transactionVolumeScore + userTypesScore
				+ userTransactionVolumeScore + diffInternetAndAgentScore + uwAutomatedScore
				+ uwAutomatedTransactionVolumeSTPScore + transactionTypeOverridenScore + projectWBPresenceScore
				+ productLaunchTimeScore + pasProductTypeScore + pasProductVolumeScore + pasProductWrittenPremiumScore
				+ productVariantsScore + systemEndorsementScore + backToPreviousTermsScore + ratingAutomatedScore
				+ ratingAutomatedIntegratedScore + internalInterfacingAppCountScore + externalInterfacingAppCountScore
				+ numberOfTransDiffBetweenChannelsScore + pasUsedStateCountScore + pasUsedStateCountForLessSatesScore
				+ npsScoreScore + csScoreScore;

		this.businessValue = transactionTypesScore + transactionVolumeScore + userTransactionVolumeScore
				+ pasProductTypeScore + pasProductVolumeScore + pasProductWrittenPremiumScore + pasUsedStateCountScore
				+ pasUsedStateCountForLessSatesScore + npsScoreScore + csScoreScore;

		this.agility = userTypesScore + diffInternetAndAgentScore + uwAutomatedScore
				+ uwAutomatedTransactionVolumeSTPScore + transactionTypeOverridenScore + projectWBPresenceScore
				+ productLaunchTimeScore + productVariantsScore + systemEndorsementScore + backToPreviousTermsScore
				+ ratingAutomatedScore + ratingAutomatedIntegratedScore + internalInterfacingAppCountScore
				+ externalInterfacingAppCountScore + numberOfTransDiffBetweenChannelsScore;
	}

	private int retrieveTransactionTypesScore(BusinessApplicationDetailsTO baTO) {
		String transactionTypeCount = "";
		if (baTO.getTransactions().size() <= 4) {
			transactionTypeCount = String.valueOf(baTO.getTransactions().size());
		} else {
			transactionTypeCount = ">4";
		}
		return retrieveScore(scoreEngine.getTransactionTypes(), transactionTypeCount);
	}

	private int retrieveTransactionVolumeScore(BusinessApplicationDetailsTO baTO) {
		int count = 0;
		String transactionVolumeCount = "";
		for (int i = 0; i < baTO.getTransactions().size(); i++) {
			count = count + baTO.getTransactions().get(i).getVolumeObject().getTransaction2020()
					+ baTO.getTransactions().get(i).getVolumeObject().getTransaction2019()
					+ baTO.getTransactions().get(i).getVolumeObject().getTransaction2018();
		}
		if (count < 5000000) {
			transactionVolumeCount = "<5 Million";
		} else if (count >= 5000000 && count <= 10000000) {
			transactionVolumeCount = "5-10 Million";
		} else if (count > 10000000 && count <= 25000000) {
			transactionVolumeCount = "10-25 Million";
		} else if (count > 25000000 && count <= 50000000) {
			transactionVolumeCount = "25-50 Million";
		} else if (count > 50000000 && count <= 100000000) {
			transactionVolumeCount = "50-100 Million";
		} else if (count > 100000000) {
			transactionVolumeCount = ">100 Million";
		}
		return retrieveScore(scoreEngine.getTransactionVolume(), transactionVolumeCount);
	}

	private int retrieveUserTypesScore(BusinessApplicationDetailsTO baTO) {
		String userTypeCount = "";
		if (baTO.getUsers().size() <= 4) {
			userTypeCount = String.valueOf(baTO.getUsers().size());
		} else {
			userTypeCount = ">4";
		}
		return retrieveScore(scoreEngine.getUserTypes(), userTypeCount);
	}

	private int retrieveUserTransactionVolumeScore(BusinessApplicationDetailsTO baTO) {
		int count = 0;
		String userTransactionVolumeCount = "";
		for (int i = 0; i < baTO.getUsers().size(); i++) {
			count = count + baTO.getUsers().get(i).getVolumeObject().getTransaction2020()
					+ baTO.getUsers().get(i).getVolumeObject().getTransaction2019()
					+ baTO.getUsers().get(i).getVolumeObject().getTransaction2018();
		}
		if (count < 5000000) {
			userTransactionVolumeCount = "<5 Million";
		} else if (count >= 5000000 && count <= 10000000) {
			userTransactionVolumeCount = "5-10 Million";
		} else if (count > 10000000 && count <= 25000000) {
			userTransactionVolumeCount = "10-25 Million";
		} else if (count > 25000000 && count <= 50000000) {
			userTransactionVolumeCount = "25-50 Million";
		} else if (count > 50000000 && count <= 100000000) {
			userTransactionVolumeCount = "50-100 Million";
		} else if (count > 100000000) {
			userTransactionVolumeCount = ">100 Million";
		}
		return retrieveScore(scoreEngine.getUserTypesTransactionVolume(), userTransactionVolumeCount);
	}

	private int retrievePasProductTypeScore(BusinessApplicationDetailsTO baTO) {
		String pasProductTypeCount = "";
		if (baTO.getProducts().size() <= 4) {
			pasProductTypeCount = String.valueOf(baTO.getProducts().size());
		} else {
			pasProductTypeCount = ">4";
		}
		return retrieveScore(scoreEngine.getPasProductType(), pasProductTypeCount);
	}

	private int retrievePasProductVolumeScore(BusinessApplicationDetailsTO baTO) {
		int count = 0;
		String pasProductVolumeCount = "";
		for (int i = 0; i < baTO.getProducts().size(); i++) {
			count = count + baTO.getProducts().get(i).getVolumeObject().getTransaction2020();
		}
		if (count < 250000) {
			pasProductVolumeCount = "<250K";
		} else if (count >= 250000 && count <= 500000) {
			pasProductVolumeCount = "250K - 500K";
		} else if (count > 500000 && count <= 1000000) {
			pasProductVolumeCount = "500K - 1M";
		} else if (count > 1000000 && count <= 5000000) {
			pasProductVolumeCount = "1M - 5M";
		} else if (count > 5000000) {
			pasProductVolumeCount = ">5M";
		}
		return retrieveScore(scoreEngine.getPasProductVolume(), pasProductVolumeCount);
	}

	private int retrievepasProductWrittenPremiumScore(BusinessApplicationDetailsTO baTO) {
		long count = 0;
		String pasProductWrittenPremiumCount = "";
		for (int i = 0; i < baTO.getProducts().size(); i++) {
			count = count + baTO.getProducts().get(i).getWrittenPremiumOfProductsObject().getTransaction2020();
		}
		if (count < 500000000) {
			pasProductWrittenPremiumCount = "<500 MUSD";
		} else if (count >= 500000000 && count <= 1000000000) {
			pasProductWrittenPremiumCount = "500 MUSD - 1 BUSD";
		} else if (count > 1000000000 && count <= 5000000000l) {
			pasProductWrittenPremiumCount = "1 - 5 BUSD";
		} else if (count > 5000000000l && count <= 10000000000l) {
			pasProductWrittenPremiumCount = "5 - 10 BUSD";
		} else if (count > 10000000000l) {
			pasProductWrittenPremiumCount = ">10 BUSD";
		}
		return retrieveScore(scoreEngine.getPasProductWrittenPremium(), pasProductWrittenPremiumCount);
	}

	private int retrieveScore(Map<String, String> scoreMap, String key) {
		if (scoreMap.get(key) != null) {
			return Integer.parseInt(scoreMap.get(key));
		}
		return 0;
	}

	public int getBusinessValue() {
		return businessValue;
	}

	public void setBusinessValue(int businessValue) {
		this.businessValue = businessValue;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getBusinessTotal() {
		return businessTotal;
	}

	public void setBusinessTotal(int businessTotal) {
		this.businessTotal = businessTotal;
	}
}
