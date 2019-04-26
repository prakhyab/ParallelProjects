package com.capgemini.BankApplication;

import java.util.Scanner;

import com.cg.ba.DTO.CustomerDetails;
 
import com.cg.ba.Service.BankAppServiceImpl1;
 
import com.cg.ba.Service.IBankAppService1;
 

public class App {
	static Scanner sc = new Scanner(System.in);
	static CustomerDetails custDetails = new CustomerDetails();
	 	static IBankAppService1 bankAppService1 = new BankAppServiceImpl1();
	 

	public static void main(String[] args) {
		
		
		selection();
	}
		
		
		public static void selection(){
		 
		  do{
		System.out.println("Enter 1. Register 2. Login");
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			System.out.println("Enter First name");
			custDetails.setFirstName(sc.next());
			System.out.println("Enter last name");
			custDetails.setLastName(sc.next());
			System.out.println("Enter email_id");
			custDetails.setEmailId(sc.next());
			System.out.println("Enter password");
			custDetails.setPassword(sc.next());
			System.out.println("Enter aadharno");
			custDetails.setAadharNo(sc.next());
			System.out.println("Enter pancard no");
			custDetails.setPancardNo(sc.next());
			System.out.println("Enter address ");
			custDetails.setAddress(sc.next());
			System.out.println("Enter mobile no");
			custDetails.setMobile(sc.next());
			custDetails.setBalance(0);
			int accoNo = bankAppService1.registerCustomer(custDetails);
            if(accoNo!=0) {
            	custDetails.setAccNo(accoNo);
			System.out.println("Account Number : " + accoNo);
            }

			break;
		case 2:

			System.out.println("Enter account number and password for login");
			int accNo = sc.nextInt();
			String password=sc.next();
			
			 int i = bankAppService1.login(accNo,password);
			
			if (i==1) {
				
				System.out.println("Login Successful");

				do {

					System.out
							.println("Enter 1. Deposit 2. Withdraw 3. Show Balance 4. Fund Transfer 5. Exit");
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Enter acc no");
						custDetails.setAccNo(sc.nextInt());
						System.out.println("Enter amount");
						int amount1 = sc.nextInt();
						 
						int j = bankAppService1.deposit(custDetails,amount1);
						if (j == 1) {
							System.out.println("Successfully Deposited ");
						}
						else
							System.out.println("Deposit failed");
						break;
					case 2:
						System.out.println("Enter acc no");
						custDetails.setAccNo(sc.nextInt());
						System.out.println("Enter amount");
						int amount2 = sc.nextInt();
						 
						int m = bankAppService1.withdrawal(custDetails,amount2);
						if(m==1)
						System.out.println("Successfully Withdrawn");
						else
							System.out.println("Withdrawal failed");
						break;
					case 3:
						
						System.out.println("Enter account no ");
						 custDetails.setAccNo(sc.nextInt());
						int k = bankAppService1.showBalance(custDetails);
						System.out.println("Balance is: " + k);
						break;
					case 4:
						System.out.println("Enter from acc no");
						custDetails.setAccNo(sc.nextInt());
						System.out.println("Enter amount to be transfered");
						int amount=sc.nextInt();
						 
						System.out.println("Enter to account no");
						int toAccNo=sc.nextInt();
									int n = bankAppService1.transferFund(custDetails,toAccNo,amount);
                             if(n==2){
                            	 System.out.println("Fund transfered from"+ custDetails.getAccNo()+" to "+toAccNo+ " : "+ amount);
                             }
						break;
					case 5:
						System.exit(0);
						break;
					default:
						System.out.println("Invalid choice");

					}
				} while (true);
			}

		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("invalid choice");
		}
      }while(true);

	}
		
	}

