package com.cg.ba.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.ba.model.CustomerDetails;

class UserEntryDaoImplnTest {
	static UserEntryDaoImpl user;
	static CustomerDetails cd;
	@BeforeAll
	public static void init()
	{
		user = new UserEntryDaoImpl();
		cd = new CustomerDetails();
	}
//	@Test
//	void testRegister() {
//		cd.setFirstName("vindhya");
//		cd.setLastName("sri");
//		cd.setEmailId("vindhya@gmail.com");
//		cd.setPassword("7897");
//		cd.setPancardNo(987654);
//		cd.setAadharNo("345877890156");
//		cd.setAddress("hyd");
//		cd.setMobileNo("9876843214");
//		cd.setBalance(100);
//		int acc = user.register(cd);
//		assertEquals(1004, acc);
//	}

	@Test
	void testLogin() {
		cd = user.login(1002, "1432");
		assertEquals(1002, cd.getAccountNo());
	}

}

