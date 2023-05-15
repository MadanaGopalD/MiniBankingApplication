package org.banking;

import java.util.Scanner;

import org.accimp.CurrentAccount;
import org.accimp.SavingsAccount;
import org.jdbc.Jdbc;
import org.jdbc.JdbcConnection;

public class Banking {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int ch=0,accno=0,pin=0;
		SavingsAccount sa=null;
		CurrentAccount ca=null;
		start:
		while(true)
		{
			do 
			{
				System.out.println("Create Account 1.Savings 2.Current 3.More Options");
				ch=sc.nextInt();
				switch(ch)
				{
				case 1:
					sa=new SavingsAccount();
					sa.createAccount();
					break;
				case 2:
					ca=new CurrentAccount();
					ca.createAccount();
					break;
				}
			}
			while(ch<=2);
			savings:
			do {
				System.out.println("1.Savingsaccount Deposit 2.Savingsaccount Withdraw 3.Savingsaccount BalanceEnquiry 4.Back 5.CurrentAccount");
				ch=sc.nextInt();
				if(ch<=3)
				{
					System.out.println("Enter Account Number");
					accno=sc.nextInt();
					System.out.println("Enter the pin ");
					pin=sc.nextInt();
					if(!Jdbc.validateAccount(accno, pin))
					{
						System.out.println("Invalid Account Details");
						System.out.println("Please Try Again :(");
						continue savings;
					}
				}
				switch(ch)
				{
				case 1:
					
					sa=new SavingsAccount();
					sa.depositAmount(accno);
					break;
				case 2: 
					sa=new SavingsAccount();
					sa.withdrawAmount(accno);
					break;
				case 3: 
					sa=new SavingsAccount();
					sa.balanceEnquiry(accno);
					break;
				case 4:
					continue start;
				}
			}
			while(ch<=4);
			current:
			do
			{
				System.out.println("1. Current Acc Deposit 2. Current Acc Withdraw 3.Current Acc BalanceEnquiry 4.Back 5.Continue");
				ch = sc.nextInt();
				if(ch<=3){
				System.out.println("Enter the Account Number");
				accno = sc.nextInt();
				System.out.println("Enter the pin ");
				pin=sc.nextInt();
				if(!Jdbc.validateAccount(accno, pin))
				{
					System.out.println("Invalid Account Details");
					System.out.println("Please Try Again :(");
					continue current;
				}
				}
				switch(ch){
				case 1:
					ca=new CurrentAccount();
					ca.depositAmount(accno);
				break;
				case 2: 
					ca=new CurrentAccount();
					ca.withdrawAmount(accno);
				break;
				case 3: 
					ca=new CurrentAccount();
					ca.balanceEnquiry(accno);
				break;
				case 4:
					continue start;
				}
					
				}
			while(ch<=4);
			System.out.println("Would you like to continue ?? Input 1 to continue... Anyother to exit");
					int num = sc.nextInt();
					if(num!=1)
					{
						System.out.println("Thank You :)");
						JdbcConnection.closeConnection();
						System.out.println("Connection Closed ");
						break;
					}
			}
	}

}
