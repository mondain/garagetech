package com.lewdlistings.repository.usertype;

import com.lewdlistings.entity.PhoneNumber;
import com.lewdlistings.exception.PhoneNumberFormatException;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PhoneNumberUserType implements UserType {

    private static final int[] SQL_TYPES = {Types.VARCHAR};

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return value;
        try {
            return PhoneNumber.parse(value.toString());
        } catch (PhoneNumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return value.toString();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null) return (y != null);
        return (x.equals(y));
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.toString().hashCode();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        if (!rs.wasNull()) {
            try {
                return PhoneNumber.parse(rs.getString(names[0]));
            } catch (PhoneNumberFormatException e) {
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
            st.setString(index, value.toString());
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class returnedClass() {
        return PhoneNumber.class;
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }
}
