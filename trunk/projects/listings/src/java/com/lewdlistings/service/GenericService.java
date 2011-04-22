package com.lewdlistings.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, Id extends Serializable> {

    List<T> all();
    T create(T entity);
    void delete(T entity);
    T read(Id id);
    void update(T entity);
}
