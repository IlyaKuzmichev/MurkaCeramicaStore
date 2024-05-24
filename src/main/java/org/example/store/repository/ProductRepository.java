package org.example.store.repository;

import org.example.store.model.Product;

import java.util.Optional;

/**
 * Interface for Product Repository, extended from base CRUD repository
 * @author Ilya Kuzmichev aka wilmerno
 */
public interface ProductRepository extends CrudRepository<Product> {
    Optional<Product> findByName(String name);
}
