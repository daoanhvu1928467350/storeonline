package com.storeonline.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.storeonline.DTO.RatingTO;
import com.storeonline.DTO.StaticTO;

@Transactional
@Repository
public class RatingDA {
	@Autowired
	private JdbcTemplate jdbc;

// add rating
	public boolean addNew(RatingTO ratingTO) {
		String sql = "insert into " + StaticTO.DB_RATING + " values(?,?,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {
					ps.setLong(index++, ratingTO.getRatingId());
					ps.setString(index++, ratingTO.getTitle());
					ps.setLong(index++, ratingTO.getCustomer_id());
					ps.setInt(index++, ratingTO.getRating());
					ps.setString(index++, ratingTO.getContent());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

// update rating 
	public boolean updateRating(RatingTO ratingTO) {
		String sql = "update " + StaticTO.DB_RATING
				+ " set Title=?,customer_id=?,Rating=?,Content=? where id_product=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				try {

					ps.setString(index++, ratingTO.getTitle());
					ps.setLong(index++, ratingTO.getCustomer_id());
					ps.setInt(index++, ratingTO.getRating());
					ps.setString(index++, ratingTO.getContent());
					ps.setLong(index++, ratingTO.getRatingId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
	}

// remove rating
	public boolean removeRating(long id) {
		String sql = "delete from " + StaticTO.DB_RATING + " where id_product=?";
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

// find rating by product id
	public RatingTO retrieveRatingByProductId(long id) {
		String sql = "select * from " + StaticTO.DB_RATING + " where id_product=?";
		return jdbc.execute(sql, new PreparedStatementCallback<RatingTO>() {
			@Override
			public RatingTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				RatingTO ratingTO = null;
				ps.setLong(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					ratingTO = new RatingTO(rs.getLong("id_product"), rs.getString("Title"), rs.getLong("customer_id"),
							rs.getInt("Rating"), rs.getString("Content"));

				}
				return ratingTO;
			}
		});
	}

}
