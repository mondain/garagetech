package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Embeddable
public class Availability implements Serializable, Comparable<Availability> {

    private static final long serialVersionUID = -1932239095561486541L;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "current_zipcode")
    private Location location;

    @Column(name = "current_starts_at")
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime start;

    @Column(name = "current_ends_at")
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime end;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "prebook_zipcode", nullable = true)
    private Location prebookLocation;

    @Column(name = "prebook_starts_at", nullable = true)
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime prebookStart;

    @Column(name = "prebook_ends_at", nullable = true)
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime prebookEnd;

    public Availability() {
    }

    public Availability(Location location) {
        this.location = location;
    }

    public Availability(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    public Availability(Location location, DateTime start, DateTime end) {
        this.location = location;
        this.start = start;
        this.end = end;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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

    public Location getPrebookLocation() {
        return prebookLocation;
    }

    public void setPrebookLocation(Location prebookLocation) {
        this.prebookLocation = prebookLocation;
    }

    public DateTime getPrebookStart() {
        return prebookStart;
    }

    public void setPrebookStart(DateTime prebookStart) {
        this.prebookStart = prebookStart;
    }

    public DateTime getPrebookEnd() {
        return prebookEnd;
    }

    public void setPrebookEnd(DateTime prebookEnd) {
        this.prebookEnd = prebookEnd;
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
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        if (location != null) {
            toStringBuilder.append("location", location.getZipCode());
        }
        if (start != null) {
            toStringBuilder.append("start", start);
        }
        if (end != null) {
            toStringBuilder.append("end", end);
        }
        return toStringBuilder.toString();
    }

    public int compareTo(Availability availability) {
        return reflectionCompare(this, availability);
    }
}
