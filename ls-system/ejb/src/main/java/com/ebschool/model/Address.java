package com.ebschool.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User: michau
 * Date: 3/19/13
 * Time: 8:19 PM
 */
@Embeddable
public class Address implements Serializable{

    private static final long serialVersionUID = 1000L;

    @Column(length = 40, nullable = false)
    private String country;

    @Column(length = 40, nullable = false)
    private String city;

    @Column(length = 40, nullable = false)
    private String street;

    @Column(length = 6, nullable = false, name = "zip_code")
    private String zipCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
