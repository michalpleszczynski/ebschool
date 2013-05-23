package com.ebschool.rest.model;

import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.rest.ResponseEntityBean;
import com.ebschool.service.TeacherServiceLocal;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
public class TeacherElement implements ResponseEntityBean<Teacher>{

    @EJB
    TeacherServiceLocal teacherServiceLocal;

    private Long id;
    private byte[] avatar;
    private DetailedInfoElement detailedInfo;
    private Set<ClassInfoElement> classes;

    //TODO: find some more generic approach
    public static Set<TeacherElement> buildSet(Set<Teacher> teachers){
        Set<TeacherElement> returnSet = new HashSet<TeacherElement>();
        for (Teacher teacher : teachers){
            TeacherElement teacherElement = new TeacherElement();
            teacherElement.init(teacher);
            returnSet.add(teacherElement);
        }
        return returnSet;
    }

    @Override
    public void init(Teacher teacher) {
        teacher = teacherServiceLocal.getTeacherById(teacher.getId());
        setId(teacher.getId());
        setAvatar(teacher.getAvatar());
        DetailedInfoElement detailedInfoElement = new DetailedInfoElement();
        detailedInfoElement.init(teacher.getDetailedInfo());
        setDetailedInfo(detailedInfoElement);
        setClasses(ClassInfoElement.buildSet(teacher.getClasses()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public DetailedInfoElement getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(DetailedInfoElement detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    public Set<ClassInfoElement> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassInfoElement> classes) {
        this.classes = classes;
    }
}
