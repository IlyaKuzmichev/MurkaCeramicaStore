package org.example.store.service;

import org.example.store.model.Product;
import org.example.store.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the Product Service for Product Repository layer
 * @author Ilya Kuzmichev aka wilmerno
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Method for adding new product
     * @param product Entity of the product
     * @since 1.0
     */
    @Override
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Method for updating existing product
     * @param product Entity of the product
     * @since 1.0
     */
    @Override
    public void updateProduct(Product product) {
        productRepository.update(product);
    }


    /**
     * Method for finding product by it's identifier
     * @param name Product name
     * @return Optional of Product (not present due to product doesn't exist with presented identifier)
     * @since 1.0
     */
    @Override
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    /**
     * Method for finding product by it's identifier
     * @param id Product identifier
     * @return Optional of Product (not present due to product doesn't exist with presented identifier)
     * @since 1.0
     */
    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Method, that returns Collection of all products
     * @return List of products
     * @since 1.0
     */
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Method for deleting product from the store
     * @param id Product identifier
     * @return Has deletion been done
     * @since 1.0
     */
    @Override
    @Transactional
    public boolean deleteProductById(Long id) {
        if(productRepository.findById(id).isEmpty()) {
            return false;
        }
        productRepository.delete(id);
        return true;
    }
}
