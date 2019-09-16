package com.storeonline.DTO;

import java.util.Date;

public class BillTO {
	private long id;
	private long customer_id;
	private String address;
	private String district;
	private String city;
	private Date dateMotified;
	private String Status;
	private long total;
	private long productId;
	private int quatity;

	public BillTO(long id, long customer_id, String address, String district, String city, Date dateMotified,
			String status, long total) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.address = address;
		this.district = district;
		this.city = city;
		this.dateMotified = dateMotified;
		Status = status;
		this.total = total;
	}

// dung cho detail bill
	public BillTO(long id, long productId, int quatity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quatity = quatity;
	}

	public BillTO(long customer_id, String address, String district, String city, Date dateMotified, String status,
			long total) {
		super();
		this.customer_id = customer_id;
		this.address = address;
		this.district = district;
		this.city = city;
		this.dateMotified = dateMotified;
		Status = status;
		this.total = total;
	}

	public BillTO(long customer_id, String address, String district, String city, Date dateMotified, String status,
			long total, long productId, int quatity) {
		super();
		this.customer_id = customer_id;
		this.address = address;
		this.district = district;
		this.city = city;
		this.dateMotified = dateMotified;
		Status = status;
		this.total = total;
		this.productId = productId;
		this.quatity = quatity;
	}

	// dung cho detail bill
	public BillTO(long productId, int quatity) {
		super();
		this.productId = productId;
		this.quatity = quatity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateMotified() {
		return dateMotified;
	}

	public void setDateMotified(Date dateMotified) {
		this.dateMotified = dateMotified;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}

}
