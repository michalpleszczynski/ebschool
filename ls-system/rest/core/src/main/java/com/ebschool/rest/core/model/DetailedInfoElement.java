package com.ebschool.rest.core.model;

import com.ebschool.model.DetailedInfo;
import com.ebschool.rest.core.utils.RestHelper;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "detailed_info")
public class DetailedInfoElement {

    private Long id;
    private String dateJoined;
    private String dateOfBirth;
    private String pin;
    private AddressElement address;

    public DetailedInfoElement() {}

    public DetailedInfoElement(DetailedInfo detailedInfo){
        setId(detailedInfo.getId());
        setDateJoined(RestHelper.convertLongDate(detailedInfo.getDateJoined(),"MM/dd/yyyy"));
        setDateOfBirth(RestHelper.convertLongDate(detailedInfo.getDateOfBirth(), "MM/dd/yyyy"));
        setAddress(new AddressElement(detailedInfo.getAddress()));
        setPin(detailedInfo.getIdentificationNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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
