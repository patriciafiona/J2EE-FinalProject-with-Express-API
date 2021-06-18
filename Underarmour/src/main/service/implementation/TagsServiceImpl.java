package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.Dao;
import main.dao.implementation.TagsDaoImpl;
import main.entities.User;
import main.service.Service;

public class TagsServiceImpl implements Service{
	Dao dao = new TagsDaoImpl();

	@Override
	public <Tag> int insert(Tag tag) throws SQLException {
		return dao.insert(tag);
	}
	
	@Override
	public <Tag> int update(Tag tag) throws SQLException {
		return dao.update(tag);
	}

	@Override
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <Tag> List<Tag> findAll() throws SQLException {
		return dao.findAll();
	}

	@Override
	public <Tag> Tag findById(int id) throws SQLException {
		return dao.findById(id);
	}

	@Override
	public <Tag> List<Tag> findByName(String name) throws SQLException {
		return dao.findByName(name);
	}
	
	//-------------------------------------------------------------------

	@Override
	public <T> T findByName02(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
