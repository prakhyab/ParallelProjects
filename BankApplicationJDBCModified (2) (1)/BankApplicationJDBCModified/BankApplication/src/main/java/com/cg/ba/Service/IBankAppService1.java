package com.cg.ba.Service;

import com.cg.ba.DTO.CustomerDetails;

public interface IBankAppService1 {

 int registerCustomer( CustomerDetails custDetails); 
int login(int accNo,String password);

int deposit(CustomerDetails custDetails,int amount );
int withdrawal(CustomerDetails custDetails,int amount );
int showBalance(CustomerDetails custDetails);
int transferFund(CustomerDetails custDetails, int toAccNo, int amount);

}
