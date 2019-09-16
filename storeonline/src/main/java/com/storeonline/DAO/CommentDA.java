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

import com.storeonline.DTO.CommentTO;
import com.storeonline.DTO.StaticTO;

@Transactional
@Repository
public class CommentDA {
	@Autowired
	private JdbcTemplate jdbc;

// add comment
	public boolean addNew(CommentTO commentTO) {
		String sql = "insert into " + StaticTO.DB_COMMENT + " values(0,?,?,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				ps.setLong(index++, commentTO.getProductId());
				ps.setString(index++, commentTO.getTitle());
				ps.setLong(index++, commentTO.getCustomerId());
				ps.setString(index++, commentTO.getContent());
				java.sql.Date sqlDate = new java.sql.Date(commentTO.getDate().getTime());
				ps.setDate(index++,sqlDate );
				return (ps.executeUpdate() > 0);

			}
		});
	}
// add reponse comment
	public boolean addResponseComment(CommentTO commentTO) {
		String sql = "insert into " + StaticTO.DB_REPONSE_COMMENT + " values(?,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				ps.setLong(index++, commentTO.getProductId());
			
				ps.setLong(index++, commentTO.getCustomerId());
				ps.setString(index++, commentTO.getContent());
				java.sql.Date sqlDate = new java.sql.Date(commentTO.getDate().getTime());
				ps.setDate(index++,sqlDate);
				return (ps.executeUpdate() > 0);

			}
		});
	}
// remove comment 
	public boolean RemoveComment(long commentId) {
		RemoveReponseComment(commentId);
		String sql = "delete from " + StaticTO.DB_COMMENT + " where ID_Comment=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				ps.setLong(index++, commentId);

				return (ps.executeUpdate() > 0);

			}
		});
	}
// remove reponse comment 
	public boolean RemoveReponseComment(long commentId) {
		String sql = "delete from " + StaticTO.DB_REPONSE_COMMENT + " where ID_Comment=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				ps.setLong(index++, commentId);

				return (ps.executeUpdate() > 0);

			}
		});
	}
// update comment
	public boolean UpdateComment(CommentTO commentTO) {
		String sql = "update " + StaticTO.DB_COMMENT + " set ID_Product=?,Title=?,customer_id=?,Content=?,DateModified=? where ID_Comment=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				ps.setLong(index++, commentTO.getProductId());
				ps.setString(index++, commentTO.getTitle());
				ps.setLong(index++, commentTO.getCustomerId());
				ps.setString(index++, commentTO.getContent());
				java.sql.Date sqlDate = new java.sql.Date(commentTO.getDate().getTime());
				ps.setDate(index++, sqlDate);
				ps.setLong(index++, commentTO.getId());
				return (ps.executeUpdate() > 0);

			}
		});
	}
// update reponse comment
	public boolean UpdateReponseComment(CommentTO commentTO) {
		String sql = "update " + StaticTO.DB_REPONSE_COMMENT + " set customer_id=?,Content=?,DateModified=? where ID_Comment=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				int index = 1;
				
			
				ps.setLong(index++, commentTO.getCustomerId());
				ps.setString(index++, commentTO.getContent());
				java.sql.Date sqlDate = new java.sql.Date(commentTO.getDate().getTime());
				ps.setDate(index++,sqlDate );
				ps.setLong(index++, commentTO.getId());
				return (ps.executeUpdate() > 0);

			}
		});
	}
// find all
	public ArrayList<CommentTO> findAll() {
		String sql = "select * from " + StaticTO.DB_COMMENT;
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<CommentTO>>() {
			@Override
			public ArrayList<CommentTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ArrayList<CommentTO> listComment = new ArrayList<CommentTO>();
				ResultSet rs = null;
				rs = ps.executeQuery();
				while (rs.next()) {
					CommentTO commentTO = new CommentTO(rs.getLong("ID_Comment"), rs.getLong("ID_Product"),
							rs.getString("Title"), rs.getLong("customer_id"), rs.getString("Content"),
							rs.getDate("DateModified"));
					listComment.add(commentTO);
				}
				return listComment;
			}
		});
	}

// find all by product id
	public ArrayList<CommentTO> retrieveByProductId(long id) {
		String sql = "select * from  " + StaticTO.DB_COMMENT + " where ID_Product=?";
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<CommentTO>>() {
			@Override
			public ArrayList<CommentTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ArrayList<CommentTO> listComment = new ArrayList<CommentTO>();
				try {
					ps.setLong(1, id);
					ResultSet rs = null;
					rs = ps.executeQuery();
					while (rs.next()) {
						CommentTO commentTO = new CommentTO(rs.getLong("ID_Comment"), rs.getLong("ID_Product"),
								rs.getString("Title"), rs.getLong("customer_id"), rs.getString("Content"),
								rs.getDate("DateModified"));
						listComment.add(commentTO);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return listComment;
			}
		});
	}

// find comment by id
	public CommentTO retrieveCommentById(long id) {
		String sql = "select * from " + StaticTO.DB_COMMENT + " where ID_Comment=?";
		return jdbc.execute(sql, new PreparedStatementCallback<CommentTO>() {
			@Override
			public CommentTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				CommentTO commentTO = null;
				ps.setLong(1, id);
				ResultSet rs = null;
				rs = ps.executeQuery();
				while (rs.next()) {
					commentTO = new CommentTO(rs.getLong("ID_Comment"), rs.getLong("ID_Product"), rs.getString("Title"),
							rs.getLong("customer_id"), rs.getString("Content"), rs.getDate("DateModified"));

				}
				return commentTO;

			}
		});
	}

}
