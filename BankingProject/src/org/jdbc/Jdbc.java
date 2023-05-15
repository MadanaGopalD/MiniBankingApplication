package org.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.account.Account;

public class Jdbc {
	
	public static Connection con=JdbcConnection.getDbConnection();

	public static int getMoney(int accountnumber) 
	{
		int money=0;
		int amount=0;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String query="select balance from madan.banking where accountnumber= ?";
		try 
		{
			System.out.println("Fetching All the details...");
			Thread.sleep(500);
			stmt=con.prepareStatement(query);
			stmt.setInt(1, accountnumber);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				 money+=rs.getInt("balance");
			}
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
		}
		return money;
	}
	
	
	public static boolean createAccount(Account acc,String type) 
	{
		PreparedStatement stmt=null;
		boolean result=false;
		String query = "insert into madan.banking values(?,?,?,?,?,?,?)";
		try 
		{
			stmt=con.prepareStatement(query);
			stmt.setInt(1, acc.getAccountNumber());
			stmt.setString(2, acc.getAccountHolderName());
			stmt.setInt(3, 0);
			stmt.setFloat(4, acc.getRateOfInterest());
			stmt.setString(5, acc.isOverdraft()+"");
			stmt.setString(6, type);
			stmt.setInt(7, acc.getPin());
			if(stmt.executeUpdate()>=1)
				{
					result=true;
				}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static void getAccountData(int accNo)
	{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String query="select * from madan.banking where accountnumber= ? ";
		try 
		{
			stmt=con.prepareStatement(query);
			stmt.setInt(1, accNo);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				System.out.println("Account No :"+ rs.getInt(1)+"\t"+"Account Holder Name: "+rs.getString(2)
				+"\t"+"Balance: "+rs.getInt(3)+"\t"+"Rate Of Interest: "+rs.getInt(4)+"\t"+"OverDraft Allowed: "+rs.getString(5)
				+"\t"+"Account Type: "+rs.getString(6)+" Account");
				System.out.println();		
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void depositMoney(int money,int accountNumber)
	{
		PreparedStatement preStm=null;
		String query="update madan.banking set balance=? where accountnumber=?";
		int bal=0;
		bal+=getMoney(accountNumber);
		try 
		{
			preStm=con.prepareStatement(query);
			preStm.setInt(1, bal+money);
			preStm.setInt(2, accountNumber);
			if(preStm.executeUpdate()>=1)
			{
				System.out.println("Amount Deposited! ");
				System.out.println("Updated Balance is ");
				Thread.sleep(200);
				System.out.println(getMoney(accountNumber));
			}
		}
		catch (SQLException | InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getAccountType(int accountNumber)
	{
		PreparedStatement preStm=null;
		ResultSet rs=null;
		String accountType=null;
		String query= "select accounttype from madan.banking where accountnumber=?";
		try 
		{
			preStm=con.prepareStatement(query);
			preStm.setInt(1,accountNumber);
			rs=preStm.executeQuery();
			while(rs.next())
			{
				accountType=rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(accountType);
		return accountType;
	}
	public static void withdrawMoney(int accountNumber,int money)
	{
		PreparedStatement preStm=null;
		ResultSet rs=null;
		String query= "update madan.banking set balance= ? where accountnumber=?";
		int bal=0;
		bal+=getMoney(accountNumber);
		if(money>bal && getAccountType(accountNumber).equalsIgnoreCase("savings"))
		{
			System.out.println("Insufficient Balance");
		}
		else
		{
			try 
			{
				preStm=con.prepareStatement(query);
				preStm.setInt(1, bal-money);
				preStm.setInt(2, accountNumber);
				if(preStm.executeUpdate()>=1)
				{
					System.out.println("Money Withdrawn.");
					System.out.println("Updated Balance is "+getMoney(accountNumber));
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean validateAccount(int accNo,int pin)
	{
		PreparedStatement preStm=null;
		ResultSet rs=null;
		boolean result=false;
		String query= "select * from madan.banking where accountnumber=? and pin=?";
		try 
		{
			preStm=con.prepareStatement(query);
			preStm.setInt(1, accNo);
			preStm.setInt(2, pin);
			rs=preStm.executeQuery();
			if(rs.next())
			{
				 result=true;
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
