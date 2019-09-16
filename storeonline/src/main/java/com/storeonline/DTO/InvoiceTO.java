package com.storeonline.DTO;

import java.util.Date;

public class InvoiceTO {
	private long id;
	private long customer_id;
	private long companyId;
	private Date dateMotified;
	private long total;
	private long productId;
	private int quatity;
	private long priceBuy;

	public InvoiceTO(long id, long customer_id, long companyId, Date dateMotified, long total) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.companyId = companyId;
		this.dateMotified = dateMotified;
		this.total = total;
	}

	public InvoiceTO(long id, long customer_id, long companyId, Date dateMotified, long total, long productId,
			int quatity, long priceBuy) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.companyId = companyId;
		this.dateMotified = dateMotified;
		this.total = total;
		this.productId = productId;
		this.quatity = quatity;
		this.priceBuy = priceBuy;
	}

	public InvoiceTO(long id, long productId, int quatity, long priceBuy) {
		super();
		this.id = id;
		this.productId = productId;
		this.quatity = quatity;
		this.priceBuy = priceBuy;
	}

	public InvoiceTO(long customer_id, long companyId, Date dateMotified, long total, long productId, int quatity,
			long priceBuy) {
		super();
		this.customer_id = customer_id;
		this.companyId = companyId;
		this.dateMotified = dateMotified;
		this.total = total;
		this.productId = productId;
		this.quatity = quatity;
		this.priceBuy = priceBuy;
	}

	public InvoiceTO(long customer_id, long companyId, Date dateMotified, long total) {
		super();
		this.customer_id = customer_id;
		this.companyId = companyId;
		this.dateMotified = dateMotified;
		this.total = total;
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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public Date getDateMotified() {
		return dateMotified;
	}

	public void setDateMotified(Date dateMotified) {
		this.dateMotified = dateMotified;
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

	public long getPriceBuy() {
		return priceBuy;
	}

	public void setPriceBuy(long priceBuy) {
		this.priceBuy = priceBuy;
	}
}
