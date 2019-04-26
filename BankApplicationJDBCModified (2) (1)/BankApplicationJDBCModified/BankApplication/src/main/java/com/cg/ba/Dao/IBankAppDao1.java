package com.cg.ba.Dao;

import com.cg.ba.DTO.CustomerDetails;

public interface IBankAppDao1 {
	 int registerCustomer( CustomerDetails custDetails); 
	 int login(int accNO,String password);
	 int deposit(CustomerDetails custDetails,int amount );
		int withdrawal( CustomerDetails custDetails, int amount );
		int showBalance(CustomerDetails custDetails);
		int transferFund(CustomerDetails custDetails ,int toAccNo, int amount);


}