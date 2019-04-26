package com.cg.ba.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.ba.DTO.CustomerDetails;
 
import DBConn.DBConnection;

public class BankAppDaoImpl1 implements IBankAppDao1 {

	 int j=0,q=0;
	 int i=0,k=0,p=0;
	static int bal1, bal2, bal3,bal4,bal5;


	DBConnection dbCon = new DBConnection();

	public int registerCustomer(CustomerDetails custDetails) {

		try {

			Connection con = dbCon.connect();
			Statement statement = con.createStatement();

			PreparedStatement preparedStatement = con
					.prepareStatement("INSERT INTO CUST_DETAILS VALUES(BANK_SEQUENCE.NEXTVAL,?,?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, custDetails.getFirstName());
			preparedStatement.setString(2, custDetails.getLastName());
			preparedStatement.setString(3, custDetails.getEmailId());
			preparedStatement.setString(4, custDetails.getPassword());
			preparedStatement.setString(5, custDetails.getPancardNo());
			preparedStatement.setString(6, custDetails.getAadharNo());
			preparedStatement.setString(7, custDetails.getAddress());
			preparedStatement.setString(8, custDetails.getMobile());
			preparedStatement.setInt(9, custDetails.getBalance());
			preparedStatement.executeUpdate();

			ResultSet resultset = statement.executeQuery("select * from CUST_DETAILS");
			while (resultset.next()) {
				if (resultset.getString(7).equals(custDetails.getAadharNo())) {
					i = resultset.getInt(1);
				}
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	public int login(int accNo, String password) {

	 
		try {
			Connection con = dbCon.connect();
			Statement statement = con.createStatement();

			ResultSet resultset = statement.executeQuery("select * from CUST_DETAILS ");

			while (resultset.next()) {

				if (resultset.getInt(1) == accNo && resultset.getString(5).equals(password)) {

					// int accno = resultset.getInt(10);

					j++;

				}
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return j;

	}
	
	public int deposit(CustomerDetails custDetails, int amount) {

		try {
			 
DBConnection dbcon = new DBConnection();
Connection con =dbcon.connect();
			Statement statement = con.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from CUST_DETAILS ");

			while (resultset.next()) {

				if (resultset.getInt(1) == custDetails.getAccNo()) {

					bal1 = resultset.getInt(10);
				}

			}

			bal1 += amount;
			PreparedStatement preparedStatement = con
					.prepareStatement("UPDATE CUST_DETAILS SET balance=? where ACCOUNTNO=?");
			preparedStatement.setInt(1, bal1);
			preparedStatement.setInt(2, custDetails.getAccNo());
			k = preparedStatement.executeUpdate();

			System.out.println("Error");
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return k;
	}

	public int withdrawal( CustomerDetails custDetails,int amount) {

		try {
			DBConnection dbcon = new DBConnection();
			Connection con =dbcon.connect();
			 		Statement statement = con.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from CUST_DETAILS ");

			while (resultset.next()) {

				if (resultset.getInt(1) == custDetails.getAccNo()) {

					bal2 = resultset.getInt(10);
				}

			}

			bal2 -= amount;
			PreparedStatement preparedStatement = con
					.prepareStatement("UPDATE CUST_DETAILS SET balance=? where ACCOUNTNO=?");
			preparedStatement.setInt(1, bal2);
			preparedStatement.setInt(2, custDetails.getAccNo());
			p = preparedStatement.executeUpdate();

		}   catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	public int showBalance(CustomerDetails custDetails) {

		try {
			DBConnection dbcon = new DBConnection();
			Connection con =dbcon.connect();
			Statement statement = con.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from CUST_DETAILS ");

			while (resultset.next()) {

				if (resultset.getInt(1) == custDetails.getAccNo()) {

					bal3 = resultset.getInt(10);
				}

			}
		 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bal3;
	}

	public int transferFund(CustomerDetails custDetails, int toAccNo, int amount) {




		try {
			DBConnection dbcon = new DBConnection();
			Connection con =dbcon.connect();
			Statement statement = con.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from CUST_DETAILS ");

			while (resultset.next()) {

				if (resultset.getInt(1) == custDetails.getAccNo()) {

					bal4 = resultset.getInt(10);
					
					 
				}

			}
			
			if(bal4> amount) {
			bal4-= amount;

			PreparedStatement preparedStatement = con
					.prepareStatement("UPDATE CUST_DETAILS SET balance=? where ACCOUNTNO=?");
			preparedStatement.setInt(1, bal4);
			preparedStatement.setInt(2, custDetails.getAccNo());
			 preparedStatement.executeUpdate();
			
			 ResultSet resultset1 = statement
						.executeQuery("select * from CUST_DETAILS ");
			 while (resultset1.next()) {

					if (resultset1.getInt(1) == toAccNo) {

						bal5 = resultset1.getInt(10);
						 
					}

				}
				bal5+= amount;

				PreparedStatement preparedStatement1 = con
						.prepareStatement("UPDATE CUST_DETAILS SET balance=? where ACCOUNTNO=?");
				preparedStatement1.setInt(1, bal5);
				preparedStatement1.setInt(2, toAccNo);
				preparedStatement1.executeUpdate();
			
				PreparedStatement preparedStatement2 = con
						.prepareStatement("insert into Transactions values(TRAN_SEQ.NEXTVAL,?,?,?)");
				preparedStatement2.setInt(1, custDetails.getAccNo());
				preparedStatement2.setInt(2, toAccNo);
				preparedStatement2.setInt(3, amount);
				q=preparedStatement2.executeUpdate();
			 				
			}
			
			
			else 
				return 0;
			
		}   catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		return q;
	}

}