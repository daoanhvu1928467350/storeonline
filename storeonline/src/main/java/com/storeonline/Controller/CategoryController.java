package com.storeonline.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.storeonline.DAO.CategoryDA;
import com.storeonline.DTO.CategoryTO;
import com.storeonline.DTO.StaticTO;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryDA categoryDA;

	// list category
	@RequestMapping(value = "/category", produces = { "application/json" })
	public ArrayList<CategoryTO> FindAllCategories() {

		return categoryDA.FindAllCategory();
	}

	// add category
	@PostMapping(value = "/category/add", produces = { "application/json" })
	public boolean AddCategory(WebRequest request) {

		String categoryName = request.getParameter("categoryname") != null
				? (String) request.getParameter("categoryname")
				: "";
		String categoryType = request.getParameter("categorytype") != null
				? (String) request.getParameter("categorytype")
				: "";
		int categoryTypeInt;
		try {
			categoryTypeInt = Integer.parseInt(categoryType);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block

			categoryTypeInt = 0;
		}
		return categoryDA.AddCategory(new CategoryTO(0, categoryName, categoryTypeInt, StaticTO.Active));
	}

	// Remove Category by id
	@DeleteMapping(value = "/category/DeleteById", produces = { "application/json" })
	public boolean RemoveCategoryById(WebRequest request) {
		String id = request.getParameter("categoryid") != null ? (String) request.getParameter("categoryid") : "0";
		int idInt;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idInt = 0;
		}
		return categoryDA.RemoveCategoryById(idInt);
	}

	// Remove Category by name
	@DeleteMapping(value = "/category/DeleteByName", produces = { "application/json" })
	public boolean RemoveCategoryByName(WebRequest request) {
		String categoryName = request.getParameter("categoryname") != null
				? (String) request.getParameter("categoryname")
				: "";
		return categoryDA.RemoveCategoryByName(categoryName);
	}

	// Remove Category by type
	@DeleteMapping(value = "/category/DeleteByType", produces = { "application/json" })
	public boolean RemoveCategoryByType(WebRequest request) {
		String categoryType = request.getParameter("categorytype") != null
				? (String) request.getParameter("categorytype")
				: "";
		return categoryDA.RemoveCategoryByType(categoryType);
	}

	// update change name
	@PutMapping(value = "/category/UpdateName", produces = { "application/json" })
	public boolean UpdateCategoryNameById(@RequestBody Map<String,String> body) {
		String name = body.get("categoryname") != null ? (String) body.get("categoryname") : "";
		String id = body.get("categoryid") != null ? (String) body.get("categoryid") : "0";
		int idInt = 0;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idInt = 0;
		}
		CategoryTO categoryTO = categoryDA.RetrieveCategoryById(idInt);
		categoryTO.setName(name);
		return categoryDA.UpdateCategory(categoryTO);
	}

	// update change type
	@PutMapping(value = "/category/UpdateType", produces = { "application/json" })
	public boolean UpdateCategoryTypeById(@RequestBody Map<String,String> body) {
		String type = body.get("categorytype") != null ? (String) body.get("categorytype") : "";
		String id = body.get("categoryid") != null ? (String) body.get("categoryid") : "0";
		int idInt = 0,typeInt=0;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idInt = 0;
		}
		typeInt=Integer.parseInt(type);
		CategoryTO categoryTO = categoryDA.RetrieveCategoryById(idInt);
		categoryTO.setType(typeInt);
		return categoryDA.UpdateCategory(categoryTO);
	}
	// update change status 
	@PutMapping(value = "/category/UpdateStatus", produces = { "application/json" })
	public boolean UpdateCategoryStatusById(@RequestBody Map<String,String> body) {
		String status = body.get("categorystatus") != null ? (String) body.get("categorystatus") : "";
		String id = body.get("categoryid") != null ? (String) body.get("categoryid") : "0";
		int idInt = 0;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idInt = 0;
		}
		CategoryTO categoryTO = categoryDA.RetrieveCategoryById(idInt);
		categoryTO.setStatus(status);
		return categoryDA.UpdateCategory(categoryTO);
	}
	// Retrieve by category_id
	@RequestMapping(value = "/category/retrieveById", produces = { "application/json" })
	public CategoryTO RetrieveCategoryById(WebRequest request) {
		
		String id = request.getParameter("categoryid") != null ? (String) request.getParameter("categoryid") : "0";
		int idInt = 0;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idInt = 0;
		}
		CategoryTO categoryTO = categoryDA.RetrieveCategoryById(idInt);
	
		return categoryTO;
	}
}
