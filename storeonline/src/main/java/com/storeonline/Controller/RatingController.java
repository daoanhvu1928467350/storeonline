package com.storeonline.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.storeonline.DAO.RatingDA;
import com.storeonline.DTO.RatingTO;
@RestController
public class RatingController {
	@Autowired
	private RatingDA ratingDA;
	// add rating
	@PostMapping(value="/Rating/add",produces= {"Application/json"})
	public boolean addRating(WebRequest request) {
		String productId=request.getParameter("productid")!=null?(String)request.getParameter("productid"):"";
		String title=request.getParameter("title")!=null?(String)request.getParameter("title"):"";
		String customerId=request.getParameter("customerid")!=null?(String)request.getParameter("customerid"):"";
		String rating=request.getParameter("rating")!=null?(String)request.getParameter("rating"):"";
		String content=request.getParameter("content")!=null?(String)request.getParameter("content"):"";
		long productIdLong,customerIdLong;
		try {
			productIdLong=Long.parseLong(productId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			productIdLong=0;
		}
		try {
			customerIdLong=Long.parseLong(customerId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			customerIdLong=0;
		}
		int ratingInt;
		ratingInt=Integer.parseInt(rating);
		RatingTO ratingTO=new RatingTO(productIdLong,title,customerIdLong,ratingInt,content);
		return ratingDA.addNew(ratingTO);
	}
	//update rating 
	@PutMapping(value="/Rating/updateRating",produces= {"Application/json"})
	public boolean updateRating(@RequestBody Map<String,String> body) {
		String productId=body.get("productid")!=null?(String)body.get("productid"):"";
		String title=body.get("title")!=null?(String)body.get("title"):"";
		String customerId=body.get("customerid")!=null?(String)body.get("customerid"):"";
		String rating=body.get("rating")!=null?(String)body.get("rating"):"";
		String content=body.get("content")!=null?(String)body.get("content"):"";
		long productIdLong,customerIdLong;
		try {
			productIdLong=Long.parseLong(productId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			productIdLong=0;
		}
		try {
			customerIdLong=Long.parseLong(customerId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			customerIdLong=0;
		}
		int ratingInt;
		ratingInt=Integer.parseInt(rating);
		RatingTO ratingTO=new RatingTO(productIdLong,title,customerIdLong,ratingInt,content);
		return ratingDA.updateRating(ratingTO);
	}
	//find rating by product id
	@GetMapping(value="/Rating/{id}",produces= {"Application/json"})
	public RatingTO retrieveRatingByProductId(@PathVariable String id) {
		long idLong;
		try {
			idLong=Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong=0;
		}
		return ratingDA.retrieveRatingByProductId(idLong);
	}
	//remove rating
	@DeleteMapping(value="/Rating/delete/{id}",produces= {"Application/json"})
	public boolean deleteRating(@PathVariable String id) {
		long idLong;
		try {
			idLong=Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong=0;
		}
		return ratingDA.removeRating(idLong);
	}
	

}
