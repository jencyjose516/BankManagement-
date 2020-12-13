package com.cestar.main;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 * @author Jency 
 */
public class BankManagment {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		  Scanner scanner = new Scanner(System.in);
		  int choice;
		  BankOperations op= new BankOperations();
		  try{
			  do {
				  
				  String inputChoice = JOptionPane.showInputDialog(
							"Please choose any of the option from below:\n 1. Create Account\n 2. Deposit money\n 3. Display Current Balance\n 4. Withdraw Money\n"
									+ " 5. Transfer Money to other account\n 6. Add UtilityBill to your Account \n 7. Pay utility Bills \n 9. Exit\n"
									+ "\nAdditional Feature\n"
									+ "8. Edit Your Address Details");
					choice = Integer.parseInt(inputChoice);
	              
	              switch(choice) {
	              
	              case 1: op.createAccount();
	                      break;
	                      
	              case 2: op.doDeposit();
	              		 break;
	              case 3: op.displayBalance();
	                      break;
	                      
						
	              case 4: op.doWithdraw(); 
	              		  break;
						 
	                       
	              case 5 : op.doTransfer();
	               			break;
	               
	              case 6 : op.addBill();
	              break;
	              
	              case 7: op.payBills();
	              			break;
	              case 8: op.editAddress();
        			break;
				default: 
					break;
	       
	              }
			  }while(choice!=9);
		  } catch (Exception e) {
			System.out.println();
		}
			  
				

	}

}
