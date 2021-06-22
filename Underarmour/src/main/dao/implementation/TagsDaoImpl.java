package main.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.utils.JDBCUtil;

public class TagsDaoImpl implements Dao{

	@Override
	public <Tag> int insert(Tag tag) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into tags (id, name) values(null,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Tag) tag).getName());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Tag> int update(Tag tag) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update tags set name=? "
				+ "where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Tag) tag).getName());
		pst.setInt(2, ((main.entities.Tag) tag).getId());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public int delete(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from tags where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Tag> List<Tag> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from tags";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Tag> tags = new ArrayList<main.entities.Tag>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			main.entities.Tag tag = new main.entities.Tag(id, name);
			tags.add(tag);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Tag>) tags;
	}

	@Override
	public <Tag> Tag findById(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from tags WHERE id='"+ id +"' ";
		ResultSet rs = st.executeQuery(sql);
		
		Tag tag = null;
		if (rs.next()) {
			int tag_id = rs.getInt("id");
			String name = rs.getString("name");
			tag = (Tag) new main.entities.Tag(tag_id, name);
		}
		JDBCUtil.close(conn, st, rs);
		return tag;
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
