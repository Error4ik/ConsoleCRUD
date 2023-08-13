package com.voronin.repository;

import java.util.List;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public interface GenericRepository<T, ID> {

    T save(T t);

    T findById(ID id);

    List<T> findAll();

    T update(T t);

    void deleteById(ID id);
}
