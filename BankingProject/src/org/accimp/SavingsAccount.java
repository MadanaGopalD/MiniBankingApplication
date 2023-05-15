package org.accimp;
import java.util.Scanner;

import org.account.Account;
import org.jdbc.Jdbc;

public class SavingsAccount extends Account{
	
	public void createAccount(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Account Holder Name");
		accountHolderName = scan.nextLine();
		rateOfInterest = 5;
		int max = 50000;
		int min = 10000;
		accountNumber = (int)Math.floor(Math.random() * (max - min + 1) + min);
		pin=(int)Math.floor(Math.random()*(999-100+1)+100);
		System.out.println("Your Account Number is: "+accountNumber);
		System.out.println("Your Pin is: "+pin);
		if(Jdbc.createAccount(this,"savings"))
		{
			System.out.println("Account Created Sucessfully! :) ");
			showAccountDetails();
		}
		
		
		}
	
	
}
