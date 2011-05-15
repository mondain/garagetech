package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Embeddable
public class Availability implements Serializable, Comparable<Availability> {

    private static final long serialVersionUID = -1932239095561486541L;

    @Column(name = "zip_code", length = 10, nullable = false)
    private String zipCode;

    @Column(name = "location", length = 255, nullable = true)
    private String location;

    @Column(name = "starts_at")
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime start;

    @Column(name = "ends_at")
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime end;

    public Availability() {
    }

    public Availability(String zipCode) {
        this.zipCode = zipCode;
    }

    public Availability(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    public Availability(String zipCode, DateTime start, DateTime end) {
        this.zipCode = zipCode;
        this.start = start;
        this.end = end;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public boolean equals(Availability availability) {
        return reflectionEquals(this, availability);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("zipCode", zipCode)
                .append("start", start)
                .append("end", end)
                .toString();
    }

    public int compareTo(Availability availability) {
        return reflectionCompare(this, availability);
    }
}
