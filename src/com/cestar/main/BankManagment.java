package com.cestar.main;

import java.io.IOException;
import java.util.Scanner;

public class BankManagment {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		  Scanner scanner = new Scanner(System.in);
		  int choice=1;
		  BankOperations op= new BankOperations();
		  do {
			  System.out.println("1. Create Account");
              System.out.println("2. Deposit money");
              System.out.println("3. Withdraw money");
              System.out.println("4. Check balance");
              System.out.println("5. Display Account Details");
              System.out.println("6. Pay utility Bills");
              System.out.println("7. Exit\n");
              System.out.print("Enter Your Choice : ");
              choice = scanner.nextInt();
              
              switch(choice) {
              
              case 1: op.createAccount();
                      break;
                      
              case 2: op.doDeposit();
              		 break;
              case 3: op.doWithdraw();
                      break;
                      
					/*
					 * case 4: op.displayBalance(); break;
					 */
                       
              case 5 : op.doTransfer();
               break;
               
              case 6 : op.payBills();
              break;
              default: break;
       
              }
		  }while(choice==6);

	}

}
