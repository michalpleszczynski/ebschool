package com.ebschool.service;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.repo.ClassInfoRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(ClassInfoServiceLocal.class)
@Remote(ClassInfoServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClassInfoServiceImpl implements ClassInfoServiceLocal {

    @Inject
    ClassInfoRepository classInfoRepository;

    @Override
    public ClassInfo getById(Long id) {
        return classInfoRepository.getById(id);
    }

    @Override
    public ClassInfo create(ClassInfo classInfo) {
        return classInfoRepository.create(classInfo);
    }

    @Override
    public void delete(ClassInfo... classes) {
        classInfoRepository.delete(classes);
    }

    @Override
    public ClassInfo update(ClassInfo classInfo) {
        return classInfoRepository.update(classInfo);
    }

    @Override
    public Set<ClassInfo> getAll() {
        return classInfoRepository.getAll();
    }

    @Override
    public Set<ClassInfo> getByIds(Collection<Long> ids) {
        Set<ClassInfo> classes = new HashSet<>();
        for (Long id : ids){
            classes.add(classInfoRepository.getById(id));
        }
        return classes;
    }

    @Override
    public ClassInfo addStudent(Student student, ClassInfo classInfo) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        classInfo.getStudents().add(student);
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo removeStudent(Student student, ClassInfo classInfo) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        classInfo.getStudents().remove(student);
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo addTeacher(Teacher teacher, ClassInfo classInfo) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        classInfo.getTeachers().add(teacher);
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo removeTeacher(Teacher teacher, ClassInfo classInfo) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        classInfo.getTeachers().remove(teacher);
        return classInfoRepository.update(classInfo);
    }
}
