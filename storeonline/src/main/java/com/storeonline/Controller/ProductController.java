package com.storeonline.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.storeonline.DTO.ProductTO;
import com.storeonline.DAO.ProductDA;

@RestController
public class ProductController {
// list product
	@Autowired
	private ProductDA productDA;

	@GetMapping(value = "/product", produces = { "application/json" })
	public ArrayList<ProductTO> retrieveAllProduct() {
		return productDA.findAllProduct();
	}

// add product
	@PostMapping(value = "/product/add", produces = { MediaType.APPLICATION_JSON_VALUE })
	public boolean AddProduct(WebRequest request) {
		String productName = request.getParameter("productname") != null ? (String) request.getParameter("productname")
				: "";
		String productquantity = request.getParameter("productquantity") != null
				? (String) request.getParameter("productquantity")
				: "0";
		String productprice = request.getParameter("productprice") != null
				? (String) request.getParameter("productprice")
				: "0";
		String productimg = request.getParameter("productimg") != null ? (String) request.getParameter("productimg")
				: "";
		String productdescriptionString = request.getParameter("productdescription") != null
				? (String) request.getParameter("productdescription")
				: "";
		String category = request.getParameter("productcategory") != null
				? (String) request.getParameter("productcategory")
				: "0";
		int quantityInt, categoryInt;
		long priceLong;
		try {
			priceLong = Long.parseLong(productprice);
		} catch (NumberFormatException e) {
			priceLong = 0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			quantityInt = Integer.parseInt(productquantity);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			quantityInt = 0;

		}
		try {
			categoryInt = Integer.parseInt(category);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			categoryInt = 0;
		}
		ProductTO productTO = new ProductTO(productName, quantityInt, priceLong, productimg, productdescriptionString,
				categoryInt);
		return productDA.addProduct(productTO);
	}

//update product name by id
	@PutMapping(value = "/UpdateProductNameById/{id}", produces = { "application/json" })
	public boolean UpdateProductName(@PathVariable String id,@RequestBody Map<String,String>  newProductName) {
//		String newProductName = request.getParameter("productname") != null
//				? (String) request.getParameter("productname")
//				: "";
//		String id = request.getParameter("productid") != null ? (String) request.getParameter("productid") : "";
		
		String productNameStr=newProductName.get("productname")!=null?newProductName.get("productname"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		ProductTO productTO = productDA.retrieveProductById(idLong);
		if (productTO != null) {
			productTO.setProductName(productNameStr);
			return productDA.updateProduct(productTO);
		}
		return false;
	}

//update product price by id
	@PutMapping(value = "/UpdateProductPriceById/{id}", produces = { "application/json" })
	public boolean UpdateProductPrice(@PathVariable String id,@RequestBody Map<String,String> newProductPrice) {
//		String newProductPrice = request.getParameter("productprice") != null
//				? (String) request.getParameter("productprice")
//				: "";
//		String id = request.getParameter("productid") != null ? (String) request.getParameter("productid") : "";
		String productPriceStr=newProductPrice.get("productprice")!=null?newProductPrice.get("productprice"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		long newProductPriceInt;
		try {
			newProductPriceInt = Long.parseLong(productPriceStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			newProductPriceInt = 0;
		}
		ProductTO productTO = productDA.retrieveProductById(idLong);
		if (productTO != null) {
			productTO.setProductPrice(newProductPriceInt);
			return productDA.updateProduct(productTO);
		}
		return false;
	}

	// update product quantity by id
	@PutMapping(value = "/UpdateProductQuantityById/{id}", produces = { "application/json" })
	public boolean UpdateProductQuantity(@PathVariable String id,@RequestBody Map<String,String> productquantity) {
//		String newProductQuanTity = request.getParameter("productquantity") != null
//				? (String) request.getParameter("productquantity")
//				: "";
//		String id = request.getParameter("productid") != null ? (String) request.getParameter("productid") : "";
		String productQuantityStr=productquantity.get("productquantity")!=null?productquantity.get("productquantity"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		int newProductQuanTityInt;
		try {
			newProductQuanTityInt = Integer.parseInt(productQuantityStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			newProductQuanTityInt = 0;
		}
		ProductTO productTO = productDA.retrieveProductById(idLong);
		if (productTO != null) {
			productTO.setProductQuanity(newProductQuanTityInt);
			return productDA.updateProduct(productTO);
		}
		return false;
	}

	// update product img by id
	@PutMapping(value = "/UpdateProductImgById/{id}", produces = { "application/json" })
	public boolean UpdateProductImg(@PathVariable String id,@RequestBody Map<String,String> newProductImg) {
//		String newProductImg = request.getParameter("productimg") != null ? (String) request.getParameter("productimg")
//				: "";
//		String id = request.getParameter("productid") != null ? (String) request.getParameter("productid") : "";
		String imgStr=newProductImg.get("productimg")!=null?newProductImg.get("productimg"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		ProductTO productTO = productDA.retrieveProductById(idLong);
		if (productTO != null) {
			productTO.setProductImg(imgStr);
			return productDA.updateProduct(productTO);
		}
		return false;
	}

	// update product description by id
	@PutMapping(value = "/UpdateProductDescById/{id}", produces = { "application/json" })
	public boolean updateDescProduct(@PathVariable String id,@RequestBody Map<String,String> description) {
//		String id = request.getParameter("id") != null ? (String) request.getParameter("id") : "0";
//		String desc = request.getParameter("desc") != null ? (String) request.getParameter("desc") : "";
		String desc= description.get("desc")!=null?description.get("desc"):"";
		long idLong;
		try {
			idLong = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}
		ProductTO productTO = productDA.retrieveProductById(idLong);
		if (productTO != null) {
			productTO.setProductDescription(desc);
			return productDA.updateProduct(productTO);
		}
		return false;

	}

	// delete product by id
	@DeleteMapping(value = "/deleteProduct/{id}")
	public boolean deleteProduct(@PathVariable String id) {
		long idLong;
		System.out.println("sdfgsdf" + id);
		try {
			idLong = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			idLong = 0;
		}

		return productDA.deleteProduct(idLong);
	}
}
