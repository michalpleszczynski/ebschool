package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.StudentTask;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface StudentTaskService {

    public StudentTask getById(Long id);
    public StudentTask getByIdWithRelated(Long id, StudentTask.Related... related);
    public Set<StudentTask> getAll();
    public Set<StudentTask> getAllWithRelated(StudentTask.Related... related);
    public List<StudentTask> getTestsByClass(ClassInfo classInfo);

    public StudentTask create(StudentTask studentTask);
    public StudentTask update(StudentTask studentTask);
    public void delete(StudentTask... studentTasks);

}
