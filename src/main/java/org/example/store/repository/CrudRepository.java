package org.example.store.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for CRUD Repositories implementation
 * @author Ilya Kuzmichev aka wilmerno
 * @param <T> Entity of the repository
 */
public interface CrudRepository<T> {
    Optional<T> findById(Long id);

    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(Long id);

}
