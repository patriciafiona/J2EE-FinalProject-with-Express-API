package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.Dao;
import main.dao.implementation.CategoriesDaoImpl;
import main.service.Service;

public class CategoriesServiceImpl implements Service{
	Dao dao = new CategoriesDaoImpl();

	@Override
	public <Category> int insert(Category cat) throws SQLException {
		return dao.insert(cat);
	}
	
	@Override
	public <Category> int update(Category cat) throws SQLException {
		return dao.update(cat);
	}

	@Override
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <Category> List<Category> findAll() throws SQLException {
		return dao.findAll();
	}

	@Override
	public <Category> Category findById(int id) throws SQLException {
		return dao.findById(id);
	}

	@Override
	public <Category> List<Category> findByName(String name) throws SQLException {
		return dao.findByName(name);
	}
	
	//-------------------------------------------------------------------
	
	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
