package com.ebschool.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:20 PM
 */
@Entity
@Table(name = "student")
public class Student extends User {

    private Address address;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", nullable = false, unique = true)
    private DetailedInfo detailedInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Grade> grades;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DetailedInfo getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(DetailedInfo detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
