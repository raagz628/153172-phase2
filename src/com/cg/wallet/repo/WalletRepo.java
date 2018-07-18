package com.cg.wallet.repo;

import com.cg.wallet.beans.Customer;

public interface WalletRepo {

	public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);

}
