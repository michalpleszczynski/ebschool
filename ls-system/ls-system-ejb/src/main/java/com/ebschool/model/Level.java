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

}
