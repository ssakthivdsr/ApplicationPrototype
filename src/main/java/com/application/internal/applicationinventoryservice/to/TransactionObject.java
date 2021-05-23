package com.application.internal.applicationinventoryservice.to;

public class TransactionObject {

	public int transaction2020;
	public int transaction2019;
	public int transaction2018;

	public int getTransaction2020() {
		return transaction2020;
	}

	public void setTransaction2020(int transaction2020) {
		this.transaction2020 = transaction2020;
	}

	public int getTransaction2019() {
		return transaction2019;
	}

	public void setTransaction2019(int transaction2019) {
		this.transaction2019 = transaction2019;
	}

	public int getTransaction2018() {
		return transaction2018;
	}

	public void setTransaction2018(int transaction2018) {
		this.transaction2018 = transaction2018;
	}

	@Override
	public String toString() {
		return "{ \"transaction2020\": " + transaction2020
				+ ", \"transaction2019\": " + transaction2019 + ", \"transaction2018\": " + transaction2018 + " }";
	}

}