package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.implementation.UserDaoImpl;
import main.entities.User;
import main.service.Service;

public class UserServiceImpl implements Service{
	UserDaoImpl dao = new UserDaoImpl();

	public User findUserByEmail(String email) throws SQLException{
		return dao.findUserByEmail(email);
	}
	
	@Override
	public <User> int insert(User user) throws SQLException {
		return dao.insert(user);
	}
	
	public int update(int id, int isLogin) throws SQLException {
		return dao.update(id, isLogin);
	}
	
	@Override
	public <User> int update(User user) throws SQLException {
		return dao.update(user);
	}
	
	public int updatePassword(int id, String encryptPass) throws SQLException {
		return dao.updatePassword(id, encryptPass);
	}

	@Override
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <User> List<User> findAll() throws SQLException {
		List<User> userlist = null;
		userlist = (List<User>) dao.findAll();
		return userlist;
	}

	@Override
	public <User> User findById(int id) throws SQLException {
		User user = null;
		user = (User) dao.findById(id);
		return user;
	}

	@Override
	public <User> List<User> findByName(String name) throws SQLException {
		List<User> userlist = null;
		userlist = dao.findByName(name);
		return userlist;
	}

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
