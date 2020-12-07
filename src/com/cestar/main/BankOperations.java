package com.cestar.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cestar.model.Customer;

import jdk.jfr.FlightRecorderListener;

public class BankOperations {

	 Scanner scanner = new Scanner(System.in);
	 //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
     Date date = new Date();

	public void createAccount() {
		ArrayList<Customer>listObj = new ArrayList<>();
		Customer customer = new Customer();
		System.out.print("Enter your Name : ");
        customer.setCustomerName(scanner.nextLine());
        System.out.print("Enter Accout Type : 1. Savings, 2. Chequing, 3. Current");
        int choice = scanner.nextInt();
        if (choice ==1)
        	customer.setAccountType("SAV");
        else if(choice==2)
        	customer.setAccountType("CHE");
        else
        	customer.setAccountType("CUR");
        customer.setAccountNumber((int)((Math.random() * 9000)+10000));
        customer.setCustomerID((int)((Math.random() * 9000)+200));
        System.out.print("Enter your Mobile Number : ");
        customer.setContact(scanner.nextLine());
        System.out.println("Enger your EMail ID");
        customer.setEmail(scanner.nextLine());
        System.out.println("Enter your address Line 1");
        customer.setAddressLine1(scanner.nextLine());
        System.out.println("Enter your Country");
        customer.setCountry(scanner.nextLine());

        System.out.println("Enter your state");
        customer.setState(scanner.nextLine());
        customer.setOpenDate(date);
        customer.setBalance(0.0);
        listObj.add(customer);
        
        System.out.println("The details are\n:"+ customer.toString());
        doWriteFile(listObj);
        
		
	}

	private void doWriteFile(ArrayList<Customer> listObj) {
		 FileWriter fstream;
		try {
			 boolean exists = new File("out.txt").exists();
		        FileOutputStream fos = new FileOutputStream("out.txt", true);
		        ObjectOutputStream oos = exists ? 
		            new ObjectOutputStream(fos) {
		                protected void writeStreamHeader() throws IOException {
		                    reset();
		                }
		            }:new ObjectOutputStream(fos);
			 oos.writeObject(listObj);
			 oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  
		
	}

	public void doDeposit() throws IOException, ClassNotFoundException {
		 ArrayList<Customer> recordList = new ArrayList();
		 
		 System.out.println("Enter the customer ID:");
		 int customerID= scanner.nextInt(); 
		 System.out.println("Enter Account Number");
		 int acNo = scanner.nextInt();
		 System.out.print("Enter Amount Of Money : ");
         int balance=scanner.nextInt();
		 FileInputStream fis = new FileInputStream("out.txt");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        while(fis.available() > 0) {
	            try {
	            	recordList = (ArrayList<Customer>) ois.readObject();
	                recordList.forEach(ob->{
	                	if(customerID==ob.getCustomerID()&&acNo==ob.getAccountNumber()) {
	                		ob.setBalance(ob.getBalance()+balance);
	     
	                	}
	                	
	                });
	                doWriteFile(recordList);
	                displayBalance(customerID,acNo);
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        }
	        ois.close();
	        
	        
	}

	public void doWithdraw() {
		// TODO Auto-generated method stub
		
	}

	public void displayBalance(int customerID, int acNo) throws IOException {
	
			 ArrayList<Customer> recordList = new ArrayList();
			 
			 FileInputStream fis = new FileInputStream("out.txt");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        while(fis.available() > 0) {
		            try {
		            	recordList = (ArrayList<Customer>) ois.readObject();
		            	recordList.forEach(obj->{
		            		if(customerID==obj.getCustomerID()&&acNo==obj.getAccountNumber())
			            		System.out.println(obj.toString());
		            	});
		            	
		                
		            } catch (ClassNotFoundException e) {
		                e.printStackTrace();
		            }
		        }
		      
	}

	public void doTransfer() {
		// TODO Auto-generated method stub
		
	}

	public void payBills() {
		// TODO Auto-generated method stub
		
	}

	
}
