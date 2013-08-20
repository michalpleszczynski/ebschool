package com.ebschool.rest.core.model;

import com.ebschool.model.Teacher;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "teacher")
public class TeacherElement extends UserElement {

    private Long id;
    private DetailedInfoElement detailedInfo;
    private Set<Long> classIds;

    public TeacherElement () {
        super();
    }

    public TeacherElement (Teacher teacher) {
        super(teacher);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "detailedInfo")
    public DetailedInfoElement getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(DetailedInfoElement detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    public Set<Long> getClassIds() {
        return classIds;
    }

    @XmlElementWrapper(name = "classes")
    @XmlElement(name = "class")
    public void setClassIds(Set<Long> classIds) {
        this.classIds = classIds;
    }
}
