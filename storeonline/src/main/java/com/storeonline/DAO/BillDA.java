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

import com.storeonline.DTO.BillTO;
import com.storeonline.DTO.StaticTO;

@Repository
@Transactional
public class BillDA {
	@Autowired
	private JdbcTemplate jdbc;

	// add bill
	public boolean addNew(BillTO bill) {
		String sql = "insert into " + StaticTO.DB_BILL + " values(0,?,?,?,?,?,?,?)";
		String sqlForBillDetail = "insert into " + StaticTO.DB_DETAIL_BILL + " values (?,?,?)";
		// add bill
		boolean checkAddBill = jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				ps.setLong(index++, bill.getCustomer_id());
				ps.setString(index++, bill.getAddress());
				ps.setString(index++, bill.getDistrict());
				ps.setString(index++, bill.getCity());
				java.sql.Date sqlDate = new java.sql.Date(bill.getDateMotified().getTime());
				ps.setDate(index++, sqlDate);
				ps.setString(index++, bill.getStatus());
				ps.setLong(index++, bill.getTotal());
				return (ps.executeUpdate() > 0);
			}
		});
		BillTO billTO = retrieveLastBill();
		if (checkAddBill) {
			if (bill.getProductId() > 0) {
				return jdbc.execute(sqlForBillDetail, new PreparedStatementCallback<Boolean>() {
					@Override
					public Boolean doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						int index = 1;
						ps.setLong(index++, billTO.getId());
						ps.setLong(index++, bill.getProductId());
						ps.setInt(index++, bill.getQuatity());
						return (ps.executeUpdate() > 0);
					}
				});
			} else {
				return true;
			}
		}
		return false;
	}

	// remove bill
	public boolean removeBill(long id) {
		String sql = "update " + StaticTO.DB_BILL + " set status='" + StaticTO.Pending + "' where id_bill=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setLong(1, id);
				return (ps.executeUpdate() > 0);
			}
		});

	}

	// update bill
	public boolean updateBill(BillTO billTO) {
		boolean check_bill = false;
		String sql = "update " + StaticTO.DB_BILL
				+ " set customer_id=?,address=?,district=?,city=?,DateModified=?,status=?,total=? where id_bill=?";
		String sqlForBillDetail = "update " + StaticTO.DB_DETAIL_BILL + " set id_product=?,quatity=? where id_bill=?";
		check_bill = jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;

				try {
					ps.setLong(index++, billTO.getCustomer_id());
					ps.setString(index++, billTO.getAddress());
					ps.setString(index++, billTO.getDistrict());
					ps.setString(index++, billTO.getCity());
					java.sql.Date sqlDate = new java.sql.Date(billTO.getDateMotified().getTime());
					ps.setDate(index++,sqlDate);
					ps.setString(index++, billTO.getStatus());
					ps.setLong(index++, billTO.getTotal());
                    ps.setLong(index++,billTO.getId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;

			}
		});
		if (check_bill) {
			if (billTO.getProductId() > 0) {
				jdbc.execute(sqlForBillDetail, new PreparedStatementCallback<Boolean>() {
					@Override
					public Boolean doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						int index = 1;

						try {
							ps.setLong(index++, billTO.getProductId());
							ps.setInt(index++, billTO.getQuatity());
							ps.setLong(index++, billTO.getId());
							return (ps.executeUpdate() > 0);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;

					}
				});
			}
		}
		return check_bill;
	}

	// select element last bill
	public BillTO retrieveLastBill() {
		String sql = "select * from " + StaticTO.DB_BILL + " order by id_bill desc";
		return jdbc.execute(sql, new PreparedStatementCallback<BillTO>() {
			@Override
			public BillTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				BillTO billTO = null;
				try {
					rs = ps.executeQuery();

					if (rs.next()) {
						billTO = new BillTO(rs.getLong("id_bill"), rs.getLong("customer_id"), rs.getString("address"),
								rs.getString("district"), rs.getString("city"), rs.getDate("DateModified"),
								rs.getString("status"), rs.getLong("total"));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return billTO;
			}
		});
	}

	// find all
	public ArrayList<BillTO> findAll() {

		String sql = "select * from " + StaticTO.DB_BILL;
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<BillTO>>() {
			@Override
			public ArrayList<BillTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ArrayList<BillTO> listBill = new ArrayList<BillTO>();
				BillTO billDetailTO = null;
				ResultSet rs = null;
				rs = ps.executeQuery();
				while (rs.next()) {
					BillTO billTO = new BillTO(rs.getLong("id_bill"), rs.getLong("customer_id"),
							rs.getString("address"), rs.getString("district"), rs.getString("city"),
							rs.getDate("DateModified"), rs.getString("status"), rs.getLong("total"));
					listBill.add(billTO);
					billDetailTO = retrieveBillDetail(rs.getLong("id_bill"));
					if (billDetailTO != null) {
						billTO.setProductId(billDetailTO.getProductId());
						billTO.setQuatity(billDetailTO.getQuatity());
					}
				}
				return listBill;
			}
		});
	}

	// find bill by id
	public BillTO retrieveBillById(long id) {
		String sql = "select * from " + StaticTO.DB_BILL + " where id_bill=? ";
		return jdbc.execute(sql, new PreparedStatementCallback<BillTO>() {
			@Override
			public BillTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				BillTO billTO = null;
				BillTO billDetailTO = null;
				try {
					ps.setLong(1, id);
					rs = ps.executeQuery();

					if (rs.next()) {
						billTO = new BillTO(rs.getLong("id_bill"), rs.getLong("customer_id"), rs.getString("address"),
								rs.getString("district"), rs.getString("city"), rs.getDate("DateModified"),
								rs.getString("status"), rs.getLong("total"));
						billDetailTO = retrieveBillDetail(rs.getLong("id_bill"));
						if (billDetailTO != null) {
							billTO.setProductId(billDetailTO.getProductId());
							billTO.setQuatity(billDetailTO.getQuatity());
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return billTO;
			}
		});
	}

	// find detail bill by id
	public BillTO retrieveBillDetail(long id) {
		String sql = "select * from " + StaticTO.DB_DETAIL_BILL + " where id_bill=? ";
		return jdbc.execute(sql, new PreparedStatementCallback<BillTO>() {
			@Override
			public BillTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				BillTO billTO = null;
				try {
					ps.setLong(1, id);
					rs = ps.executeQuery();

					if (rs.next()) {
						billTO = new BillTO(rs.getLong("id_product"), rs.getInt("quatity"));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return billTO;
			}
		});
	}

	// update detail bill by id
	public boolean updateDetailBill(BillTO billDetail) {
		String sql = "update " + StaticTO.DB_DETAIL_BILL + " set id_product=?,quatity=? where id_bill=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++, billDetail.getProductId());
					ps.setInt(index++, billDetail.getQuatity());
					ps.setLong(index++, billDetail.getId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

}
