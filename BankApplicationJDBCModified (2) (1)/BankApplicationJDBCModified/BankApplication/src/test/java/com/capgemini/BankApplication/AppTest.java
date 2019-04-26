package com.capgemini.BankApplication;

 
 import org.junit.jupiter.api.BeforeAll;
 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.Test;

 
import com.cg.ba.DTO.CustomerDetails;
import com.cg.ba.Dao.BankAppDaoImpl1;
 import com.cg.ba.Dao.IBankAppDao1;
 
 
public class AppTest 
{
	static CustomerDetails custdetails;
	static IBankAppDao1 bd1 ;
	 
	
	@BeforeAll
	

	
	public static void init(){
		
		custdetails = new CustomerDetails();
		bd1 = new BankAppDaoImpl1();
		 
	}
    
    @Test
    public void testRegisterCustomer()
    {
    	
    	custdetails.setFirstName("prakhya");
    	custdetails.setLastName("sri");
        custdetails.setEmailId("pra@mail");
    	custdetails.setPassword("prakhya123");
    	custdetails.setPancardNo("agfhf5464");
    	custdetails.setAadharNo("123412341234");
    	custdetails.setAddress("Hyderabad");
    	custdetails.setMobile("9876543216");
    	custdetails.setBalance(0);
    	
        assertEquals(1012,bd1.registerCustomer(custdetails));
    }
    
    @Test
    public void testLogin() {
    	 
    	assertEquals(1, bd1.login(1002, "56780"));
    }
   @Test
    public void testDeposit() {
    	custdetails.setAccNo(1002);
    	assertEquals(1, bd1.deposit(custdetails, 1000));
    }
    @Test
    public void testWithdraw() {
    	custdetails.setAccNo(1002);
    	assertEquals(1, bd1.withdrawal(custdetails, 100));
    }
    @Test
    public void testShowBalance() {
    	custdetails.setAccNo(1001);
    	assertEquals(200, bd1.showBalance(custdetails));
    	
    }
    @Test
    public void testFundTransfer() {
    	
    	custdetails.setAccNo(1002);
    	
    	assertEquals(1, bd1.transferFund(custdetails, 1008, 100));
    }
    
    
}
