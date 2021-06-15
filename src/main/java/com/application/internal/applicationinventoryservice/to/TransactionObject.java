package com.application.internal.applicationinventoryservice.to;

public class TransactionObject {

	public long transaction2020;
	public long transaction2019;
	public long transaction2018;

	public long getTransaction2020() {
		return transaction2020;
	}

	public void setTransaction2020(long transaction2020) {
		this.transaction2020 = transaction2020;
	}

	public long getTransaction2019() {
		return transaction2019;
	}

	public void setTransaction2019(long transaction2019) {
		this.transaction2019 = transaction2019;
	}

	public long getTransaction2018() {
		return transaction2018;
	}

	public void setTransaction2018(long transaction2018) {
		this.transaction2018 = transaction2018;
	}

	@Override
	public String toString() {
		return "{ \"transaction2020\": " + transaction2020 + ", \"transaction2019\": " + transaction2019
				+ ", \"transaction2018\": " + transaction2018 + " }";
	}

}