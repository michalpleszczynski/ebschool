package com.ebschool.model;

import com.ebschool.utils.Identifiable;

import javax.persistence.*;
import java.util.HashSet;
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
public class Level  implements Identifiable {

    public static final String LEVEL_BY_NAME = "findLevelByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_", unique = true)
    private String name;

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
        return getName() != null ? getName().hashCode() : 0;
    }
}
