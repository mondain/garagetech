package com.lewdlistings.service.impl;

import com.lewdlistings.repository.GenericRepository;
import com.lewdlistings.service.GenericService;

import java.io.Serializable;

public abstract class GenericServiceImpl<T, Id extends Serializable> implements GenericService<T, Id> {

    private final GenericRepository repos;

    protected GenericServiceImpl(GenericRepository repos) {
        this.repos = repos;
    }
}