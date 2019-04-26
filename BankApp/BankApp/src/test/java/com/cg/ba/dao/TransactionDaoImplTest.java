package com.cg.ba.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TransactionDaoImplTest {
	static TransactionDaoImpl td;
	@BeforeAll
	public static void init() {
		td = new TransactionDaoImpl();
	}
	@Test
	void testWithdraw() {
		assertEquals(800, td.withdraw(1000, 200, 1000));
	}

	@Test
	void testDeposit() {
		assertEquals(1600, td.deposit(1002, 400, 1200));
	}

	@Test
	void testShowBalance() {
		assertEquals(1000, td.showBalance(1003));
	}

	@Test
	void testFundTransfer() {
		assertEquals(1500, td.fundTransfer(1002, 1001, 100));
	}

}
