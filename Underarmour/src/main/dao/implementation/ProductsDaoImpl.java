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
import main.utils.JDBCUtil;

public class ProductsDaoImpl implements Dao{

	@Override
	public <Product> int insert(Product product) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into products (id, name, category, tag, rating, price, stock, color, description) "
				+ "values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Product) product).getName());
		pst.setInt(2, ((main.entities.Product) product).getCategory());
		pst.setInt(3, ((main.entities.Product) product).getTag());
		pst.setDouble(4, ((main.entities.Product) product).getRating());
		pst.setDouble(5, ((main.entities.Product) product).getPrice());
		pst.setInt(6, ((main.entities.Product) product).getStock());
		pst.setString(7, ((main.entities.Product) product).getColor());
		pst.setString(8, ((main.entities.Product) product).getDescription());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Product> int update(Product product) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update products set "
				+ "name=? ,"
				+ "category=? ,"
				+ "tag=? ,"
				+ "price=? ,"
				+ "stock=? ,"
				+ "color=? ,"
				+ "description=? "
				+ "where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, ((main.entities.Product) product).getName());
		pst.setInt(2, ((main.entities.Product) product).getCategory());
		pst.setInt(3, ((main.entities.Product) product).getTag());
		pst.setDouble(4, ((main.entities.Product) product).getPrice());
		pst.setInt(5, ((main.entities.Product) product).getStock());
		pst.setString(6, ((main.entities.Product) product).getColor());
		pst.setString(7, ((main.entities.Product) product).getDescription());
		pst.setInt(8, ((main.entities.Product) product).getId());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public int delete(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from products where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

	@Override
	public <Product> List<Product> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select products.*, categories.name AS category_name, "
				+ "tags.name AS tag_name, photos.photo_01 from products "
				+ "INNER JOIN photos ON products.id = photos.product_id "
				+ "INNER JOIN categories ON products.category = categories.id "
				+ "INNER JOIN tags ON products.tag = tags.id "
				+ "ORDER BY products.id DESC";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int category_id  = rs.getInt("category");
			String category_name = rs.getString("category_name");
			int tag_id  = rs.getInt("tag");
			String tag_name = rs.getString("tag_name");
			double rating = rs.getDouble("rating");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			String color = rs.getString("color");
			String description = rs.getString("description");
			String photo_01 = rs.getString("photo_01");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Product product = new main.entities.Product(id, name, category_id, category_name, tag_id, tag_name, rating, price, stock,
					color, description, photo_01, created_at, updated_at);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}
	
	public <Product> List<Product> findByManyId(ArrayList<Integer> ids) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		
		String query = "";
		for(int i=0; i<ids.size(); i++) {
			if(i < ids.size()-1) {
				query = query + ids.get(i) + ", ";
			}else {
				query = query + ids.get(i);
			}
			
		}
		
		String sql = "Select products.*, categories.name AS category_name, "
				+ "tags.name AS tag_name, photos.photo_01 from products "
				+ "INNER JOIN categories ON products.category = categories.id "
				+ "INNER JOIN tags ON products.tag = tags.id "
				+ "INNER JOIN photos ON products.id = photos.product_id "
				+ "where products.id IN ("+ query +") ";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int category_id  = rs.getInt("category");
			String category_name = rs.getString("category_name");
			int tag_id  = rs.getInt("tag");
			String tag_name = rs.getString("tag_name");
			double rating = rs.getDouble("rating");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			String color = rs.getString("color");
			String description = rs.getString("description");
			String photo_01 = rs.getString("photo_01");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Product product = new main.entities.Product(id, name, category_id, category_name, tag_id, tag_name, rating, price, stock,
					color, description, photo_01, created_at, updated_at);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}
	
	public <Product> List<Product> findByCategory(String cat) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select products.*, categories.name AS category_name, "
				+ "tags.name AS tag_name, photos.photo_01 from products "
				+ "INNER JOIN categories ON products.category = categories.id "
				+ "INNER JOIN tags ON products.tag = tags.id "
				+ "INNER JOIN photos ON products.id = photos.product_id "
				+ "WHERE categories.name= '"+ cat +"' "
				+ "ORDER BY products.updated_at DESC";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int category_id  = rs.getInt("category");
			String category_name = rs.getString("category_name");
			int tag_id  = rs.getInt("tag");
			String tag_name = rs.getString("tag_name");
			double rating = rs.getDouble("rating");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			String color = rs.getString("color");
			String description = rs.getString("description");
			String photo_01 = rs.getString("photo_01");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Product product = new main.entities.Product(id, name, category_id, category_name, tag_id, tag_name, rating, price, stock,
					color, description, photo_01, created_at, updated_at);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}

	public <Product> List<Product> findByTagCategory(String cat, String tag) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select products.*, categories.name AS category_name, "
				+ "tags.name AS tag_name, photos.photo_01 from products "
				+ "INNER JOIN categories ON products.category = categories.id "
				+ "INNER JOIN tags ON products.tag = tags.id "
				+ "INNER JOIN photos ON products.id = photos.product_id "
				+ "WHERE categories.name LIKE '"+ cat +"%' AND tags.name LIKE '%"+ tag +"%' "
				+ "ORDER BY products.updated_at DESC";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int category_id  = rs.getInt("category");
			String category_name = rs.getString("category_name");
			int tag_id  = rs.getInt("tag");
			String tag_name = rs.getString("tag_name");
			double rating = rs.getDouble("rating");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			String color = rs.getString("color");
			String description = rs.getString("description");
			String photo_01 = rs.getString("photo_01");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Product product = new main.entities.Product(id, name, category_id, category_name, tag_id, tag_name, rating, price, stock,
					color, description, photo_01, created_at, updated_at);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}
	
	@Override
	public <Product> Product findById(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "Select products.*, categories.name AS category_name, "
				+ "tags.name AS tag_name, photos.photo_01 from products "
				+ "INNER JOIN categories ON products.category = categories.id "
				+ "INNER JOIN tags ON products.tag = tags.id "
				+ "INNER JOIN photos ON products.id = photos.product_id "
				+ "WHERE products.id='"+ id +"' ";
		ResultSet rs = st.executeQuery(sql);
		
		main.entities.Product product = null;
		if (rs.next()) {
			int product_id = rs.getInt("id");
			String name = rs.getString("name");
			int category_id  = rs.getInt("category");
			String category_name = rs.getString("category_name");
			int tag_id  = rs.getInt("tag");
			String tag_name = rs.getString("tag_name");
			double rating = rs.getDouble("rating");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			String color = rs.getString("color");
			String description = rs.getString("description");
			String photo_01 = rs.getString("photo_01");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			product = new main.entities.Product(product_id, name, category_id, category_name, tag_id, tag_name, rating, price, stock,
					color, description, photo_01, created_at, updated_at);
		}
		JDBCUtil.close(conn, st, rs);
		return (Product) product;
	}
	
	@Override
	public <Product> Product findByName02(String name) throws SQLException {
		//only get id to insert photo
		Connection conn = JDBCUtil.getConnection();
		
		name = name.replaceAll("'","''");
		System.out.println("Name: "+ name);
		
		Statement st = conn.createStatement();
		String sql = "Select * from products WHERE name = '"+ name +"' ";
		ResultSet rs = st.executeQuery(sql);
		
		Product product = null;
		if (rs.next()) {
			int product_id = rs.getInt("id");
			
			product = (Product) new main.entities.Product(product_id);
		}
		JDBCUtil.close(conn, st, rs);
		return product;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public <T> List<T> findByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
