package com.application.internal.applicationinventoryservice.scoreengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.internal.applicationinventoryservice.config.BusinessApplicationScoreEngine;
import com.application.internal.applicationinventoryservice.to.BusinessApplicationDetailsTO;

@Component
public class BusinessApplicationRuleEngine {
	@Autowired
	private BusinessApplicationScoreEngine scoreEngine;
	
	public int storeBusinessApplicationScore(BusinessApplicationDetailsTO bpTO) {
		List<Integer> scores = new ArrayList<>();
		scores.add(retrieveUserTypeScore(bpTO));
		
		return 0;
	}
	
	private int retrieveUserTypeScore (BusinessApplicationDetailsTO bpTO) {
		String transactionTypeCount = String.valueOf(bpTO.getTransactions().size());
		return(retrieveScore(scoreEngine.getUserTypes(),transactionTypeCount));
	}
	
	private int retrieveScore(Map<String,String> scoreMap, String key) {
		if(scoreMap.get(key) != null) {
			return Integer.parseInt(scoreMap.get(key));
		}
		return 0;
	}

}
