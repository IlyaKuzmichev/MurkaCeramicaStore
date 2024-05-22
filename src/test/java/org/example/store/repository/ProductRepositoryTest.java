package org.example.store.repository;

import org.example.store.config.TestApplicationConfig;
import org.example.store.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByIdProductInProductRepository() {
        assertTrue(productRepository.findById(1L).isPresent());
    }

    @Test
    public void testFindAllProductsFromProductRepository() {
        assertEquals(3, productRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveProductToProductRepository() {
        // No test because of H2 not supported RETURNING id...
    }

    @Test
    public void testUpdateProductInProductRepository() {
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        String newName = "a";
        BigDecimal newPrice = BigDecimal.valueOf(12.34);
        int newQuantity = -1;

        product.setName(newName);
        product.setPrice(newPrice);
        product.setQuantity(newQuantity);

        productRepository.update(product);
        product = productRepository.findById(id).get();

        assertEquals(newName, product.getName());
        assertEquals(newPrice, product.getPrice());
        assertEquals(newQuantity, product.getQuantity());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteFromProductRepository() {
        int size = productRepository.findAll().size();
        productRepository.delete(1L);
        assertEquals(size - 1, productRepository.findAll().size());
    }

}
