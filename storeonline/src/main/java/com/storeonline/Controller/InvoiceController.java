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

import com.storeonline.DAO.InvoiceDA;
import com.storeonline.DTO.InvoiceTO;
import com.storeonline.DTO.StaticTO;

@RestController
public class InvoiceController {
	@Autowired
	private InvoiceDA invoiceDA;
     // add invoice
	@PostMapping(value="/invoice/add",produces= {"application/json"})
	public boolean addInvoice(WebRequest request) {
		String customer_id=request.getParameter("customerid")!=null?(String)request.getParameter("customerid"):"";
		String companyId=request.getParameter("companyid")!=null?(String)request.getParameter("companyid"):"";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		df.format(date);
		String total =request.getParameter("total")!=null?(String)request.getParameter("total"):"";
		long customerIdLong,companyIdLong,totalLong;
		try {
			customerIdLong=Long.parseLong(customer_id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			customerIdLong=0;
		}
		try {
			companyIdLong=Long.parseLong(companyId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			companyIdLong=0;
		}
		try {
			totalLong=Long.parseLong(total);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			totalLong=0;
		}
		InvoiceTO invoiceTO=new InvoiceTO(customerIdLong,companyIdLong,date,totalLong);
		return invoiceDA.addNew(invoiceTO);
	}
	// reomove invoice
	@DeleteMapping(value="/invoice/delete/{id}",produces= {"application/json"})
	public boolean deleteInvoice(@PathVariable String id) {
		
	    long idLong;
	    try {
			idLong=Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong=0;
		}
		return invoiceDA.removeInvoice(idLong);
	}
	// update invoice
	@PutMapping(value="/invoice/updateInvoice",produces= {"application/json"})
	public boolean updateInvoice(@RequestBody Map<String,String> body) {
		String invoiceId=body.get("invoiceid")!=null?body.get("invoiceid"):"0";
		String customerId=body.get("customerid")!=null?body.get("customerid"):"0";
		String companyId=body.get("companyid")!=null?body.get("companyid"):"0";
		String dateStr=body.get("date")!=null?body.get("date"):"";
		String total=body.get("total")!=null?body.get("total"):"0";
		String productId=body.get("productid")!=null?body.get("productid"):"0";
		String quatityStr=body.get("quatity")!=null?body.get("quatity"):"0";
		String priceBuy=body.get("pricebuy")!=null?body.get("pricebuy"):"0";
		long invoiceIdLong,customerIdLong,companyIdLong,totalLong,pricebuyLong,productIdLong;
		int quatityInt;
		try {
			pricebuyLong=Long.parseLong(priceBuy);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			pricebuyLong=0;
		}
		try {
			productIdLong=Long.parseLong(productId);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			productIdLong=0;
		}
		try {
			quatityInt=Integer.parseInt(quatityStr);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			quatityInt=0;
		}
		try {
			invoiceIdLong=Long.parseLong(invoiceId);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			invoiceIdLong=0;
		}
		try {
			customerIdLong=Long.parseLong(customerId);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			customerIdLong=0;
		}
		try {
			companyIdLong=Long.parseLong(companyId);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			companyIdLong=0;
		}
		try {
			totalLong=Long.parseLong(total);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			totalLong=0;
		}
	
		Date date;
		
		try {
			date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			date = new Date();
			df.format(date);
		}
		InvoiceTO invoiceTO=new InvoiceTO(invoiceIdLong,customerIdLong,companyIdLong,date,totalLong,productIdLong,quatityInt,pricebuyLong);
		return invoiceDA.updateInvoice(invoiceTO);
	}
	// update detail invoice
	
	// find all
	@GetMapping(value="/invoice" ,produces= {"application/json"})
	public ArrayList<InvoiceTO> findAll(){
		return invoiceDA.findAll();
	}
	// find by id
	@GetMapping(value="/invoice/{id}",produces= {"application/json"})
	public InvoiceTO retrieveInvoiceById(@PathVariable String id) {
		long idLong;
		try {
			idLong=Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong=0;
		}
		
		return invoiceDA.retrieveInvoiceById(idLong);
	}
}
