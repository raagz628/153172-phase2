package com.cg.wallet.pl;

import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.wallet.beans.Customer;
import com.cg.wallet.service.WalletService;
import com.cg.wallet.service.WalletServiceImpl;

public class Client {
	public static void main(String[] args) {
		WalletService service;
		{	
			service=new WalletServiceImpl();
		}
		String ans;	
		Scanner sc=new Scanner(System.in);
		System.out.println("****Paytm Application****");
		Customer customer=new Customer();
		do
		{
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance");
			System.out.println("3. Withdraw amount");
			System.out.println("4. Deposit amount");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Exit");			
			System.out.println("\nPlease Select an option:");
			int choice=sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Enter Name: ");
				String name=sc.next();
				System.out.println("Enter Mobile Number: ");
				String mobileNo=sc.next();
				System.out.println("Enter Balance");
				BigDecimal amount=sc.nextBigDecimal();				
				customer=service.createAccount(name, mobileNo, amount);
				if(customer!=null){
				System.out.println("Your account is successfully created!");
				System.out.println(customer);
				}
				else
					System.out.println("Account Already Exist,Plz Change mobileno");
				break;				
			case 2:				
				System.out.println("Enter the mobile number to view balance: ");
				String mobileNo2=sc.next();
				customer=service.showBalance(mobileNo2);
				System.out.println("Your balance for mobile number: "+mobileNo2+" is "+customer.getWallet());
				break;
			case 3:
				System.out.println("Enter Mobile Number: ");
				String mobileNo4=sc.next();
				System.out.println("Enter amount to withdraw");
				BigDecimal amount4=sc.nextBigDecimal();				
				customer=service.withdrawAmount(mobileNo4, amount4);
				System.out.println(customer);
				break;
			case 4:
				System.out.println("Enter Mobile Number: ");
				String mobileNo3=sc.next();
				System.out.println("Enter amount to deposit");
				BigDecimal amount3=sc.nextBigDecimal();				
				customer=service.depositAmount(mobileNo3, amount3);
				System.out.println(customer);
				break;
			case 5:

				System.out.println("Enter Source Mobile Number: ");
				String sourceMobileNo=sc.next();
				System.out.println("Enter Target Mobile Number: ");
				String targetMobileNo=sc.next();
				System.out.println("Enter amount to transfer");
				BigDecimal amount1=sc.nextBigDecimal();				
				customer=service.fundTransfer(sourceMobileNo, targetMobileNo, amount1);
				System.out.println(customer);
				break;

			case 6:
				System.out.println("Thank You");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid options");
				break;
			}			
			System.out.println("\nDo you want to continue: Y/N ");
			ans=sc.next();
		}while(ans.equalsIgnoreCase("y")|| ans.equalsIgnoreCase("yes"));
		System.out.println("\nThank you for using this service!");	

	}

}