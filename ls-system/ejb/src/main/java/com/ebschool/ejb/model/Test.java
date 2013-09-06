package com.ebschool.ejb.model;

import com.ebschool.ejb.utils.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 8:05 PM
 */
@Entity
@Table(name = "test")
@NamedQueries({
        @NamedQuery(name = "findTestsByClass", query = "SELECT t FROM Test AS t WHERE t.classInfo = :classInfo")
})
public class Test implements Identifiable, Serializable {

    private static final long serialVersionUID = 1009L;

    public static final String TESTS_BY_CLASS = "findTestsByClass";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @Column(nullable = false, name = "when_")
    private long when;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassInfo classInfo;

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

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Test.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Test test = (Test) object;
        return (getDescription() !=null ? getDescription().equals(test.getDescription()) : false) &&
                (getWhen() == test.getWhen()) &&
                (getClassInfo() !=null ? getClassInfo().getId() == test.getClassInfo().getId() : false);
    }

    // TODO: come back to this, probably wrong
    @Override
    public int hashCode(){
        String identifier = getDescription();
        if (identifier != null){
            identifier = identifier + Long.valueOf(when);
            if (classInfo != null){
                return (Long.valueOf(classInfo.getId()) + identifier).hashCode();
            }
        }
        return 0;
    }

}
