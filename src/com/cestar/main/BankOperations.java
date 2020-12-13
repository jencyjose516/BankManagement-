package com.cestar.main;

/*
 * @author jency
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.cestar.constants.AccountType;
import com.cestar.model.Customer;

public class BankOperations {

	Scanner scanner = new Scanner(System.in);
	Date date = new Date();

	public void createAccount() {

		try {
			ArrayList<Customer> listObj = new ArrayList<>();
			Customer customer = new Customer();

			customer.setCustomerName(JOptionPane.showInputDialog("Enter your Name : "));

			String[] options = { "Savings", "Chequeing", "Current", "Loan" };
			ImageIcon icon = new ImageIcon("src/images/turtle32.png");

			String type = (String) JOptionPane.showInputDialog(null, "Enter the Accout Type??", "",
					JOptionPane.QUESTION_MESSAGE, icon, options, options[2]);

			if (type.equalsIgnoreCase("savings"))

				customer.setAccountType(AccountType.SAVINGS_ACCOUNT.getAccountType());
			else if (type.equalsIgnoreCase("Chequeing"))
				customer.setAccountType(AccountType.CHEQUEING_ACCOUNT.getAccountType());
			else if (type.equalsIgnoreCase("Current"))
				customer.setAccountType(AccountType.CURRENT_ACCOUNT.getAccountType());

			else if (type.equalsIgnoreCase("Loan"))
				customer.setAccountType(AccountType.LOAN_ACCOUNT.getAccountType());

			customer.setAccountNumber((int) ((Math.random() * 9000) + 10000));
			customer.setCustomerID((int) ((Math.random() * 9000) + 200));

			customer.setEmail(JOptionPane.showInputDialog("Enter your Mail ID : "));

			customer.setContact(JOptionPane.showInputDialog("Enter your Mobile Number : "));

			customer.setAddressLine1(JOptionPane.showInputDialog("Enter your address Line 1"));
			customer.setPostalCode(JOptionPane.showInputDialog("Enter your Postal Code"));

			customer.setCountry(JOptionPane.showInputDialog("Enter your Country"));

			customer.setState(JOptionPane.showInputDialog("Enter your Province"));
			customer.setOpenDate(date);
			customer.setBalance(0.0);
			listObj.add(customer);

			JOptionPane.showMessageDialog(null, "Hi " + customer.getCustomerName()
					+ "\nYou have created a record succesfully. \n Your Customer ID is:   " + customer.getCustomerID()
					+ "\n Your Account Number is :   " + customer.getAccountNumber());
			doWriteFile(customer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doWriteFile(Customer newCustomerObject) throws IOException {
		FileWriter fstream;
		try {
			Set<Customer> existingRecord = new HashSet<>();
			Set<Customer> newRecordSet = new HashSet<>();
			boolean exists = new File("out.txt").exists();
			while (exists) {
				existingRecord = readFile();
				for (Customer customer : existingRecord) {
					if (newCustomerObject.getCustomerID() == customer.getCustomerID()
							&& newCustomerObject.getAccountNumber() == customer.getAccountNumber()) {
						newRecordSet.remove(customer);
						newRecordSet.add(newCustomerObject);
					} else {
						newRecordSet.add(customer);
					}

				}

				exists = false;
			}

			newRecordSet.add(newCustomerObject);
			// System.out.println("new records are:"+newCustomerObject.toString());
			FileOutputStream fos = new FileOutputStream("out.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(newRecordSet);
			oos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doDeposit() throws IOException, ClassNotFoundException {
		try {
			Set<Customer> recordSet = new HashSet<Customer>();
			ArrayList<Customer> newList = new ArrayList<>();

			int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));

			int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));

			int balance = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount"));

			recordSet = readFile();
			for (Customer customer : recordSet) {
				if (customer.getCustomerID() == customerID && acNo == customer.getAccountNumber()) {
					customer.setBalance(balance + customer.getBalance());
					// newList.add(customer);
					doWriteFile(customer);
					JOptionPane.showMessageDialog(null,
							"Hi " + customer.getCustomerName() + "\nMoney has deposited to your account");

					break;
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Data");
		}
	}

	public void doWithdraw() throws IOException {
		Set<Customer> recordSet = new HashSet<Customer>();

		ArrayList<Customer> newList = new ArrayList<>();

		int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));

		int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));

		int withdrawMoney = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount"));
		float chargingFee = chargingFee(withdrawMoney);

		recordSet = readFile();
		for (Customer customer : recordSet) {
			if (customer.getCustomerID() == customerID && acNo == customer.getAccountNumber()) {
				customer.setBalance(customer.getBalance() - withdrawMoney - chargingFee);
				// newList.add(customer);
				doWriteFile(customer);

				JOptionPane.showMessageDialog(null, "Hi " + customer.getCustomerName() + "\nYou have withdrawn "
						+ withdrawMoney + " . You have charged a fee of " + chargingFee);

				break;
			}

		}
	}

	private float chargingFee(int withdrawMoney) {

		float chargingFee = (float) (withdrawMoney * 0.0001);
		return chargingFee;
		// TODO Auto-generated method stub

	}

	public Set readFile() throws IOException {
		Set<Customer> recordSet = new HashSet<Customer>();
		FileInputStream fis = new FileInputStream("out.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {

			int size = 0;
			while (size >= 0) {
				try {
					recordSet = (Set<Customer>) ois.readObject();
					if (recordSet == null) {
						ois.close();
						break;
					}
					size = recordSet.size();

				} catch (Exception ex) {

				}
				size--;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			ois.close();
		}
		return recordSet;
	}

	public void displayBalance() throws IOException {
		int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));
		int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));

		Set<Customer> existingRecord = new HashSet<>();

		existingRecord = readFile();
		for (Customer customer : existingRecord) {

			if (customerID == customer.getCustomerID() && acNo == customer.getAccountNumber()) {
				JOptionPane.showMessageDialog(null,
						"Hi " + customer.getCustomerName() + "\nYour total balance is: " + customer.getBalance());
			}

		}
	}

	public void doTransfer() {
		try {
			
			int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));
			
			int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));

			
			int benAccNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Beneficiery Account Number"));

		
			double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Money to transfer"));

			Set<Customer> existingRecord = new HashSet<>();

			existingRecord = readFile();
			for (Customer customer : existingRecord) {

				if (customerID == customer.getCustomerID() && acNo == customer.getAccountNumber()) {
					customer.setBalance(customer.getBalance() - amount);
					doWriteFile(customer);
					JOptionPane.showMessageDialog(null,
							"Hi " + customer.getCustomerName() + "\nYou have transferred " + amount);
				
				} else if (customer.getAccountNumber() == benAccNumber) {
					customer.setBalance(customer.getBalance() + amount);
					doWriteFile(customer);
					JOptionPane.showMessageDialog(null,
							"Hi " + customer.getCustomerName() + "\n" + amount + " has deposited to your account ");
					
				}

				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void payBills() {

		try {
			/*
			 * System.out.println("Please login:\n=========================");
			 * System.out.println("Enter the customer ID:");
			 */ int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));
			// System.out.println("Enter Account Number");
			int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));
			Set<Customer> existingRecord = new HashSet<>();
			existingRecord = readFile();
			for (Customer customer : existingRecord) {
				if (customerID == customer.getCustomerID() && acNo == customer.getAccountNumber()) {
					JOptionPane.showMessageDialog(null,
							"Your next Utility Bill date is : " + customer.getNextUtility());
					// System.out.println("Your next Utility Bill date is :"+
					// customer.getNextUtility());
					Date todayDate = Calendar.getInstance().getTime();
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					Date currentDate = sm.parse(sm.format(todayDate));
					Date nextUtilDate = customer.getNextUtility();
					if (currentDate.compareTo(nextUtilDate) == 0) {
						customer.setBalance(customer.getBalance() - customer.getUtilityAmount());
						customer.setLastUtility(currentDate);

						Calendar c = Calendar.getInstance();
						c.setTime(currentDate);
						c.add(Calendar.DATE, customer.getUtilityCycle());
						Date nextUtility = c.getTime();
						customer.setNextUtility(nextUtility);
						doWriteFile(customer);

						JOptionPane.showMessageDialog(null, " Hi " + customer.getCustomerName() + " \n"
								+ "You have Paid the utility bill of " + customer.getUtilityAmount());
						// System.out.println(" Hi" + customer.getCustomerName() + " " + "You have Paid
						// the utility bill of" + customer.getUtilityAmount());
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addBill() {
		try {
			Set<Customer> existingRecord = new HashSet<>();
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

			/*
			 * System.out.println("Please login:\n=========================");
			 * System.out.println("Enter the customer ID:");
			 */int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));
			// System.out.println("Enter Account Number");
			int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));

			// System.out.println("Enter the utility amount");
			double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the utility amount"));

			// System.out.println("Enter the utility cycle in days");
			int cycle = Integer.parseInt(JOptionPane.showInputDialog("Enter the utility cycle in days"));

			// System.out.println("Enter the next utility bill date in yyyy-MM-dd Format");
			String utilDate = JOptionPane.showInputDialog("Enter the next utility bill date in yyyy-MM-dd Format");

			Date nextUtilDate = sm.parse(utilDate);
			Date todayDate = Calendar.getInstance().getTime();
			existingRecord = readFile();
			for (Customer customer : existingRecord) {
				if (customerID == customer.getCustomerID() && acNo == customer.getAccountNumber()) {

					customer.setNextUtility(nextUtilDate);

					customer.setUtilityAmount(amount);

					customer.setUtilityCycle(cycle);
					customer.setLastUtility(sm.parse(sm.format(todayDate)));
					doWriteFile(customer);
					
					JOptionPane.showMessageDialog(null, " Hi " + customer.getCustomerName() + " \n"
							+ "You have added utility bill of " + customer.getUtilityAmount()+". Your next Utility bill due date is :"+customer.getNextUtility());
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void editAddress() {
		Set<Customer> existingRecord = new HashSet<>();
		int customerID = Integer.parseInt(JOptionPane.showInputDialog("Enter your Customer ID : "));
		int acNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number"));
		String addresLine1 = JOptionPane.showInputDialog("Enter Adress Line 1");
		String province = JOptionPane.showInputDialog("Enter Province");
		String postalCode = JOptionPane.showInputDialog("Enter Postal Code");
		try {
			existingRecord = readFile();
			for (Customer customer : existingRecord) {
				if (customerID == customer.getCustomerID() && acNo == customer.getAccountNumber()) {
					
					customer.setAddressLine1(addresLine1);
					customer.setPostalCode(postalCode);
					customer.setState(province);
					doWriteFile(customer);
					
					JOptionPane.showMessageDialog(null, " Hi " + customer.getCustomerName() + " \n You have updated your contact details. Thank you !");
				}
					
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
