package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.StudentTask;
import com.ebschool.rest.core.utils.adapters.LocalDateTimeAdapter;
import org.joda.time.LocalDateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "test")
public class StudentTaskElement {

    @XmlEnum
    public enum TaskType {
        TEST,
        ASSIGNMENT
    }

    private Long id;
    private String description;
    private LocalDateTime when;
    private TaskType type;
    private ClassInfoElement classInfo;

    public StudentTaskElement() {}

    public StudentTaskElement(StudentTask studentTask) {
        this.id = studentTask.getId();
        this.description = studentTask.getDescription();
        setWhen(studentTask.getWhen());
        this.type = TaskType.valueOf(studentTask.getType().name());
        setClassInfo(new ClassInfoElement(studentTask.getClassInfo()));
    }

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

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public ClassInfoElement getClassInfo() {
        return classInfo;
    }

    @XmlElement(name = "class")
    public void setClassInfo(ClassInfoElement classInfo) {
        this.classInfo = classInfo;
    }
}
