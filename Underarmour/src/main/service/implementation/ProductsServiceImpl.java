package main.service.implementation;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.dao.implementation.ProductsDaoImpl;
import main.entities.User;
import main.service.Service;

public class ProductsServiceImpl implements Service{

	ProductsDaoImpl dao = new ProductsDaoImpl();
	
	@Override
	public <Product> int insert(Product product) throws SQLException {
		return dao.insert(product);
	}

	@Override
	public <Product> int update(Product product) throws SQLException {
		return dao.update(product);
	}

	@Override
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <Product> List<Product> findAll() throws SQLException {
		return dao.findAll();
	}
	
	public <Product> List<Product> findByManyId(ArrayList<Integer> ids) throws SQLException{
		return dao.findByManyId(ids);
	}
	
	public <Product> List<Product> findByCategory(String cat) throws SQLException{
		return dao.findByCategory(cat);
	}
	
	public <Product> List<Product> findByTagCategory(String cat, String tag) throws SQLException{
		return dao.findByTagCategory(cat, tag);
	}

	@Override
	public <Product> Product findById(int id) throws SQLException {
		return dao.findById(id);
	}
	
	@Override
	public <T> T findByName02(String name) throws SQLException {
		return dao.findByName02(name);
	}

	//--------------------------------------------------------------------------------
	
	@Override
	public <Product> List<Product> findByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
