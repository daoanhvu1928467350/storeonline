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

import com.storeonline.DAO.BillDA;
import com.storeonline.DTO.BillTO;
import com.storeonline.DTO.StaticTO;

@RestController
public class BillController {
	@Autowired
	private BillDA billDA;

	// list bill
	@GetMapping(value = "bill", produces = "application/json")
	public ArrayList<BillTO> retrieveAll() {
		return billDA.findAll();
	}

	// search bill by id
	@GetMapping(value = "bill/{id}", produces = "application/json")
	public BillTO retrieveById(@PathVariable String id) {
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		return billDA.retrieveBillById(idLong);
	}

	// add bill
	@PostMapping(value = "bill/add", produces = "application/json")
	public boolean addBill(WebRequest request) {
		String customer_id = request.getParameter("customerid") != null ? (String) request.getParameter("customerid")
				: "";
		String address = request.getParameter("address") != null ? (String) request.getParameter("address") : "";
		String district = request.getParameter("district") != null ? (String) request.getParameter("district") : "";
		String city = request.getParameter("city") != null ? (String) request.getParameter("city") : "";
		String status = StaticTO.Active;
		String total = request.getParameter("total") != null ? (String) request.getParameter("total") : "";
		String productid=request.getParameter("productid")!=null?(String)request.getParameter("productid"):"";
		String quatity=request.getParameter("quatity")!=null?(String)request.getParameter("quatity"):"";
		long totalLong, customerLong,productidLong;
		try {
			totalLong = Long.parseLong(total);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			totalLong = 0;
		}
		try {
			customerLong = Long.parseLong(customer_id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			customerLong = 0;
		}
		try {
			productidLong= Long.parseLong(productid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			productidLong=0;
		}
		int quatityInt;
		try {
			quatityInt=Integer.parseInt(quatity);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			quatityInt=0;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		df.format(date);
		BillTO billTO = new BillTO(customerLong, address, district, city, date, status, totalLong,productidLong,quatityInt);
		return billDA.addNew(billTO);

	}

	// remove bill by id
	@DeleteMapping(value = "bill/delete/{id}", produces = "application/json")
	public boolean removeBill(@PathVariable String id) {
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		return billDA.removeBill(idLong);

	}

	// update bill
	@PutMapping(value = "bill/update")
	public boolean updateBill( @RequestBody Map<String, String> body) {
		String id= body.get("id") != null ? body.get("id") : "";
		String customer_id = body.get("customerid") != null ? body.get("customerid") : "";
		String address = body.get("address") != null ? body.get("address") : "";
		String district = body.get("district") != null ? body.get("district") : "";
		String city = body.get("city") != null ? body.get("city") : "";
		String status = body.get("status") != null ? body.get("status") : "";
		String total = body.get("total") != null ? body.get("total") : "";
		long totalLong, customerLong,idLong;
		try {
			totalLong = Long.parseLong(total);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			totalLong = 0;
		}
		try {
			customerLong = Long.parseLong(customer_id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			customerLong = 0;
		}
		try {
			idLong=Long.parseLong(id);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			idLong=0;
		}
		Date date;
		String dateStr = body.get("datemodified") != null ? body.get("datemodified") : "";
		try {
			date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			date = new Date();
			df.format(date);
		}
		BillTO billTO = new BillTO(idLong,customerLong, address, district, city, date, status, totalLong);
		return billDA.updateBill(billTO);

	}
	// update detail bill by id
	@PutMapping(value = "bill/updateDetail")
	 public boolean updateDetailBill(@RequestBody Map<String,String> body) {
		String id= body.get("id") != null ? body.get("id") : "";
		String productid=body.get("productid")!=null?body.get("productid"):"";
		String quatity=body.get("quatity")!=null?body.get("quatity"):"";
		int quatityInt;
		long productidLong,idLong;
		try {
			quatityInt=Integer.parseInt(quatity);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			quatityInt=0;
		}
		try {
			productidLong=Long.parseLong(productid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			productidLong=0;
		}
		try {
			idLong=Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong=0;
		}
		BillTO billTO=new BillTO(idLong,productidLong,quatityInt);
		return billDA.updateDetailBill(billTO);
	}

}
