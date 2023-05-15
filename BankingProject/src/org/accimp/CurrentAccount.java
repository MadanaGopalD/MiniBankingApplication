package org.accimp;
import java.util.Random;
import java.util.Scanner;

import org.account.Account;
import org.jdbc.Jdbc;


public class CurrentAccount extends Account{
	
	Scanner scan = new Scanner(System.in);
	
	public void createAccount()
	{
		System.out.println("Enter the Account Holder Name");
		accountHolderName = scan.nextLine();
		int max=100000;
		int min=50000;
		accountNumber = (int)Math.floor(Math.random() * (max - min + 1) + min);
//		pin=(int)Math.floor(Math.random()*(999-100+1)+100);
		Random random=new Random();
		pin=random.nextInt(10000);
		rateOfInterest = 0;
		overdraft = true;
		System.out.println("Your Account Number is: "+accountNumber);
		System.out.println("Your Pin is: "+pin);
		if(Jdbc.createAccount(this,"current"))
		{
			System.out.println("Account Created Sucessfully! :) ");
			showAccountDetails();
		}
	}
}
