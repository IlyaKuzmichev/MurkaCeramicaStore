package org.example.store.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for CRUD Repositories implementation
 * @author Ilya Kuzmichev aka wilmerno
 * @param <T> Entity of the repository
 */
public interface CrudRepository<T> {
    /**
     * Standard CRUD method find
     * @param id Identifier
     * @return Optional of entity
     */
    Optional<T> findById(Long id);

    /**
     * Standard CRUD method findAll
     * @return List of entities
     */
    List<T> findAll();

    /**
     * Standard CRUD method save
     * @param entity Entity
     */
    void save(T entity);

    /**
     * Standard CRUD method update
     * @param entity Updating
     */
    void update(T entity);

    /**
     * Standard CRUD method delete
     * @param id Identifier
     */
    void delete(Long id);

}
