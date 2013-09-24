package com.ebschool.ejb.model;

import com.ebschool.ejb.utils.Identifiable;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * User: michau
 * Date: 4/3/13
 * Time: 8:05 PM
 */
@Entity
@Table(name = "student_task")
@NamedQueries({
        @NamedQuery(name = "findTestsByClass", query = "SELECT t FROM StudentTask AS t WHERE t.classInfo = :classInfo")
})
public class StudentTask implements Identifiable, Serializable {

    public static final String TESTS_BY_CLASS = "findTestsByClass";

    public enum TaskType {
        TEST,
        ASSIGNMENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false, name = "when_")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime when;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassInfo classInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !StudentTask.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final StudentTask studentTask = (StudentTask) object;
        if (!Objects.equals(getDescription(), studentTask.getDescription()))
            return false;
        if (!Objects.equals(getType(), studentTask.getType()))
            return false;
        if (!Objects.equals(getWhen(), studentTask.getWhen()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getDescription() != null ? getDescription().hashCode() : 0);
        result = result*37 + (getType() != null ? getType().toString().hashCode() : 0);
        result = result*37 + (getWhen() != null ? getWhen().hashCode() : 0);
        return result;
    }

}
