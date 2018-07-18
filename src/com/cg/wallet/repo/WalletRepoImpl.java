package com.cg.wallet.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.cg.wallet.beans.Customer;

public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data=new HashMap<>();
	
	
	public Map<String, Customer> getData() {
		return data;
	}
	public void setData(Map<String, Customer> data) {
		this.data = data;
	}

	Customer cust=new Customer();
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}
	public WalletRepoImpl() {
		
	}
	;
	Connection con;
	{
		try
		{
		String url="jdbc:mysql://localhost:3306/test";
		String user="root";
		String pass="corp123";
		
		con=DriverManager.getConnection(url,user,pass);
				
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}	
	@Override
	public boolean save(Customer customer) {
		if(findOne(customer.getMobileNo())==null)
	{
		String query="insert into Customer values("+"customer.getName()"+","
	+"customer.getMobileNo()"+","+customer.getWallet().getBalance()+")";
		try {
			con.setAutoCommit(false);
			Statement st=con.createStatement();
			st.execute(query);
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}else
	{
		String query="update Customer set balance="+customer.getWallet().getBalance()+
				"where mobileNo="+customer.getMobileNo();
		try {
			con.setAutoCommit(false);
			Statement st=con.createStatement();
			st.execute(query);
			con.commit();			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}	
	}
	@Override
	public Customer findOne(String mobileNo) {		
		Customer cust=new Customer();
		String query="select * from Customer where mobileNo="+mobileNo;
		int count=0;
		try{
			con.setAutoCommit(false);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.next())
			{
				cust.setName(rs.getString(1));
				cust.setMobileNo(rs.getString(2));
				cust.getWallet().setBalance(rs.getBigDecimal(3));
				count++;
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(count==0)
		{
			return null;
		}
				 return cust;
}

}