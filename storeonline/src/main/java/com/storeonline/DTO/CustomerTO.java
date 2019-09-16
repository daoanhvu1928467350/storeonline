package com.storeonline.DTO;

public class CustomerTO {
 
	private long id;
	private String username;
	private String password;
	private String address;
	private String status;
	private long amt_balance;
	public CustomerTO(long id, String username, String password, String address, String status, long amt_balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.status = status;
		this.amt_balance = amt_balance;
	}
	
	public CustomerTO() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public long getAmt_balance() {
		return amt_balance;
	}

	public void setAmt_balance(long amt_balance) {
		this.amt_balance = amt_balance;
	}

	
	
	
	
	
}
