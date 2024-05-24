package org.example.store.service;

import org.example.store.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interface for Product Service
 * @author Ilya Kuzmichev aka wilmerno
 */
public interface ProductService {
    void addNewProduct(Product product);
    void updateProduct(Product product);
    Optional<Product> findProductByName(String name);
    Optional<Product> findProductById(Long id);
    List<Product> findAllProducts();
    boolean deleteProductById(Long id);
}
