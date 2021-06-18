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

public class StatusDaoImpl implements Dao{

	@Override
	public <UserStatus> int insert(UserStatus status) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into user_status (id, name) values(null,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.UserStatus) status).getName());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}
	
	@Override
	public <UserStatus> int update(UserStatus status) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update user_status set name=? "
				+ "where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.UserStatus) status).getName());
		pst.setInt(2, ((main.entities.UserStatus) status).getId());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public int delete(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from user_status where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public List<UserStatus> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from user_status";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<UserStatus> status = new ArrayList<UserStatus>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			status.add(new UserStatus(id, name) );
		}
		JDBCUtil.close(conn, st, rs);
		return status;
	}

	@Override
	public <UserStatus> UserStatus findById(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from user_status WHERE id='"+ id +"' ";
		ResultSet rs = st.executeQuery(sql);
		
		UserStatus status = null;
		if (rs.next()) {
			int status_id = rs.getInt("id");
			String name = rs.getString("name");
			status = (UserStatus) new main.entities.UserStatus(status_id, name);
		}
		JDBCUtil.close(conn, st, rs);
		return status;
	}

	@Override
	public <UserStatus> List<UserStatus> findByName(String name) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select * from user_status WHERE name LIKE '%"+ name +"%' ";
		ResultSet rs = st.executeQuery(sql);
		
		List<main.entities.UserStatus> status = new ArrayList<main.entities.UserStatus>();
		while (rs.next()) {
			int status_id = rs.getInt("id");
			String status_name = rs.getString("name");
			status.add(new main.entities.UserStatus(status_id, status_name) );
		}
		JDBCUtil.close(conn, st, rs);
		return (List<UserStatus>) status;
	}

	//---------------------------------------------------------------------

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
