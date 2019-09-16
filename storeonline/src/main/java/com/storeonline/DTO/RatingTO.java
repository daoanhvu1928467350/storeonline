package com.storeonline.DTO;

public class RatingTO {
	private long ratingId;
	private String title;
	private long customer_id;
	private int rating;
	private String content;

	public RatingTO(long ratingId, String title, long customer_id, int rating, String content) {
		super();
		this.ratingId = ratingId;
		this.title = title;
		this.customer_id = customer_id;
		this.rating = rating;
		this.content = content;
	}

	public RatingTO(String title, long customer_id, int rating, String content) {
		super();
		this.title = title;
		this.customer_id = customer_id;
		this.rating = rating;
		this.content = content;
	}

	public long getRatingId() {
		return ratingId;
	}

	public void setRatingId(long ratingId) {
		this.ratingId = ratingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
