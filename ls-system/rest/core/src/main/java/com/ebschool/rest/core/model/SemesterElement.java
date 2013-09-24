package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.time.Semester;
import com.ebschool.rest.core.utils.adapters.LocalDateAdapter;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * User: michau
 * Date: 9/24/13
 */
@XmlRootElement(name = "semester")
public class SemesterElement {

    private Long id;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;

    public SemesterElement(){}

    public SemesterElement(Semester semester) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.beginDate = semester.getBeginDate();
        this.endDate = semester.getEndDate();
    }

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

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
