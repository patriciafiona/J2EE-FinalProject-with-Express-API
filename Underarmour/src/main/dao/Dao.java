package main.dao;

import java.sql.SQLException;
import java.util.List;

import main.entities.User;


public interface Dao {
	public <T> int insert(T t) throws SQLException ;
	public <T> int update(T t) throws SQLException ;
	public int delete(int id) throws SQLException ;
	
	public <T> List<T> findAll() throws SQLException ;
	public <T> T findById(int id) throws SQLException ;
	public <T> List<T> findByName(String name) throws SQLException ;
	public <T> T findByName02(String name) throws SQLException ;
}
