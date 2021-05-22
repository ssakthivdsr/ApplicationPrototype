package com.application.internal.applicationinventoryservice.to;

import java.util.List;

public class BusinessApplicationDetailsTO {

	private List<ChannelTO> channelTOs;
	private List<TransactionTO> transactionTOs;
	private List<ProductTO> productTOs;
	private List<UserTO> userTOs;
	private List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswerTOs;

	public List<ChannelTO> getChannelTOs() {
		return channelTOs;
	}

	public void setChannelTOs(List<ChannelTO> channelTOs) {
		this.channelTOs = channelTOs;
	}

	public List<TransactionTO> getTransactionTOs() {
		return transactionTOs;
	}

	public void setTransactionTOs(List<TransactionTO> transactionTOs) {
		this.transactionTOs = transactionTOs;
	}

	public List<ProductTO> getProductTOs() {
		return productTOs;
	}

	public void setProductTOs(List<ProductTO> productTOs) {
		this.productTOs = productTOs;
	}

	public List<UserTO> getUserTOs() {
		return userTOs;
	}

	public void setUserTOs(List<UserTO> userTOs) {
		this.userTOs = userTOs;
	}

	public List<BusinessApplicationQuestionAnswerTO> getBusinessApplicationQuestionAnswerTOs() {
		return businessApplicationQuestionAnswerTOs;
	}

	public void setBusinessApplicationQuestionAnswerTOs(
			List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswerTOs) {
		this.businessApplicationQuestionAnswerTOs = businessApplicationQuestionAnswerTOs;
	}

}
