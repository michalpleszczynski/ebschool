package com.ebschool.ejb.model;

import com.ebschool.ejb.utils.Identifiable;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: michau
 * Date: 3/19/13
 * Time: 8:13 PM
 */
@Entity
@Table(name = "detailed_info", uniqueConstraints = @UniqueConstraint(columnNames = "pin"))
public class DetailedInfo implements Identifiable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_birth", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateOfBirth;

    @Column(name = "date_joined", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateJoined;

    @Embedded
    private Address address;

    @Column(unique = true, name = "pin")
    @Pattern(regexp = "[A-Z0-9]*", message = "must contain only digits and capital letters")
    private String identificationNumber;

    @Override
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
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

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !DetailedInfo.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final DetailedInfo detailedInfo = (DetailedInfo) object;
        return Objects.equals(getIdentificationNumber(), detailedInfo.getIdentificationNumber());
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getIdentificationNumber() != null ? getIdentificationNumber().hashCode() : 0);
        return result;
    }
}
