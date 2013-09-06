package com.ebschool.rest.core.utils;

import com.ebschool.ejb.model.*;
import com.ebschool.rest.core.model.*;

import java.util.Set;

/**
 * User: michau
 * Date: 5/28/13
 */
public interface RestElementBuilder {

    public StudentElement buildStudentElement(Student student);

    public TeacherElement buildTeacherElement(Teacher teacher);

    public ParentElement buildParentElement(Parent parent);

    public UserElement buildUserElement(User user);

    public ClassInfoElement buildClassInfoElement(ClassInfo classInfo);

    public <S, T> Set<S> buildElementSet(Set<T> entities, Class<S> type);

}
