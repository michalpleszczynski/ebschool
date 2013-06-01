package com.ebschool.rest.model;

import com.ebschool.model.DetailedInfo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "detailed_info")
public class DetailedInfoElement {

    private Long id;
    private long dateJoined;
    private long dateOfBirth;
    private String pin;
    private AddressElement address;

    public DetailedInfoElement() {}

    public DetailedInfoElement(DetailedInfo detailedInfo){
        setId(detailedInfo.getId());
        setDateJoined(detailedInfo.getDateJoined());
        setDateOfBirth(detailedInfo.getDateOfBirth());
        setAddress(new AddressElement(detailedInfo.getAddress()));
        setPin(detailedInfo.getIdentificationNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(long dateJoined) {
        this.dateJoined = dateJoined;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public AddressElement getAddress() {
        return address;
    }

    public void setAddress(AddressElement address) {
        this.address = address;
    }
}
