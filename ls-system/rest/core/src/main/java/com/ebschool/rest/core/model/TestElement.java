package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "test")
public class TestElement {

    private Long id;
    private String description;
    private long when;
    private ClassInfoElement classInfo;

    public TestElement() {}

    public TestElement (Test test) {
        setId(test.getId());
        setDescription(test.getDescription());
        setWhen(test.getWhen());
        setClassInfo(new ClassInfoElement(test.getClassInfo()));
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

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public ClassInfoElement getClassInfo() {
        return classInfo;
    }

    @XmlElement(name = "class")
    public void setClassInfo(ClassInfoElement classInfo) {
        this.classInfo = classInfo;
    }
}
