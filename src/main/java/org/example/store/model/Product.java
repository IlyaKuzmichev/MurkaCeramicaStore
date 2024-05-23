package org.example.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Product entity
 */
@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
}
