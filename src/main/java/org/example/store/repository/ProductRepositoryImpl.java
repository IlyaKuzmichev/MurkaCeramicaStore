package org.example.store.repository;

import org.example.store.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of Product Repository interface for base CRUD operations
 * @author Ilya Kuzmichev aka wilmerno
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Method for getting product by identifier
     * @param id Identifier of the entity in database
     * @return Optional of Product entity with data from database
     * @since 1.0
     */
    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select * from products where id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    /**
     * Method for getting all data from database
     * @return List of all product entities stored
     * @since 1.0
     */
    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Method for saving new product in database
     * Sets the assigned identifier from db to the entity
     * @param entity Product entity with data
     * @since 1.0
     */
    @Override
    public void save(Product entity) {
        String sql = "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, entity.getName(), entity.getPrice(), entity.getQuantity());
        entity.setId(id);
    }

    /**
     * Method for updating existing product in the database
     * @param entity Updated entity
     * @since 1.0
     */
    @Override
    public void update(Product entity) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, entity.getName(), entity.getPrice(), entity.getQuantity(), entity.getId());

    }

    /**
     * Method for deleting product from the database
     * @param id Identifier of the product in db
     * @since 1.0
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    /**
     * Lambda function for mapping rows from db to the Product entity
     * @since 1.0
     */
    private final RowMapper<Product> rowMapper = (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getBigDecimal("price"),
            resultSet.getInt("quantity")
    );

}
