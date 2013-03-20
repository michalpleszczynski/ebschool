package com.ebschool.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:20 PM
 */
@Entity
public class Student extends User {

    private Address address;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "info_id", nullable = false, unique = true)
    private DetailedInfo detailedInfo;

}
