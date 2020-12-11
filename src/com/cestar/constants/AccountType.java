package com.cestar.constants;

public enum AccountType {

	SAVINGS_ACCOUNT("SAV"),
	CHEQUEING_ACCOUNT("CHE"),
	CURRENT_ACCOUNT("CUR"),
	LOAN_ACCOUNT("LN");
	
	
	
	private String accountType;

	AccountType(String string) {
		this.accountType= string;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
}
