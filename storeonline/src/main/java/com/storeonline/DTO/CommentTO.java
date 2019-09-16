package com.storeonline.DTO;

import java.util.Date;

public class CommentTO {
    private long id;
    private long productId;
    private String title;
    private long customerId;
    private String content;
    private Date date;
	public CommentTO(long id, long productId, String title, long customerId, String content, Date date) {
		super();
		this.id = id;
		this.productId = productId;
		this.title = title;
		this.customerId = customerId;
		this.content = content;
		this.date = date;
	}
	
	public CommentTO(long id, long productId, long customerId, String content, Date date) {
		super();
		this.id = id;
		this.productId = productId;
		this.customerId = customerId;
		this.content = content;
		this.date = date;
	}

	

	public CommentTO(long id, long customerId, String content, Date date) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.content = content;
		this.date = date;
	}

	public CommentTO(long productId, String title, long customerId, String content, Date date) {
		super();
		this.productId = productId;
		this.title = title;
		this.customerId = customerId;
		this.content = content;
		this.date = date;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
