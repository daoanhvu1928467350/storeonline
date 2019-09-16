package com.storeonline.DTO;

public class CompanyTO {
	private long companyId;
	private String companyName;
	private String address;
	private String phone;
	private String fax;

	public CompanyTO(long companyId, String companyName, String address, String phone, String fax) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
	}

	public CompanyTO(String companyName, String address, String phone, String fax) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
