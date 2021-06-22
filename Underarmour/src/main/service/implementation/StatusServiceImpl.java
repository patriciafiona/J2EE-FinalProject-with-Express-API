package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.Dao;
import main.dao.implementation.StatusDaoImpl;
import main.service.Service;

public class StatusServiceImpl implements Service{
	Dao dao = new StatusDaoImpl();

	@Override
	public <UserStatus> int insert(UserStatus us) throws SQLException {
		return dao.insert(us);
	}
	
	@Override
	public <UserStatus> int update(UserStatus us) throws SQLException {
		return dao.update(us);
	}

	@Override
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <UserStatus> List<UserStatus> findAll() throws SQLException {
		return dao.findAll();
	}

	@Override
	public <UserStatus> UserStatus findById(int id) throws SQLException {
		return dao.findById(id);
	}

	@Override
	public <UserStatus> List<UserStatus> findByName(String name) throws SQLException {
		return dao.findByName(name);
	}
	
	//-------------------------------------------------------------------

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
