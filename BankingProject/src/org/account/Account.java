package org.account;
import java.util.Scanner;

import org.intf.IAccount;
import org.jdbc.Jdbc;

public class Account implements IAccount
{
	protected int accountNumber;
	protected String accountHolderName;
	protected long accountBalance;
	protected float rateOfInterest;
	protected boolean overdraft;
	protected int pin;
	Scanner scan=new Scanner(System.in);
	
	public void showAccountDetails()
	{
		System.out.println("Enter account number to view your account details");
		int acc = scan.nextInt();
		System.out.println();
		Jdbc.getAccountData(acc);
	}
	
	@Override
	public void balanceEnquiry(int accountNumber) 
	{
		long money;
		money=Jdbc.getMoney(accountNumber);
		System.out.println("balance is: "+money);
	}

	@Override
	public void depositAmount(int accountNumber) 
	{
			System.out.println("Enter amount to be deposited");
			int amount = scan.nextInt();
//			db.depositAmount(amount,accountNumber);
			Jdbc.depositMoney(amount, accountNumber);
	}

	@Override
	public void withdrawAmount(int accountNumber) 
	{
			System.out.println("Enter the amount to withdraw");
			int amount = scan.nextInt();
//			db.Dbwithdraw(amount,accountNumber);
			Jdbc.withdrawMoney(accountNumber,amount);
	}
	
	public int getAccountNumber() 
	{
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() 
	{
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) 
	{
		this.accountHolderName = accountHolderName;
	}

	public float getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(float rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public boolean isOverdraft() {
		return overdraft;
	}

	public void setOverdraft(boolean overdraft) {
		this.overdraft = overdraft;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
}
