package com.storeonline.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.storeonline.DTO.CustomerTO;
public class CustomerMapper implements RowMapper<CustomerTO>{
   public CustomerTO mapRow(ResultSet rs,int numrow)throws SQLException {
	   CustomerTO customer = new CustomerTO();
	   customer.setId(rs.getLong("id"));
	   customer.setUsername(rs.getString("username"));
	   customer.setPassword(rs.getString("password"));
	   customer.setAddress(rs.getString("address"));
	   customer.setStatus(rs.getString("status"));
	   customer.setAmt_balance(rs.getLong("amt_balance"));
	   
	    return customer;
   }
}
