package com.cg.wallet.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.wallet.beans.Customer;
import com.cg.wallet.beans.Wallet;
import com.cg.wallet.service.WalletService;
import com.cg.wallet.service.WalletServiceImpl;



import com.cg.wallet.exception.InvalidInputException;

import static org.junit.Assert.*;
public class TestClass {
	WalletService service;
	Customer cust1,cust2,cust3;	
		@Before
		public void initData(){
			 Map<String,Customer> data= new HashMap<String, Customer>();
			 cust1=new Customer("Raghav", "9600045352",new Wallet(new BigDecimal(9000)));
			  cust2=new Customer("Divya", "9841782619",new Wallet(new BigDecimal(6000)));
			  cust3=new Customer("Usha", "9283167596",new Wallet(new BigDecimal(7000)));
			 
						
					
			 data.put("9600045352", cust1);
			 data.put("9841782619", cust2);	
			 data.put("9283167596", cust3);	
				service= new WalletServiceImpl(data);
		
		}
		@Test(expected=NullPointerException.class)
		public void testCreateAccount() {
			
			service.createAccount(null,null,null);
			
			
		}
		@Test
		public void testCreateAccount1() {
			
			
			Customer c=new Customer();
			Customer cust=new Customer();
			cust=service.createAccount("Raghav","9600045352",new BigDecimal(7000));
			c.setMobileNo("9600045352");
			c.setName("Raghav");
			c.setWallet(new Wallet(new BigDecimal(7000)));
			Customer actual =c;
			Customer expected=cust;
			assertEquals(expected, actual);
		
			
			
		}
	@Test	
	public void testCreateAccount2() {
			
			
			
			Customer cust=new Customer();
			cust=service.createAccount("Raghav","9600045352",new BigDecimal(7000));
			assertEquals("Raghav", cust.getName());
		
			
			
		}
	@Test
	public void testCreateAccount3() {
		
		Customer cust=new Customer();
		cust=service.createAccount("Raghav","9600045352",new BigDecimal(7000));
		assertEquals("9600045352", cust.getMobileNo());

		
		
	}


	@Test(expected=InvalidInputException.class)
	public void testShowBalance() {
		Customer cust=new Customer();
	cust=service.showBalance("9600045352");

	}

	@Test
	public void testShowBalance2() {
		
		Customer cust=new Customer();
		
	cust=service.showBalance("9600045352");
	assertEquals(cust, cust3);

	}
	@Test
	public void testShowBalance3() {
		
		Customer cust=new Customer();
	cust=service.showBalance("9600045352");
	BigDecimal actual=cust.getWallet().getBalance();
	BigDecimal expected=new BigDecimal(9000);
	assertEquals(expected, actual);

	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer(null, null,new BigDecimal(7000));
	}

	@Test
	public void testFundTransfer2() {
		cust1=service.fundTransfer("9600045352","9841782619",new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit()
	{
		service.depositAmount("9600045352", new BigDecimal(2000));
	}
		
	@Test
	public void testDeposit2()
	{
		cust1=service.depositAmount("9600045352", new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw()
	{
		service.withdrawAmount("9600045352", new BigDecimal(2000));
	}
		
	@Test
	public void testWithdraw2()
	{
		cust1=service.withdrawAmount("9600045352", new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(4000);
		assertEquals(expected, actual);
	}	
	@Test
	public void TestValidate()
	{
		Customer customer=new Customer("Kavya","8796543210",new Wallet(new BigDecimal(10)));
		service.acceptCustomerDetails(customer);
	}
	@After
	public void testAfter()
	{
		service=null;
	}

}