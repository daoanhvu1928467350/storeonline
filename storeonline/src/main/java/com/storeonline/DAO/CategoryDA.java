package com.storeonline.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.storeonline.DTO.CategoryTO;
import com.storeonline.DTO.StaticTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// infomation category 
// category_id is primary key,category_name unique
@Transactional
@Repository
public class CategoryDA {
	@Autowired
	private JdbcTemplate jdbc;

	// list category
	public ArrayList<CategoryTO> FindAllCategory() {
		ArrayList<CategoryTO> listcategory = new ArrayList<CategoryTO>();
		String sql = "select * from category where category_status='"+StaticTO.Active+"'";
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<CategoryTO>>() {
			@Override
			public ArrayList<CategoryTO> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ResultSet rs = null;
				CategoryTO categoryTO;
				ArrayList<CategoryTO> listcategory = new ArrayList<CategoryTO>();
				try {
					rs = ps.executeQuery();
					while (rs.next()) {
						categoryTO = new CategoryTO(rs.getInt("category_id"), rs.getString("category_name"),
								rs.getInt("category_type"),rs.getString("category_status"));
						listcategory.add(categoryTO);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return listcategory;
			}
		});

	}

	// Add category
	public boolean AddCategory(CategoryTO categoryTO) {
		String sql = "insert into " + StaticTO.DB_CATEGORY + " values(0,?,?,?)";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, categoryTO.getName());
					ps.setInt(2, categoryTO.getType());
					ps.setString(3, categoryTO.getStatus());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

			}
		});
	}

	// Remove category by id
	public boolean RemoveCategoryById(int id) {
		String sql = "update " + StaticTO.DB_CATEGORY + " set category_status='" + StaticTO.Remove + "' where category_id=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setInt(1, id);
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return false;
				}
			}
		});
	}

	// Remove category by name
	public boolean RemoveCategoryByName(String categoryName) {
		String sql = "update  " + StaticTO.DB_CATEGORY + " set category_status='" + StaticTO.Remove + "' where category_name=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, categoryName);
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return false;
				}
			}
		});
	}

	// Remove catgory by type
	public boolean RemoveCategoryByType(String categoryType) {
		String sql = "update " + StaticTO.DB_CATEGORY + "set category_status='" + StaticTO.Remove + "' where category_type=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				try {
					ps.setString(1, categoryType);
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return false;
				}
			}
		});
	}

	// Update categoryName by type
	public boolean UpdateCategory(CategoryTO categoryTO) {
		String sql = "update " + StaticTO.DB_CATEGORY
				+ " set category_name=?,category_type=?,category_status=? where category_id=?";
		return jdbc.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				try {
					int i = 1;
					ps.setString(i++, categoryTO.getName());
					ps.setInt(i++, categoryTO.getType());
					ps.setString(i++, categoryTO.getStatus());
					ps.setInt(i++, categoryTO.getId());
					return (ps.executeUpdate() > 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return false;
				}
			}
		});
	}

	// Retrieve category by id
	public CategoryTO RetrieveCategoryById(int id) {
		String sql = "select * from " + StaticTO.DB_CATEGORY + " where category_id=?";
		return jdbc.execute(sql, new PreparedStatementCallback<CategoryTO>() {
			@Override
			public CategoryTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				try {
					int i = 1;

					ps.setInt(i++, id);
					rs = ps.executeQuery();
					rs.next();
					return new CategoryTO(rs.getInt("category_id"), rs.getString("category_name"),
							rs.getInt("category_type"), rs.getString("category_status"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return null;
				}
			}
		});
	}

	// Retrieve category by name
	public CategoryTO RetrieveCategoryByName(String name) {
		String sql = "select * from " + StaticTO.DB_CATEGORY + " where category_name=?";
		return jdbc.execute(sql, new PreparedStatementCallback<CategoryTO>() {
			@Override
			public CategoryTO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				try {
					int i = 1;

					ps.setString(i++, name);
					rs = ps.executeQuery();
					rs.next();
					return new CategoryTO(rs.getInt("category_id"), rs.getString("category_name"),
							rs.getInt("category_type"), rs.getString("category_status"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return null;
				}
			}
		});
	}

	// Retrieve category by type
	public ArrayList<CategoryTO> RetrieveCaregoryByType(int status) {
		String sql = "select * from " + StaticTO.DB_CATEGORY + " where category_type=?";
		return jdbc.execute(sql, new PreparedStatementCallback<ArrayList<CategoryTO>>() {
			@Override
			public ArrayList<CategoryTO> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = null;
				CategoryTO categoryTO;
			    ArrayList<CategoryTO> listCategory=new ArrayList<CategoryTO>();
				try {
					int i = 1;

					ps.setInt(i++, status);
					rs = ps.executeQuery();
					rs.next();
					categoryTO= new CategoryTO(rs.getInt("category_id"), rs.getString("category_name"),
							rs.getInt("category_type"), rs.getString("category_status"));
					listCategory.add(categoryTO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
				return listCategory;
			}
		});
	}
	
}
