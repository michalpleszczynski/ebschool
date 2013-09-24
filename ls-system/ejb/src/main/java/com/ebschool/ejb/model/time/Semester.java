package com.ebschool.ejb.model.time;

import com.ebschool.ejb.utils.Identifiable;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: michau
 * Date: 9/20/13
 */
@Entity
@Table(name = "semester")
public class Semester implements Identifiable, Serializable, Comparable<Semester> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "begin_date", nullable = false)
    private LocalDate beginDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int compareTo(Semester o) {
        return getBeginDate().compareTo(o.getBeginDate());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null ||
                !Semester.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Semester semester = (Semester) object;
        return Objects.equals(getName(), semester.getName());
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
