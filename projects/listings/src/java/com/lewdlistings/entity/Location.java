package com.lewdlistings.entity;

import com.lewdlistings.search.bridge.CartesianTierFieldBridgeImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.spatial.geohash.GeoHashUtils;
import org.apache.lucene.spatial.geometry.FloatLatLng;
import org.apache.lucene.spatial.geometry.LatLng;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "zipcodes")
@Indexed
public class Location implements Serializable, Comparable<Location> {

    private static final long serialVersionUID = -1455355420707668676L;

    @Id
    @Column(name = "zipcode", length = 5, nullable = false, unique = true)
    @DocumentId(name = "zipcode")
    private String zipCode;

    @Column(name = "state", length = 2, nullable = false)
    private String state;

    @Column(name = "city", length = 64, nullable = false)
    private String city;

    @Column(name = "latitude", nullable = false)
    @Field(name = "latitude", index = Index.UN_TOKENIZED, store = Store.YES)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @Field(name = "longitude", index = Index.UN_TOKENIZED, store = Store.YES)
    private Double longitude;

    public Location() {
    }

    public Location(String zipCode) {
        this.zipCode = zipCode;
    }

    public Location(String zipCode, Double latitude, Double longitude) {
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if (StringUtils.isBlank(zipCode)) {
            zipCode = null;
        }
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Transient
    @Field
    @FieldBridge(impl = CartesianTierFieldBridgeImpl.class)
    public LatLng getLatLng() {
        if (latitude == null || longitude == null) {
            return null;
        }
        return new FloatLatLng(latitude, longitude);
    }

    @Transient
    @Field(index = Index.UN_TOKENIZED, store = Store.YES, name = "geohash")
    public String getGeoHash() {
        if (latitude == null || longitude == null) {
            return null;
        }
        return GeoHashUtils.encode(latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (zipCode != location.zipCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return zipCode.hashCode();
    }

    @Override
    public int compareTo(Location location) {
        return new CompareToBuilder()
                .append(zipCode, location.getZipCode())
                .toComparison();
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append(zipCode)
                .toString();
    }
}
