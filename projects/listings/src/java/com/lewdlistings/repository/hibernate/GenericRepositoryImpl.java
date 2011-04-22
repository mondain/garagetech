package com.lewdlistings.repository.hibernate;

import com.lewdlistings.repository.GenericRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericRepositoryImpl<T, Id extends Serializable> implements GenericRepository<T, Id> {

    private Class<T> entityClass;
    private SessionFactory factory;

    @SuppressWarnings({"unchecked"})
    GenericRepositoryImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> all() {
        return list(criteria());
    }

    public T create(T entity) {
        currentSession().save(entity);
        return entity;
    }

    public void delete(T entity) {
        currentSession().delete(entity);
    }

    @SuppressWarnings({"unchecked"})
    public T read(Id id) {
        return get(id);
    }

    public void saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    public void update(T entity) {
        currentSession().update(entity);
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    protected List<T> list(Criteria criteria) {
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> list(Query query) {
        return query.list();
    }

    @SuppressWarnings("unchecked")
    protected T uniqueResult(Criteria criteria) {
        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected T uniqueResult(Query query) {
        return (T) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected T get(Serializable id) {
        return (T) currentSession().get(entityClass, id);
    }

    protected Criteria criteria() {
        return currentSession().createCriteria(entityClass);
    }

    protected Session currentSession() {
        return factory.getCurrentSession();
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    protected Query query(String hql) {
        return currentSession().createQuery(hql);
    }
}
