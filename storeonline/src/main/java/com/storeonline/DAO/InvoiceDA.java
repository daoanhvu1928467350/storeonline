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

import com.storeonline.DTO.InvoiceTO;
import com.storeonline.DTO.StaticTO;

@Repository
@Transactional
public class InvoiceDA {
	@Autowired
	private JdbcTemplate jdbc;

	// add invoice
	public boolean addNew(InvoiceTO invoiceTO) {
		String sql = "insert into " + StaticTO.DB_INVOICE + " values(0,?,?,?,?) ";
		boolean check_invoice = jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++, invoiceTO.getCustomer_id());
					ps.setLong(index++, invoiceTO.getCompanyId());
					java.sql.Date sqlDate = new java.sql.Date(invoiceTO.getDateMotified().getTime());
					ps.setDate(index++, sqlDate );
					ps.setFloat(index++, invoiceTO.getTotal());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}

		});
		if (check_invoice) {
			InvoiceTO invoiceTO_2 = retrieveLastInvoice();
			if (invoiceTO.getProductId() > 0) {
				if (invoiceTO_2 != null) {
					invoiceTO.setId(invoiceTO_2.getId());
					InsertDetailInvoice(invoiceTO);
				}
			}
			return true;
		}
		return false;

	}

	// remove invoice
	public boolean removeInvoice(long id) {
		// remove detail invoice
		removeDetailInvoice(id);
		// remove invoice
		String sql = "delete from" + StaticTO.DB_INVOICE + " where ID_Invoice=?";
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

	// remove detail invoice
	public boolean removeDetailInvoice(long id) {
		String sql = "delete from" + StaticTO.DB_DETAIL_INVOICE + " where ID_Invoice=?";
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

	// update invoice
	public boolean updateInvoice(InvoiceTO invoiceTO) {
		String sql = "update " + StaticTO.DB_INVOICE+ " set customer_id=?,companyid=?,DateModified=? ,Total=? where ID_Invoice=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override

			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++, invoiceTO.getCustomer_id());
					ps.setLong(index++,invoiceTO.getCompanyId() );
					java.sql.Date sqlDate = new java.sql.Date(invoiceTO.getDateMotified().getTime());
					ps.setDate(index++,sqlDate);
                    ps.setLong(index++,invoiceTO.getTotal());
                    ps.setLong(index++,invoiceTO.getId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}
	
	// update detail invoice
	public boolean updateDetailInvoice(InvoiceTO invoiceTO) {
		String sql = "update " + StaticTO.DB_DETAIL_INVOICE+ " set ID_Product=?,Quatity=?,PriceBuy=? where ID_Invoice=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override

			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++,invoiceTO.getProductId() );
					ps.setInt(index++,invoiceTO.getQuatity() );
				
                    ps.setLong(index++,invoiceTO.getPriceBuy());
                    ps.setLong(index++,invoiceTO.getId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}
	// select invoice by id
	public InvoiceTO retrieveInvoiceById(long id) {
		String sql = "select * from " + StaticTO.DB_INVOICE + " where ID_Invoice=?";
		return jdbc.execute(sql, new PreparedStatementCallback<InvoiceTO>() {
			@Override
			public InvoiceTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				InvoiceTO invoiceTO = null;
				int index = 1;
				try {
					ps.setLong(index++, id);
					ResultSet rs = null;
					rs = ps.executeQuery();

					if (rs.next()) {
						invoiceTO = new InvoiceTO(rs.getLong("id_invoice"), rs.getLong("customer_id"),
								rs.getLong("companyid"), rs.getDate("DateModified"), rs.getLong("Total"));
					}

					return invoiceTO;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return invoiceTO;
			}
		});
	}

	// find all
	public ArrayList<InvoiceTO> findAll() {
		String sql = "select * from " + StaticTO.DB_INVOICE;
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<InvoiceTO>>() {
			@Override
			public ArrayList<InvoiceTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ResultSet rs = null;
				rs = ps.executeQuery();
				ArrayList<InvoiceTO> listInvoice = new ArrayList<InvoiceTO>();
				while (rs.next()) {
					InvoiceTO invoiceTO = new InvoiceTO(rs.getLong("id_invoice"), rs.getLong("customer_id"),
							rs.getLong("companyid"), rs.getDate("DateModified"), rs.getLong("Total"));
					listInvoice.add(invoiceTO);
				}
				return listInvoice;
			}
		});
	}

	// insert detail invoice
	public boolean InsertDetailInvoice(InvoiceTO invoiceTO) {
		String sql = "insert into " + StaticTO.DB_DETAIL_INVOICE + " values(?,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override

			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++, invoiceTO.getId());
					ps.setLong(index++, invoiceTO.getProductId());
					ps.setInt(index++, invoiceTO.getQuatity());
					ps.setFloat(index++, invoiceTO.getPriceBuy());

					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

	// select last invoice in db
	public InvoiceTO retrieveLastInvoice() {
		String sql = "select * from " + StaticTO.DB_INVOICE + " order by id_invoice desc limit 1";
		return jdbc.execute(sql, new PreparedStatementCallback<InvoiceTO>() {
			@Override
			public InvoiceTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				rs = ps.executeQuery();
				InvoiceTO invoiceTO = null;
				if (rs.next()) {
					invoiceTO = new InvoiceTO(rs.getLong("id_invoice"), rs.getLong("customer_id"),
							rs.getLong("companyid"), rs.getDate("DateModified"), rs.getLong("Total"));

				}
				return invoiceTO;
			}
		});
	}


}
