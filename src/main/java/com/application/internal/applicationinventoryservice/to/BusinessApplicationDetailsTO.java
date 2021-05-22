package com.application.internal.applicationinventoryservice.to;

import java.util.List;

public class BusinessApplicationDetailsTO {

	private List<ChannelTO> channels;
	private List<TransactionTO> transactions;
	private List<ProductTO> products;
	private List<UserTO> users;
	private List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer;

	public List<ChannelTO> getChannels() {
		return channels;
	}

	public void setChannels(List<ChannelTO> channels) {
		this.channels = channels;
	}

	public List<TransactionTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionTO> transactions) {
		this.transactions = transactions;
	}

	public List<ProductTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductTO> products) {
		this.products = products;
	}

	public List<UserTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserTO> users) {
		this.users = users;
	}

	public List<BusinessApplicationQuestionAnswerTO> getBusinessApplicationQuestionAnswer() {
		return businessApplicationQuestionAnswer;
	}

	public void setBusinessApplicationQuestionAnswer(
			List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer) {
		this.businessApplicationQuestionAnswer = businessApplicationQuestionAnswer;
	}

}
