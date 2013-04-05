package com.ebschool.model;

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
    private long when;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class aClass;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

}
