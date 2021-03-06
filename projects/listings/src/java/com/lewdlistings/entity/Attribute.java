package com.lewdlistings.entity;

import org.hibernate.annotations.Type;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@MappedSuperclass
public class Attribute implements Serializable {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "value", nullable = true, columnDefinition = "longtext")
    @Type(type = "json")
    private JSONObject value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public boolean getBooleanValue() {
        return value != null ? value.optBoolean(getName()) : null;
    }

    public void setBooleanValue(boolean value) {
        try {
            setValue(new JSONObject().putOpt(getName(), value));
        } catch (JSONException ignore) {
            // Do nothing...
        }
    }

    @Transient
    public long getLongValue() {
        return value != null ? value.optLong(getName()) : null;
    }

    public void setLongValue(long value) {
        try {
            setValue(new JSONObject().putOpt(getName(), value));
        } catch (JSONException ignore) {
            // Do nothing...
        }
    }

    @Transient
    public Object getObjectValue() {
        return value != null ? value.opt(getName()) : null;
    }

    public void setObjectValue(Object value) {
        try {
            setValue(new JSONObject().putOpt(getName(), value));
        } catch (JSONException ignore) {
            // Do nothing...
        }
    }

    @Transient
    public String getStringValue() {
        return value != null ? value.optString(getName()) : null;
    }

    public void setStringValue(String value) {
        try {
            setValue(new JSONObject().putOpt(getName(), value));
        } catch (JSONException ignore) {
            // Do nothing...
        }
    }

    protected JSONObject getValue() {
        return value;
    }

    protected void setValue(JSONObject value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                '}';
    }
}
