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

import com.storeonline.DTO.CompanyTO;
import com.storeonline.DTO.StaticTO;

@Transactional
@Repository
public class CompanyDA {
	@Autowired
	private JdbcTemplate jdbc;

// add company 
	public boolean addNew(CompanyTO companyTO) {
		String sql = "insert into " + StaticTO.DB_COMPANY + " values(0,?,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setString(index++, companyTO.getCompanyName());
					ps.setString(index++, companyTO.getAddress());
					ps.setString(index++, companyTO.getPhone());
					ps.setString(index++, companyTO.getFax());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

// remove company by id
	public boolean removeCompany(long id) {
		String sql = "delete from " + StaticTO.DB_COMPANY + " where CompanyId=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++, id);

					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

// update company
	public boolean updateCompany(CompanyTO companyTO) {
		String sql = "update " + StaticTO.DB_COMPANY
				+ " set CompanyName=?,Address=?,PhoneNumber=?,FaxNumber=? where CompanyId=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setString(index++, companyTO.getCompanyName());
					ps.setString(index++, companyTO.getAddress());
					ps.setString(index++, companyTO.getPhone());
					ps.setString(index++, companyTO.getFax());
					ps.setLong(index++, companyTO.getCompanyId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

// find all
	public ArrayList<CompanyTO> findAll() {
		String sql = "select * from " + StaticTO.DB_COMPANY;
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<CompanyTO>>() {
			@Override
			public ArrayList<CompanyTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ResultSet rs = null;
				ArrayList<CompanyTO> listCompany = new ArrayList<CompanyTO>();
				rs = ps.executeQuery();
				while (rs.next()) {
					CompanyTO companyTO = new CompanyTO(rs.getLong("CompanyId"), rs.getString("CompanyName"),
							rs.getString("Address"), rs.getString("PhoneNumber"), rs.getString("FaxNumber"));
					listCompany.add(companyTO);
				}
				return listCompany;
			}
		});
	}

// find by id
	public CompanyTO retrieveCompanyById(long id) {
		String sql = "select * from " + StaticTO.DB_COMPANY + " where CompanyId=?";
		return jdbc.execute(sql, new PreparedStatementCallback<CompanyTO>() {
			@Override
			public CompanyTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				CompanyTO companyTO = null;
				ps.setLong(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					companyTO = new CompanyTO(rs.getLong("CompanyId"), rs.getString("CompanyName"),
							rs.getString("Address"), rs.getString("PhoneNumber"), rs.getString("FaxNumber"));

				}
				return companyTO;
			}
		});
	}
}
