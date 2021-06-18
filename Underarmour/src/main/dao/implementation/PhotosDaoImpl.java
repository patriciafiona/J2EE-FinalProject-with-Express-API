package main.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.entities.User;
import main.utils.JDBCUtil;

public class PhotosDaoImpl implements Dao{

	@Override
	public <Photo> int insert(Photo photo) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into photos (id, product_id, photo_01) values(null,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, ((main.entities.Photo) photo).getProduct_id());
		pst.setString(2, ((main.entities.Photo) photo).getPhoto_01());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Photo> int update(Photo photo) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update photos set "
				+ "photo_01=? ,"
				+ "photo_02=? ,"
				+ "photo_03=? ,"
				+ "photo_04=? ,"
				+ "photo_05=? ,"
				+ "updated_at=? "
				+ "where product_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Photo) photo).getPhoto_01());
		pst.setString(2, ((main.entities.Photo) photo).getPhoto_02());
		pst.setString(3, ((main.entities.Photo) photo).getPhoto_03());
		pst.setString(4, ((main.entities.Photo) photo).getPhoto_04());
		pst.setString(5, ((main.entities.Photo) photo).getPhoto_05());
		
		java.util.Date date= new java.util.Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		pst.setTimestamp(6, timestamp);
		
		pst.setInt(7, ((main.entities.Photo) photo).getProduct_id());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}
	
	@Override
	public int delete(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from photos where product_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Photo> List<Photo> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from photos";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Photo> photos = new ArrayList<main.entities.Photo>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String photo_01 = rs.getString("photo_01");
			String photo_02 = rs.getString("photo_02");
			String photo_03 = rs.getString("photo_03");
			String photo_04 = rs.getString("photo_04");
			String photo_05 = rs.getString("photo_05");
			main.entities.Photo photo = new main.entities.Photo(id, photo_01, photo_02, photo_03, photo_04, photo_05);
			photos.add(photo);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Photo>) photos;
	}

	@Override
	public <Photo> Photo findById(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from photos WHERE id='"+ id +"' ";
		ResultSet rs = st.executeQuery(sql);
		
		main.entities.Photo  photo = null;
		if (rs.next()) {
			int photo_id = rs.getInt("id");
			String photo_01 = rs.getString("photo_01");
			String photo_02 = rs.getString("photo_02");
			String photo_03 = rs.getString("photo_03");
			String photo_04 = rs.getString("photo_04");
			String photo_05 = rs.getString("photo_05");
			photo = new main.entities.Photo(photo_id, photo_01, photo_02, photo_03, photo_04, photo_05);
		}
		JDBCUtil.close(conn, st, rs);
		return (Photo) photo;
	}

	//-----------------------------------------------------------------------
	
	@Override
	public <Photo> List<Photo> findByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
