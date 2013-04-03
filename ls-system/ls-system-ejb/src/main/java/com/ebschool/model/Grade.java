package com.ebschool.model;

import javax.persistence.*;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 8:03 PM
 */
@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue
    private long id;

    private byte precentage;
    private byte weight;
    private String comment;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Test test;

}
