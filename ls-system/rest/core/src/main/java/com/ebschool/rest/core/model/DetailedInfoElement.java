package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.DetailedInfo;
import com.ebschool.rest.core.utils.adapters.LocalDateAdapter;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "detailed_info")
public class DetailedInfoElement {

    private Long id;
    private LocalDate dateJoined;
    private LocalDate dateOfBirth;
    private String pin;
    private AddressElement address;

    public DetailedInfoElement() {}

    public DetailedInfoElement(DetailedInfo detailedInfo){
        this.id = detailedInfo.getId();
        setDateJoined(detailedInfo.getDateJoined());
        setDateOfBirth(detailedInfo.getDateOfBirth());
        setAddress(new AddressElement(detailedInfo.getAddress()));
        this.pin = detailedInfo.getIdentificationNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
