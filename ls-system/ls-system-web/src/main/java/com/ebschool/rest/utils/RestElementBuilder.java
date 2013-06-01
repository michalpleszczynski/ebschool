package com.ebschool.rest.utils;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Parent;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.rest.model.ClassInfoElement;
import com.ebschool.rest.model.ParentElement;
import com.ebschool.rest.model.StudentElement;
import com.ebschool.rest.model.TeacherElement;

import java.util.Set;

/**
 * User: michau
 * Date: 5/28/13
 */
public interface RestElementBuilder {

    public StudentElement buildStudentElement(Student student);

    public TeacherElement buildTeacherElement(Teacher teacher);

    public ParentElement buildParentElement(Parent parent);

    public ClassInfoElement buildClassInfoElement(ClassInfo classInfo);

    public <S, T> Set<S> buildElementSet(Set<T> entities, Class<S> type);

}
