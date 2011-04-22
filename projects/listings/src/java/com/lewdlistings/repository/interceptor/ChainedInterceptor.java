package com.lewdlistings.repository.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;

public class ChainedInterceptor extends EmptyInterceptor {

    private Interceptor[] interceptors;

    @Override
    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {
        boolean result = false;
        for (Interceptor interceptor : interceptors) {
            if (interceptor.onFlushDirty(entity, id, currentState, previousState, propertyNames, types)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        boolean result = false;
        for (Interceptor interceptor : interceptors) {
            if (interceptor.onSave(entity, id, state, propertyNames, types)) {
                result = true;
            }
        }
        return result;
    }

    public Interceptor[] getInterceptors() {
        return interceptors;
    }

    @Required
    public void setInterceptors(Interceptor[] interceptors) {
        this.interceptors = interceptors;
    }
}