package com.ebschool.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * User: michau
 * Date: 3/19/13
 * Time: 8:13 PM
 */
@Entity
@Table(name = "detailed_info", uniqueConstraints = @UniqueConstraint(columnNames = "pin"))
public class DetailedInfo{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date_of_birth", nullable = false)
    private long dateOfBirth;

    @Column(name = "date_joined", nullable = false)
    private long dateJoined;

    @Embedded
    private Address address;

    @Column(unique = true, name = "pin")
    @Pattern(regexp = "[A-Z0-9]*", message = "must contain only digits and capital letters")
    private String identificationNumber;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(long dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
