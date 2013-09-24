    package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.rest.core.utils.RestHelper;
import com.ebschool.rest.core.utils.adapters.LocalTimeAdapter;
import org.joda.time.LocalTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "class_info")
public class ClassInfoElement {

    @XmlEnum
    public enum Day {
        MONDAY(1),
        TUESDAY(2),
        WEDNESDAY(3),
        THURSDAY(4),
        FRIDAY(5),
        SATURDAY(6),
        SUNDAY(7);

        private int index;

        private Day(int index){
            this.index = index;
        }

        public int getIndex(){
            return index;
        }
    }

    private Long id;
    private String where;
    private String description;
    private Day day;
    private LocalTime time;
    private LevelElement level;
    private SemesterElement semester;

    private Set<Long> students;
    private Set<Long> teachers;
    private Set<Long> tests;

    public ClassInfoElement() {}

    public ClassInfoElement (ClassInfo classInfo) {
        this.id = classInfo.getId();
        this.description = classInfo.getDescription();
        setSemester(new SemesterElement(classInfo.getSemester()));
        setTime(classInfo.getWhen().getTime());
        this.day = Day.valueOf(classInfo.getWhen().getDay().name());
        this.where = classInfo.getWhere();
        setLevel(new LevelElement(classInfo.getLevel()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getStudents() {
        return students;
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    public void setStudents(Set<Long> students) {
        this.students = students;
    }

    public Set<Long> getTeachers() {
        return teachers;
    }

    @XmlElementWrapper(name = "teachers")
    @XmlElement(name = "teacher")
    public void setTeachers(Set<Long> teachers) {
        this.teachers = teachers;
    }

    public Set<Long> getTests() {
        return tests;
    }

    @XmlElementWrapper(name = "tests")
    @XmlElement(name = "test")
    public void setTests(Set<Long> tests) {
        this.tests = tests;
    }

    public LevelElement getLevel() {
        return level;
    }

    public void setLevel(LevelElement level) {
        this.level = level;
    }

    public SemesterElement getSemester() {
        return semester;
    }

    public void setSemester(SemesterElement semester) {
        this.semester = semester;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
