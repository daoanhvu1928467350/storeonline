package com.storeonline.Controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.storeonline.DAO.CustomerDA;
import com.storeonline.DTO.CustomerTO;

// Information account customer
// username is unique,id is primary key
@RestController
public class CustomerController {
	@Autowired
	private CustomerDA customerDA;

// list all account customer
	@RequestMapping(value = "/", produces = { "application/json" })
	public ArrayList<CustomerTO> FindAll() {
		ArrayList<CustomerTO> listcustomerto = new ArrayList<CustomerTO>();
		listcustomerto = (ArrayList<CustomerTO>) customerDA.FindAll();
		return listcustomerto;
	}

// remove customer by id
	@DeleteMapping(value = "/delete", produces = { "application/json" })
	public boolean RemoveCustomer(WebRequest request) {
		boolean checkRemove = false;
		String id = request.getParameter("id") != null ? (String) request.getParameter("id") : "0";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		checkRemove = customerDA.RemoveCustomer(idLong);
		return checkRemove;
	}

// find account customer by id
	@RequestMapping(value = "/RetrieveCustomerById", produces = { "application/json" })
	public CustomerTO RetrieveCustomerById(WebRequest request) {
		String id = request.getParameter("id") != null ? (String) request.getParameter("id") : "0";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		CustomerTO customerTO = customerDA.RetrieveCustomerById(idLong);
		return customerTO;

	}

// find acount customer by username ,check exist account customer
	@RequestMapping(value = "/RetrieveCustomerByUsername", produces = { "application/json" })
	public CustomerTO RetrieveCustomerByUsername(WebRequest request) {
		String username = request.getParameter("username") != null ? (String) request.getParameter("username") : "";
		CustomerTO customerTO = customerDA.RetrieveCustomerByUsername(username);
		return customerTO;
	}

// update username customer by id
	@PutMapping(value = "/UpdateUserNameById", produces = { "application/json" })
	public boolean UpdateUserNameCustomer(@RequestBody Map<String,String> body) {
		String username = body.get("username") != null ? (String) body.get("username") : "";
		String id = body.get("id") != null ? (String) body.get("id") : "0";
		CustomerTO customerTO = customerDA.RetrieveCustomerByUsername(username);
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		if (customerTO != null) {
			return false;
		} else {
			customerTO = customerDA.RetrieveCustomerById(idLong);
			
			if (customerTO != null) {
				customerTO.setUsername(username);
				return customerDA.UpdateCustomer(customerTO);
			} else {
				return false;
			}
		}

	}

// update address by id
	@PutMapping(value = "/UpdateAddressById", produces = { "application/json" })
	public boolean UpdateAddressCustomer(@RequestBody Map<String,String> body) {
		String address = body.get("address") != null ? (String) body.get("address") : "";
		String id = body.get("id") != null ? (String) body.get("id") : "0";

		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}

		CustomerTO customerTO = customerDA.RetrieveCustomerById(idLong);
		if (customerTO != null) {
			customerTO.setAddress(address);
			return customerDA.UpdateCustomer(customerTO);
		} else {
			return false;
		}

	}

// update status by id
	@PutMapping(value = "/UpdateStatusById", produces = { "application/json" })
	public boolean UpdateStatusCustomer(@RequestBody Map<String,String> body) {
		String status = body.get("status") != null ? (String) body.get("status") : "";
		String id = body.get("id") != null ? (String) body.get("id") : "0";

		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}

		CustomerTO customerTO = customerDA.RetrieveCustomerById(idLong);
		if (customerTO != null) {
			customerTO.setStatus(status);
			return customerDA.UpdateCustomer(customerTO);
		} else {
			return false;
		}

	}

// update balance by id
	@PutMapping(value = "/UpdateBalanceById", produces = { "application/json" })
	public boolean UpdateBalanceCustomer(@RequestBody Map<String,String> body) {
		String balance = body.get("balance") != null ? (String) body.get("balance") : "0";
		String id = body.get("id") != null ? (String) body.get("id") : "0";

		long idLong, balanceLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		try {
			balanceLong = Long.parseLong(balance);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			balanceLong = 0;
		}

		CustomerTO customerTO = customerDA.RetrieveCustomerById(idLong);
		if (customerTO != null) {
			customerTO.setAmt_balance(balanceLong);
			return customerDA.UpdateCustomer(customerTO);
		} else {
			return false;
		}

	}
	// update password
	@PutMapping(value = "/UpdatePasswordById", produces = { "application/json" })
	public boolean UpdatePasswordCustomer(@RequestBody Map<String,String> body) {
		String password = body.get("password") != null ? (String) body.get("password") : "0";
		String id = body.get("id") != null ? (String) body.get("id") : "0";

		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		
		CustomerTO customerTO = customerDA.RetrieveCustomerById(idLong);
		if (customerTO != null) {
			customerTO.setPassword(password);
			return customerDA.UpdateCustomer(customerTO);
		} else {
			return false;
		}

	}
}
