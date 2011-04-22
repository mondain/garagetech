package com.lewdlistings.repository.interceptor;

public class InterceptorUtils {

    public static Object getValue(Object[] currentState, String[] propertyNames, String propertyToGet) {
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyToGet.equals(propertyNames[i])) {
                return currentState[i];
            }
        }
        return null;
    }

    public static void setValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object value) {
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyToSet.equals(propertyNames[i])) {
                currentState[i] = value;
            }
        }
    }

    private InterceptorUtils() {}
}