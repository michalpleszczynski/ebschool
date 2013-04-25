package com.ebschool.model;

import com.ebschool.utils.Identifiable;

import javax.persistence.*;
import java.util.Set;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 7:29 PM
 */
@Entity
@Table(name = "level")
public class Level  implements Identifiable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name_")
    private String name;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL)
    private Set<ClassInfo> classes;

    public Set<ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassInfo> classes) {
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
