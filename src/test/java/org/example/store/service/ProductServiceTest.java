package org.example.store.service;

import org.example.store.model.Product;
import org.example.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductServiceTest {
    private static final String NAME = "name";
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    private final Product product = new Product(1L, NAME, BigDecimal.valueOf(1), 1);

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testAddNewProduct() {
        doNothing().when(productRepository).save(product);
        productService.addNewProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        doNothing().when(productRepository).update(product);
        productService.updateProduct(product);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    public void testFindProductByName() {
        doReturn(Optional.of(product)).when(productRepository).findByName(NAME);
        var p = productService.findProductByName(NAME);
        verify(productRepository, times(1)).findByName(NAME);
        assertTrue(p.isPresent());
        assertEquals(product.getName(), p.get().getName());
    }

    @Test
    public void testFindProductById() {
        doReturn(Optional.of(product)).when(productRepository).findById(1L);
        var p = productService.findProductById(1L);
        verify(productRepository, times(1)).findById(1L);
        assertTrue(p.isPresent());
        assertEquals(product.getId(), p.get().getId());
    }

    @Test
    public void testFindAllProducts() {
        doReturn(List.of(product)).when(productRepository).findAll();
        var list = productService.findAllProducts();
        verify(productRepository, times(1)).findAll();
        assertEquals(1, list.size());
    }

    @Test
    public void deleteProductById() {
        doReturn(Optional.of(product)).when(productRepository).findById(1L);
        doNothing().when(productRepository).delete(1L);
        productService.deleteProductById(1L);
        verify(productRepository, times(1)).delete(1L);
    }
}
