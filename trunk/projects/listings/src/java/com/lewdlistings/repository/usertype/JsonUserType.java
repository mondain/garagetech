package com.lewdlistings.repository.usertype;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class JsonUserType implements UserType {

    private static final int[] SQL_TYPES = {Types.LONGVARCHAR};

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return value;
        try {
            return new JSONObject(((JSONObject) value).toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return ((JSONObject) value).toString();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null) return (y != null);
        return (x.equals(y));
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return ((JSONObject) x).toString().hashCode();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        if (!rs.wasNull()) {
            try {
                return new JSONObject(rs.getString(names[0]));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, SQL_TYPES[0]);
        } else {
            st.setString(index, ((JSONObject) value).toString());
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class returnedClass() {
        return JSONObject.class;
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }
}
