package com.lewdlistings.repository.interceptor;

import com.lewdlistings.entity.Auditable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import java.io.Serializable;

import static com.lewdlistings.repository.interceptor.InterceptorUtils.setValue;

public class AuditableInterceptor extends EmptyInterceptor {

    @Override
    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {
        if (entity instanceof Auditable) {
            setValue(currentState, propertyNames, "updated", new DateTime());
            return true;
        }
        return false;
    }

    @Override
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        if (entity instanceof Auditable) {
            setValue(state, propertyNames, "created", new DateTime());
            return true;
        }
        return false;
    }
}