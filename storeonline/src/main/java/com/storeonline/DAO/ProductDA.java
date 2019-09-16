package com.storeonline.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.storeonline.DTO.StaticTO;
import com.storeonline.DTO.ProductTO;

@Transactional
@Repository
public class ProductDA {
	@Autowired
	private JdbcTemplate jdbc;

// find all product
	public ArrayList<ProductTO> findAllProduct() {
		String sql = "select * from " + StaticTO.DB_PRODUCT;
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<ProductTO>>() {
			@Override
			public ArrayList<ProductTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ResultSet rs = null;
				ArrayList<ProductTO> listProduct = new ArrayList<ProductTO>();
				try {
					rs = ps.executeQuery();

					while (rs.next()) {
						ProductTO productTO = new ProductTO(rs.getLong("product_id"), rs.getString("product_name"),
								rs.getInt("product_quantity"), rs.getLong("product_price"),
								rs.getString("product_mainimg"), rs.getString("product_description"));
						productTO.setProductCategory(rs.getInt("product_category_id"));
						listProduct.add(productTO);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return listProduct;
			}
		});
	}

// add product 
	public boolean addProduct(ProductTO productTO) {
		String sql = "insert into " + StaticTO.DB_PRODUCT + " values(0,?,?,?,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int i = 1;

				ps.setString(i++, productTO.getProductName());
				ps.setInt(i++, productTO.getProductQuanity());
				ps.setLong(i++, productTO.getProductPrice());
				ps.setString(i++, productTO.getProductImg());
				ps.setString(i++, productTO.getProductDescription());
				ps.setInt(i++, productTO.getProductCategory());
				return (ps.executeUpdate() > 0);
			}
		});
	}

// update product
	public boolean updateProduct(ProductTO productTO) {
		String sql = "update  " + StaticTO.DB_PRODUCT
				+ " set product_name=?,product_quantity=?,product_price=?,product_mainimg=?,product_description=?,product_category_id=? where product_id=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int i = 1;

				ps.setString(i++, productTO.getProductName());
				ps.setInt(i++, productTO.getProductQuanity());
				ps.setLong(i++, productTO.getProductPrice());
				ps.setString(i++, productTO.getProductImg());
				ps.setString(i++, productTO.getProductDescription());
				ps.setInt(i++, productTO.getProductCategory());
				ps.setLong(i++, productTO.getProductId());
				return (ps.executeUpdate() > 0);
			}
		});
	}

// delete product by id
	public boolean deleteProduct(long productId) {
		String sql = "delete from " + StaticTO.DB_PRODUCT + " where product_id=? ";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int i = 1;
				ps.setLong(i++, productId);

				return (ps.executeUpdate() > 0);
			}
		});
	}

	// retrieve product by id
	public ProductTO retrieveProductById(long id) {
		String sql = "select * from " + StaticTO.DB_PRODUCT + " where product_id=?";
		return jdbc.execute(sql, new PreparedStatementCallback<ProductTO>() {
			@Override
			public ProductTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int i = 1;
				ProductTO productTO = null;
				ResultSet rs = null;
				ps.setLong(i++, id);
				try {
					rs = ps.executeQuery();
					if (rs.next()) {
						productTO = new ProductTO(rs.getLong("product_id"), rs.getString("product_name"),
								rs.getInt("product_quantity"), rs.getLong("product_price"),
								rs.getString("product_mainimg"), rs.getString("product_description"));
						productTO.setProductCategory(rs.getInt("product_category_id"));

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return productTO;
			}
		});
	}
}
