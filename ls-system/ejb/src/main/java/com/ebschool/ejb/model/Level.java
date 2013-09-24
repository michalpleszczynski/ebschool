package com.ebschool.ejb.model;

import com.ebschool.ejb.utils.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 7:29 PM
 */
@Entity
@Table(name = "level")
@NamedQueries({
        @NamedQuery(name = "findLevelByName", query = "SELECT l FROM Level AS l WHERE l.name = :name")
})
public class Level  implements Identifiable, Serializable {

    public static final String LEVEL_BY_NAME = "findLevelByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "level", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH},
            targetEntity = ClassInfo.class)
    private Set<ClassInfo> classes;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassInfo> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Level.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Level level = (Level) object;
        return getName() != null ? getName().equals(level.getName()) : false;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

}
