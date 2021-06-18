package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.Dao;
import main.dao.implementation.PhotosDaoImpl;
import main.entities.User;
import main.service.Service;

public class PhotosServiceImpl implements Service{
	Dao dao = new PhotosDaoImpl();
	
	@Override
	public <Photo> int insert(Photo photo) throws SQLException {
		return dao.insert(photo);
	}

	@Override
	public <Photo> int update(Photo photo) throws SQLException {
		return dao.update(photo);
	}

	@Override
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <Photo> List<Photo> findAll() throws SQLException {
		return dao.findAll();
	}

	@Override
	public <Photo> Photo findById(int id) throws SQLException {
		return dao.findById(id);
	}
	
	@Override
	public <Photo> Photo findByName02(String name) throws SQLException {
		return dao.findByName02(name);
	}

	//---------------------------------------------------------------------------
	
	@Override
	public <Photo> List<Photo> findByName(String name) throws SQLException {
		return null;
	}
	
}
