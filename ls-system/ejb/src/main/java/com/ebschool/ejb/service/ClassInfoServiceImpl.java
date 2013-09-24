package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.StudentTask;
import com.ebschool.ejb.model.Teacher;
import com.ebschool.ejb.repo.ClassInfoRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(ClassInfoService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClassInfoServiceImpl implements ClassInfoService {

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
    public ClassInfo addStudents(ClassInfo classInfo, Student... students) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        if (classInfo.getStudents() == null){
            classInfo.setStudents(new HashSet(Arrays.asList(students)));
        } else {
            classInfo.getStudents().addAll(Arrays.asList(students));
        }
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo removeStudents(ClassInfo classInfo, Student... students) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        if (classInfo.getStudents() == null){
            return classInfo;
        }
        classInfo.getStudents().removeAll(Arrays.asList(students));
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo addTeachers(ClassInfo classInfo, Teacher... teachers) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        if (classInfo.getTeachers() == null){
            classInfo.setTeachers(new HashSet(Arrays.asList(teachers)));
        } else {
            classInfo.getTeachers().addAll(Arrays.asList(teachers));
        }
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo removeTeachers(ClassInfo classInfo, Teacher... teachers) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        if (classInfo.getTeachers() == null){
            return classInfo;
        }
        classInfo.getTeachers().removeAll(Arrays.asList(teachers));
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo addStudentTasks(ClassInfo classInfo, StudentTask... tasks) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        if (classInfo.getStudentTasks() == null){
            classInfo.setStudentTasks(new HashSet(Arrays.asList(tasks)));
        } else {
            classInfo.getStudentTasks().addAll(Arrays.asList(tasks));
        }
        return classInfoRepository.update(classInfo);
    }

    @Override
    public ClassInfo removeStudentTasks(ClassInfo classInfo, StudentTask... tasks) {
        classInfo = classInfoRepository.getById(classInfo.getId());
        if (classInfo.getStudentTasks() == null){
            return classInfo;
        }
        classInfo.getStudentTasks().removeAll(Arrays.asList(tasks));
        return classInfoRepository.update(classInfo);
    }
}
