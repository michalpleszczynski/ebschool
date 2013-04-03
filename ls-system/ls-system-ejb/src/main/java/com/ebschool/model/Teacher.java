package com.ebschool.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:19 PM
 */
@Entity
@Table(name = "teacher")
public class Teacher extends User {

    private Address address;

    @Lob
    private byte[] avatar;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "info_id", nullable = false, unique = true)
    private DetailedInfo detailedInfo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_class",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "class_id"))
    private Set<Class> classes;

}
