package com.ebschool.ejb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:19 PM
 */
@Entity
@Table(name = "teacher")
@NamedQueries({
        @NamedQuery(name = "findTeachersByClass", query = "SELECT t FROM Teacher AS t WHERE :classInfo MEMBER OF t.classes")
})
public class Teacher extends User implements Serializable {

    public static final String TEACHERS_BY_CLASS = "findTeachersByClass";

    public enum Related {
        DETAILED_INFO ("detailedInfo");

        private final String name;

        private Related(String name){
            this.name = name;
        }

        @Override
        public String toString(){
            return name;
        }
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] avatar;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", nullable = false, unique = true)
    private DetailedInfo detailedInfo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "teacher_class",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "class_id"))
    private Set<ClassInfo> classes;

    public Teacher(){
        classes = new HashSet<ClassInfo>();
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Set<ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassInfo> classes) {
        this.classes = classes;
    }

    public DetailedInfo getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(DetailedInfo detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Teacher.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Teacher teacher = (Teacher) object;
        return getDetailedInfo() !=null ? getDetailedInfo().equals(teacher.getDetailedInfo()) : false;
    }

    @Override
    public int hashCode(){
        return getDetailedInfo() != null ? getDetailedInfo().hashCode() : 0;
    }
}
