package com.lewdlistings.repository.id;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class MillisecondIdGenerator implements IdentifierGenerator, Configurable {

    private final Long initialValue = System.currentTimeMillis();
    private long nextValue = initialValue;

    @Override
    public Serializable generate(SessionImplementor implementor, Object obj) throws HibernateException {
        long now = System.currentTimeMillis();
        synchronized(initialValue) {
            ++nextValue;
            if (now > nextValue) {
                nextValue = now;
            }
            return nextValue;
        }
    }

    @Override
    public void configure(Type type, Properties properties, Dialect dialect) throws MappingException {
        Class<?> clazz = type.getReturnedClass();
        if (!clazz.isAssignableFrom(Long.class)) {
            throw new MappingException("MillisecondIdGenerator requires java.lang.Long as the type");
        }
    }
}
