package com.ebschool.model;

import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 8:05 PM
 */
@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue
    private long id;

    private String description;

    @Column(nullable = false)
    private LocalDateTime when;

}
