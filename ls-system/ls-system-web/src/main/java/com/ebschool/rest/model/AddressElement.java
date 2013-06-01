package com.ebschool.rest.model;

import com.ebschool.model.Address;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "address")
public class AddressElement{

    private String city;
    private String country;
    private String street;
    private String zipCode;

    public AddressElement() {}

    public AddressElement(Address address){
        setCity(address.getCity());
        setCountry(address.getCountry());
        setStreet(address.getStreet());
        setZipCode(address.getZipCode());
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
