package com.ebschool.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 7:29 PM
 */
@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL)
    private Set<Class> classes;

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
