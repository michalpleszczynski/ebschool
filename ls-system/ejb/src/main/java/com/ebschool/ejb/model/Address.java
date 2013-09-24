package com.ebschool.ejb.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: michau
 * Date: 3/19/13
 * Time: 8:19 PM
 */
@Embeddable
public class Address implements Serializable{

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

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Address.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Address address = (Address) object;
        if (!Objects.equals(getCity(), address.getCity()))
            return false;
        if (!Objects.equals(getCountry(), address.getCountry()))
            return false;
        if (!Objects.equals(getStreet(), address.getStreet()))
            return false;
        if (!Objects.equals(getZipCode(), address.getZipCode()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getCity() != null ? getCity().toString().hashCode() : 0);
        result = result*37 + (getCountry() != null ? getCountry().hashCode() : 0);
        result = result*37 + (getZipCode() != null ? getZipCode().toString().hashCode() : 0);
        result = result*37 + (getStreet() != null ? getStreet().hashCode() : 0);
        return result;
    }

}
