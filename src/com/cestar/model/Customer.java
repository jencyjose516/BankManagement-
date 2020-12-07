/**
 * 
 */
package com.cestar.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DELL
 *
 */
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerID;
	private String customerName;
	private int accountNumber;
	private String accountType;
	private Date openDate;
	private double balance;
	private String contact;
	private String email;
	private String addressLine1;
	private String country;
	private String state;
	
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Customer() {
		super();
	}
	public Customer(String customerName, int accountNumber, String accountType, Date openDate, Float balance,
			String contact, String email, String addressLine1, String country, String state, int customerID) {
		super();
		this.customerName = customerName;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.openDate = openDate;
		this.balance = balance;
		this.contact = contact;
		this.email = email;
		this.addressLine1 = addressLine1;
		this.country = country;
		this.state = state;
		this.customerID= customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", accountNumber="
				+ accountNumber + ", accountType=" + accountType + ", openDate=" + openDate + ", balance=" + balance
				+ ", contact=" + contact + ", email=" + email + ", addressLine1=" + addressLine1 + ", country="
				+ country + ", state=" + state + "]";
	}
	
	
}
