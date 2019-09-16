package com.storeonline.Controller;

import java.util.ArrayList;
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

import com.storeonline.DAO.CompanyDA;
import com.storeonline.DTO.CompanyTO;

@RestController
public class CompanyController {
	@Autowired
	private CompanyDA companyDA;

// add company
	@PostMapping(value = "/Company/Add", produces = { "Application/json" })
	public boolean addCompany(WebRequest request) {
		String companyName = request.getParameter("companyname") != null ? (String) request.getParameter("companyname")
				: "";
		String address = request.getParameter("address") != null ? (String) request.getParameter("address") : "";
		String phone = request.getParameter("phone") != null ? (String) request.getParameter("phone") : "";
		String fax = request.getParameter("fax") != null ? (String) request.getParameter("fax") : "";

		CompanyTO companyTO = new CompanyTO(companyName, address, phone, fax);
		return companyDA.addNew(companyTO);
	}

// remove company by id
	@DeleteMapping(value = "/Company/Delete/{id}", produces = { "Application/json" })
	public boolean deleteCompany(@PathVariable String id) {
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		return companyDA.removeCompany(idLong);
	}

// update company
	@PutMapping(value = "/Company/updateCompany", produces = { "Application/json" })
	public boolean updateCompany(@RequestBody Map<String, String> body) {
		String companyId = body.get("companyid") != null ? body.get("companyid") : "";
		String companyName = body.get("companyname") != null ? body.get("companyname") : "";
		String address = body.get("address") != null ? body.get("address") : "";
		String phone = body.get("phone") != null ? body.get("phone") : "";
		String fax = body.get("fax") != null ? body.get("fax") : "";
		long companyIdLong;
		try {
			companyIdLong = Long.parseLong(companyId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			companyIdLong = 0;
		}
		CompanyTO companyTO = new CompanyTO(companyIdLong, companyName, address, phone, fax);
		return companyDA.updateCompany(companyTO);

	}

// find all
	@GetMapping(value = "/Company", produces = { "Application/json" })
	public ArrayList<CompanyTO> findALLCompany() {
		return companyDA.findAll();
	}

// find company by id
	@GetMapping(value = "/Company/{id}", produces = { "Application/json" })
	public CompanyTO findCompanyByid(@PathVariable String id) {
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		return companyDA.retrieveCompanyById(idLong);
	}
}
