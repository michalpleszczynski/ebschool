package com.ebschool.model;

import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * User: michau
 * Date: 3/19/13
 * Time: 8:13 PM
 */
@Entity
@Table(name = "detailed_info", uniqueConstraints = @UniqueConstraint(columnNames = "pesel"))
public class DetailedInfo{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "date_joined", nullable = false)
    private LocalDateTime dateJoined;

    @Embedded
    private Address address;

    @Column(unique = true, nullable = false)
    private String pesel;

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
