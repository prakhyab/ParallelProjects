package com.cg.ba.Service;

import com.cg.ba.Dao.BankAppDaoImpl1;
 
import com.cg.ba.Dao.IBankAppDao1;
 
import com.cg.ba.DTO.CustomerDetails;

import BankAppExceptions.*;

public class BankAppServiceImpl1 implements IBankAppService1  {
	IBankAppDao1 appDao1 = new BankAppDaoImpl1(); 
	CustomerDetails c= new CustomerDetails();
	int cd;

	public int registerCustomer(CustomerDetails custDetails) {
		
		if((custDetails.getAadharNo()).length()==12){
			if(custDetails.getPancardNo().length()==6){
				//cd= appDao1.registerCustomer(custDetails);
				
	
					return appDao1.registerCustomer(custDetails);
				 
				
			} else
				try {
					throw new InvalidPancardNoexception();
				} catch (InvalidPancardNoexception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} else
			try {
				throw new InvalidAadharNoException();
			} catch (InvalidAadharNoException e) {
		 
				e.printStackTrace();
			}
		return 0;
			
			 
		
		
		
	}


	public int login(int accNo,String password) {
		 
		return appDao1.login(accNo,password);
	}
	
	
	 
	public int deposit(CustomerDetails custDetails,int amount ) {

		return appDao1.deposit(custDetails,amount);
	}

	public int withdrawal( CustomerDetails custDetails,int amount) {

		return appDao1.withdrawal(custDetails,amount);
	}

	public int showBalance(CustomerDetails custDetails) {

		return appDao1.showBalance(custDetails);
	}

	public int transferFund(CustomerDetails custDetails, int toAccoNo, int amount) {

		return appDao1.transferFund( custDetails,toAccoNo,amount);
	}


}