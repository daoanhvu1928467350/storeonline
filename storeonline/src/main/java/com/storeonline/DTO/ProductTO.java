package com.storeonline.DTO;

public class ProductTO {

	private long productId;
	private String productName;
	private int productQuanity;
	private long productPrice;
	private String productImg;
	private String productDescription;
	private int productCategory;

	public ProductTO(String productName, int productQuanity, long productPrice, String productImg,
			String productDescription, int productCategory) {
		super();
		this.productName = productName;
		this.productQuanity = productQuanity;
		this.productPrice = productPrice;
		this.productImg = productImg;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
	}

	public ProductTO(long productId, String productName, int productQuanity, long productPrice, String productImg,
			String productDescription) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuanity = productQuanity;
		this.productPrice = productPrice;
		this.productImg = productImg;
		this.productDescription = productDescription;
	}

	public ProductTO(long productId, String productName, int productQuanity, long productPrice, String productImg,
			String productDescription, int productCategory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuanity = productQuanity;
		this.productPrice = productPrice;
		this.productImg = productImg;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuanity() {
		return productQuanity;
	}

	public void setProductQuanity(int productQuanity) {
		this.productQuanity = productQuanity;
	}

	public long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}

}
