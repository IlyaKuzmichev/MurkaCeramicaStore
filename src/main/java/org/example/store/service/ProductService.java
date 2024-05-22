package org.example.store.service;

import org.example.store.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addNewProduct(Product product);
    void updateProduct(Product product);
    Optional<Product> findProductById(Long id);
    List<Product> findAllProducts();
    void deleteProductById(Long id);
}
