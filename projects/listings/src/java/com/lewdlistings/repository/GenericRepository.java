package com.lewdlistings.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T, Id extends Serializable> {

    List<T> all();

    T create(T entity);

    void delete(T entity);

    T read(Id id);

    void saveOrUpdate(T entity);

    void update(T entity);
}
