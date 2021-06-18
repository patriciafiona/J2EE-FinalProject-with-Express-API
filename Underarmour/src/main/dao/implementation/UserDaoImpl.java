package main.dao.implementation;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import main.dao.Dao;
import main.entities.User;
import main.utils.C3P0Util;
import main.utils.JDBCUtil;

public class UserDaoImpl implements Dao{
	
	private String ConvertResult(String val) {
		String temp = "";
		try {
			temp = new String(val.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	public User findUserByEmail(String email) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from users where email=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		User user = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = ConvertResult(rs.getString("name") );
			
			String user_email = rs.getString("email");
			int status = rs.getInt("status");
			Date bod = rs.getDate("bod");
			String address =  rs.getString("address");
			String phone_number = rs.getString("phone_number");
			String password = rs.getString("password");
			String photo = rs.getString("photo");
			int isLogin = rs.getInt("isLogin");
			user = new User(id, name, user_email, password, status, bod, address, phone_number, photo, isLogin);
		}
		JDBCUtil.close(conn, pst, rs);
		return user;
	}
	
	@Override
	public <User> int insert(User user) throws SQLException {
		if(((main.entities.User) user).getPassword() != null && ((main.entities.User) user).getAddress() != null && 
				((main.entities.User) user).getPhone_number() != null ) {
			String sql = "insert into users (id, name, email, password, status, bod, address, phone_number, isLogin) "
					+ "values(null,?,?,?,?,?,?,?,?)";
			QueryRunner run = new QueryRunner(C3P0Util.getDataSource());
			return run.update(sql, 
					((main.entities.User) user).getName(),
					((main.entities.User) user).getEmail(),
					((main.entities.User) user).getPassword(),
					((main.entities.User) user).getStatus(),
					((main.entities.User) user).getBod(),
					((main.entities.User) user).getAddress(),
					((main.entities.User) user).getPhone_number(),
					((main.entities.User) user).getIsLogin());
		}else {
			String sql = "insert into users (id, name, email, status, bod, password, photo, isLogin) "
					+ "values(null,?,?,?,?,?,?,?)";
			QueryRunner run = new QueryRunner(C3P0Util.getDataSource());
			return run.update(sql, 
					((main.entities.User) user).getName(),
					((main.entities.User) user).getEmail(),
					((main.entities.User) user).getStatus(),
					((main.entities.User) user).getBod(),
					((main.entities.User) user).getPassword(), 
					((main.entities.User) user).getPhoto(), 
					((main.entities.User) user).getIsLogin());
		}
		
	}
	
	@Override
	public <User> int update(User user) throws SQLException {
		System.out.println("Enter Here");
		
		Connection conn = JDBCUtil.getConnection();
		
		if(((main.entities.User) user).getPhoto() == null || ((main.entities.User) user).getPhoto().isEmpty() ) {
			String sql = "update users set name=?, email=?, bod=?, address=?, phone_number=?, updated_at=? "
					+ "where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			System.out.println("ID: "+ ((main.entities.User) user).getId());
			
			pst.setString(1, ((main.entities.User) user).getName());
			pst.setString(2, ((main.entities.User) user).getEmail());
			pst.setDate(3, ((main.entities.User) user).getBod());
			pst.setString(4, ((main.entities.User) user).getAddress());
			pst.setString(5, ((main.entities.User) user).getPhone_number());
			
			java.util.Date date= new java.util.Date();
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			pst.setTimestamp(6, timestamp);
			
			pst.setInt(7, ((main.entities.User) user).getId());
			int n = pst.executeUpdate();
			JDBCUtil.close(conn, pst);
			return n;
		}else {
			String sql = "update users set name=?, email=?, bod=?, address=?, phone_number=?, photo=?, updated_at=? "
					+ "where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			System.out.println("ID: "+ ((main.entities.User) user).getId());
			
			pst.setString(1, ((main.entities.User) user).getName());
			pst.setString(2, ((main.entities.User) user).getEmail());
			pst.setDate(3, ((main.entities.User) user).getBod());
			pst.setString(4, ((main.entities.User) user).getAddress());
			pst.setString(5, ((main.entities.User) user).getPhone_number());
			pst.setString(6, ((main.entities.User) user).getPhoto());
			
			java.util.Date date= new java.util.Date();
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			pst.setTimestamp(7, timestamp);
			
			pst.setInt(8, ((main.entities.User) user).getId());
			int n = pst.executeUpdate();
			JDBCUtil.close(conn, pst);
			return n;
		}
		
	}
	
	public int update(int id, int isLogin) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update users set isLogin=? where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, isLogin);
		pst.setInt(2, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}
	
	public int updatePassword(int id, String encryptPass) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update users set password=?, updated_at=? where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, encryptPass);
		
		java.util.Date date= new java.util.Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		pst.setTimestamp(2, timestamp);
		
		pst.setInt(3, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public int delete(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from users where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public List<User> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "select users.*, user_status.name AS status_name "
				+ "from users "
				+ "INNER JOIN user_status ON users.status = user_status.id "
				+ "ORDER BY users.id ASC";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.User> users = new ArrayList<main.entities.User>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			int status = rs.getInt("status");
			String status_name = rs.getString("status_name");
			Date bod = rs.getDate("bod");
			String address =  rs.getString("address");
			String phone_number = rs.getString("phone_number");
			String photo = rs.getString("photo");
			int isLogin = rs.getInt("isLogin");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.User user = new main.entities.User(id, name, email, status, status_name, 
					bod, address, phone_number, photo, isLogin, created_at, updated_at);
			users.add(user);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<User>) users;
	}

	@Override
	public User findById(int user_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from users where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, user_id);
		ResultSet rs = pst.executeQuery();
		User user = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			int status = rs.getInt("status");
			String phoneNumber = rs.getString("phone_number");
			Date bod = rs.getDate("bod");
			String address = rs.getString("address");
			String password = rs.getString("password");
			String photo = rs.getString("photo");
			int isLogin = rs.getInt("isLogin");
			user = new User(user_id, name, email, password, status, bod, address, phoneNumber, photo, isLogin);
		}
		JDBCUtil.close(conn, pst, rs);
		return user;
	}
	
	//---------------------------------------------------------------------------------

	@Override
	public <T> List<T> findByName(String name) throws SQLException {
		return null;
	}

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
