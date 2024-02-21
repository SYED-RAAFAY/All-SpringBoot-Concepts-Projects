package com.example.ShoppingCartProject.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {
    private JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll(){
        String sql = "SELECT * FROM products";
        RowMapper<Product> rowMapper = new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageURL(rs.getString("imageurl"));
                return product;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int saveNewProduct(Product product){
        String sql = "INSERT INTO products (product_name, category_name, price, quantity, " +
                "imageurl) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getProductName(), product.getCategoryName(),
                product.getPrice(), product.getQuantity(), product.getImageURL());
    }

    public int editProduct(Product givenProduct){
        String sql = "UPDATE products SET product_name = ?, category_name = ?, price = ?, " +
                "quantity = ?, imageurl = ? WHERE id = ?";
        return jdbcTemplate.update(sql, givenProduct.getProductName(), givenProduct.getCategoryName(),
                givenProduct.getPrice(), givenProduct.getQuantity(),
                givenProduct.getImageURL(), givenProduct.getId());
    }

    public List<Product> findProductByProductName(String productName){
        String sql = "SELECT * FROM products WHERE product_name = ?";
        RowMapper<Product> rowMapper = new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageURL(rs.getString("imageurl"));
                return product;
            }
        };
        return jdbcTemplate.query(sql, rowMapper, productName);
    }


}
