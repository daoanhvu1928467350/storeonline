package com.storeonline.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.storeonline.DTO.CustomerTO;
import com.storeonline.DTO.StaticTO;
import com.storeonline.mapper.CustomerMapper;

@Repository
@Transactional
public class CustomerDA {
	@Autowired
	private JdbcTemplate jdbc;

	// list all customer query
	public List<CustomerTO> FindAll() {
		List<CustomerTO> listcustomer = new ArrayList<CustomerTO>();
		String sql = "select * from " + StaticTO.DB_CUSTOMER;
		listcustomer = jdbc.query(sql, new CustomerMapper());

		return listcustomer;
	}

	// remove customer by id
	public boolean RemoveCustomer(long id) {
		boolean checkRemove = false;
		String sql = "update " + StaticTO.DB_CUSTOMER + " set status='" + StaticTO.Pending + "' where id=?";
		checkRemove = jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int check = 0;
				try {
					ps.setLong(1, id);
					check = ps.executeUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
//					System.out.println("remove customer sql"+check);
//				
				}
				return (check > 0);
			}
		});
		return checkRemove;
	}

	// retrieve customer by id
	public CustomerTO RetrieveCustomerById(long id) {
		String sql = "select * from " + StaticTO.DB_CUSTOMER + " where id=? limit 1";
		CustomerTO customerTO;
		customerTO = jdbc.execute(sql, new PreparedStatementCallback<CustomerTO>() {
			@Override
			public CustomerTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				try {
					ps.setLong(1, id);
					rs = ps.executeQuery();
					rs.next();
					return new CustomerTO(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
							rs.getString("address"), rs.getString("status"), rs.getLong("amt_balance"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;

				}

			}

		});
		return customerTO;
	}

	// retrieve account customer by username
	public CustomerTO RetrieveCustomerByUsername(String username) {
		String sql = "select * from " + StaticTO.DB_CUSTOMER + "where username=?";
		return jdbc.execute(sql, new PreparedStatementCallback<CustomerTO>() {
			@Override
			public CustomerTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				try {
					ps.setString(1, username);
					rs = ps.executeQuery();
					rs.next();
					return new CustomerTO(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
							rs.getString("address"), rs.getString("status"), rs.getLong("amt_balance"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} finally {

				}

			}
		});
	}

	// update account customer
	public boolean UpdateCustomer(CustomerTO customerTO) {
		String sql = "update " + StaticTO.DB_CUSTOMER
				+ " set username=?,password=?,address=?,status=?,amt_balance=? where id=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int i = 1;
				ps.setString(i++, customerTO.getUsername());
				ps.setString(i++, customerTO.getPassword());
				ps.setString(i++, customerTO.getAddress());
				ps.setString(i++, customerTO.getStatus());
				ps.setLong(i++, customerTO.getAmt_balance());
				ps.setLong(i++, customerTO.getId());
				return (ps.executeUpdate() > 0);
			}
		});
	}
}
