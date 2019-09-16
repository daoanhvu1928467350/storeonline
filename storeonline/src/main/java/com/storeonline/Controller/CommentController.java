package com.storeonline.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.storeonline.DAO.CommentDA;
import com.storeonline.DTO.CommentTO;

@RestController
public class CommentController {
	@Autowired
	private CommentDA commentDA;

// add comment
	@PostMapping(value = "/Comment/add", produces = { "application/json" })
	public boolean addNew(WebRequest request) {
		
		String productId = request.getParameter("ID_Product") != null ? request.getParameter("ID_Product") : "";
		String title = request.getParameter("Title") != null ? request.getParameter("Title") : "";
		String customerid = request.getParameter("customer_id") != null ? request.getParameter("customer_id") : "";
		String content = request.getParameter("Content") != null ? request.getParameter("Content") : "";
		Date date;
		long productIdLong, customerIdLong;
		productIdLong = Long.parseLong(productId);
		customerIdLong = Long.parseLong(customerid);
		// TODO Auto-generated catch block
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		date = new Date();
		df.format(date);
		CommentTO commentTO = new CommentTO(productIdLong, title, customerIdLong, content, date);
		return commentDA.addNew(commentTO);
	}

	// add reponse comment
	@PostMapping(value = "/Comment/addReponseComment", produces = { "application/json" })
	public boolean addNewReponse(WebRequest request) {
		String id = request.getParameter("id") != null ? request.getParameter("id") : "";
		

		String customerid = request.getParameter("customer_id") != null ? request.getParameter("customer_id") : "";
		String content = request.getParameter("Content") != null ? request.getParameter("Content") : "";
		Date date;
		long IdLong, customerIdLong;
		IdLong = Long.parseLong(id);
		customerIdLong = Long.parseLong(customerid);
		// TODO Auto-generated catch block
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		date = new Date();
		df.format(date);
		CommentTO commentTO = new CommentTO(IdLong, customerIdLong, content, date);
		return commentDA.addResponseComment(commentTO);
	}

// remove comment
	@DeleteMapping(value = "/Comment/Delete/{id}", produces = { "application/json" })
	public boolean removeComment(@PathVariable String id) {
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			idLong = 0;
		}
		return commentDA.RemoveComment(idLong);
		
	}

// update comment
	@PutMapping(value="/Comment/updateComment", produces = { "application/json" })
	public boolean removeComment(@RequestBody Map<String,String> body) {
		String id=body.get("id")!=null?body.get("id"):"";
		String productId=body.get("productid")!=null?body.get("productid"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			idLong = 0;
		}
		
		String customeridStr=body.get("customerid")!=null?body.get("customerid"):"";
		String content=body.get("content")!=null?body.get("content"):"";
		Date date;
		String dateStr = body.get("date") != null ? body.get("date") : "";
		try {
			date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			date = new Date();
			df.format(date);
		}
		long customeridLong,productIdLong;
		try {
			productIdLong=Long.parseLong(productId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			productIdLong=0;
		}
		String title=body.get("title")!=null?body.get("title"):"";
		customeridLong=Long.parseLong(customeridStr);
		CommentTO commentTO=new CommentTO(idLong,productIdLong,title,customeridLong,content,date);
		return commentDA.UpdateComment(commentTO);
		
	}
	// update reponse comment
	@PutMapping(value="/Comment/updateReponseComment", produces = { "application/json" })
	public boolean removeReponseComment(@RequestBody Map<String,String> body) {
		String id=body.get("id")!=null?body.get("id"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			idLong = 0;
		}
		
		String customeridStr=body.get("customerid")!=null?body.get("customerid"):"";
		String content=body.get("content")!=null?body.get("content"):"";
		Date date;
		String dateStr = body.get("date") != null ? body.get("date") : "";
		try {
			date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			date = new Date();
			df.format(date);
		}
		long customeridLong;
		
		try {
			customeridLong=Long.parseLong(customeridStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			customeridLong=0;
		}
		CommentTO commentTO=new CommentTO(idLong,customeridLong,content,date);
		return commentDA.UpdateReponseComment(commentTO);
		
	}
// select by product id 
	@GetMapping(value = "/retrieveCommentByProductId/{productid}", produces = { "application/json" })
	public ArrayList<CommentTO> retrieveCommentByProductId(@PathVariable String productid) {
		long productidLong;
		try {
			productidLong = Long.parseLong(productid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			productidLong = 0;
		}
		return commentDA.retrieveByProductId(productidLong);

	}

// select comment by comment id
	@GetMapping(value = "/retrieveCommentById/{id}", produces = { "application/json" })
	public CommentTO retrieveCommentById(@PathVariable String id) {
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		return commentDA.retrieveCommentById(idLong);
	}

// find all
	@GetMapping(value = "/Comment", produces = { "application/json" })
	public ArrayList<CommentTO> findAll() {
		return commentDA.findAll();
	}
}
