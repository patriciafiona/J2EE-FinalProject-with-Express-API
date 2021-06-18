package main.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.entities.User;
import main.entities.UserStatus;
import main.utils.JDBCUtil;

public class CategoriesDaoImpl implements Dao{

	@Override
	public <Category> int insert(Category cat) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into categories (id, name) values(null,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Category) cat).getName());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Category> int update(Category cat) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update categories set name=? "
				+ "where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Category) cat).getName());
		pst.setInt(2, ((main.entities.Category) cat).getId());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public int delete(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from categories where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Category> List<Category> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from categories";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Category> cat = new ArrayList<main.entities.Category>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			main.entities.Category category = new main.entities.Category(id, name);
			cat.add(category);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Category>) cat;
	}

	@Override
	public <Category> Category findById(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from categories WHERE id='"+ id +"' ";
		ResultSet rs = st.executeQuery(sql);
		
		Category cat = null;
		if (rs.next()) {
			int status_id = rs.getInt("id");
			String name = rs.getString("name");
			cat = (Category) new main.entities.Category(status_id, name);
		}
		JDBCUtil.close(conn, st, rs);
		return cat;
	}
	
	//----------------------------------------------------------
	
	@Override
	public <T> List<T> findByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
