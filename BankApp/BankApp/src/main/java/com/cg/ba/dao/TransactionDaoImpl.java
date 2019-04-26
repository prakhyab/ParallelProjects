package com.cg.ba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.ba.model.CustomerDetails;
import com.cg.ba.utility.Database;
import com.cg.ba.utility.InsufficientBalanceException;
import com.cg.ba.utility.InvalidIdException;

public class TransactionDaoImpl implements TransactionDao {
	Database db = new Database();
	Connection con = db.getConnection();
	CustomerDetails cd = new CustomerDetails();
	public int withdraw(int accountNo, int amount, int balance) {
		// TODO Auto-generated method stub
		int c = 0;
		if(balance >= amount)
        {
            balance = balance - amount;
            int i = updateBalance(balance,accountNo);
            if(i == 1)
            {
            	c++;
            }
        }
		if(c == 1)
		{
			if(balance == 0)
				return 1;
			else
				return balance;
		}
		else
			return 0;
	}

	private int updateBalance(int balance, int accountNo) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update customer_details1 set balance = ? where account_no = ?");
			ps.setInt(1, balance);
			ps.setInt(2, accountNo);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int deposit(int accountNo, int amount, int balance) {
		// TODO Auto-generated method stub
		balance = balance + amount;
		int i = updateBalance(balance,accountNo);
		if(i == 1)
       return balance;
		else
			return 0;
	}

	public int showBalance(int accountNo) {
		// TODO Auto-generated method stub
		int bal = 0;
		System.out.println("acc"+accountNo);
		try {
			PreparedStatement ps = con.prepareStatement("select * from customer_details1 where account_no = ?");
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			bal=rs.getInt(10);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
	}

	public int fundTransfer(int fromAccountNo, int toaccountNo, int amount) {
		// TODO Auto-generated method stub
		int toBalance = 0;
		int fromBalance = 0;
		int i = 0;
		try {
			int c=0;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_details1");
			while(rs.next())
			{
				if(rs.getInt(1) == toaccountNo)
				{
					c++;
					toBalance = rs.getInt(10);
				}
				if(rs.getInt(1) == fromAccountNo)
				{
					fromBalance = rs.getInt(10);
				}
			}
			st.close();
			if(c == 1)
			{
				if(fromBalance >= amount)
				{
					i = insertAmt(fromAccountNo,toaccountNo,amount);
					if(i == 1)
					{
						toBalance += amount;
						fromBalance -= amount;
						updateBalance(fromBalance, fromAccountNo);
						updateBalance(toBalance, toaccountNo);
					}
				}
				else
				{
					throw new InsufficientBalanceException();
				}
			}	
			else
			{
				try {
					throw new InvalidIdException();
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			
		} 
		if(fromBalance != 0)
		return fromBalance;
		else return 1;
	}

	private int insertAmt(int fromAccountNo, int toaccountNo, int amount) {
		// TODO Auto-generated method stub
		int i = 0;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into transaction_details1 values(transaction_no_seq.nextval,?,?,?)");
			ps.setInt(1, fromAccountNo);
			ps.setInt(2, toaccountNo);
			ps.setInt(3, amount);
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

}
