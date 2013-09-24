package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.model.StudentTask;
import com.ebschool.ejb.model.Teacher;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:08 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClassInfoRepositoryImpl extends GenericRepositoryImpl<ClassInfo, Long> implements ClassInfoRepository{

    @Inject
    StudentTaskRepository studentTaskRepository;

    public ClassInfoRepositoryImpl() {
        super(ClassInfo.class);
    }

    @Override
    public void delete(ClassInfo... classes){
        if (ArrayUtils.isEmpty(classes)){
            throw new IllegalArgumentException("At least one object to delete must be specified");
        }
        for (ClassInfo classInfo : classes) {
            Set<StudentTask> tasks = classInfo.getStudentTasks();
            classInfo.setStudentTasks(null);
            studentTaskRepository.delete(tasks.toArray(new StudentTask[tasks.size()]));
            entityManager.remove(classInfo);
        }
    }

    @Override
    public void deleteAll() {
        Set<ClassInfo> classes = getAll();
        delete(classes.toArray(new ClassInfo[classes.size()]));
    }

    @Override
    public List<ClassInfo> getClassesByTeacher(Teacher teacher) {
        Query q = entityManager.createQuery(
                "SELECT ci FROM " + ClassInfo.class.getSimpleName()
                + " AS ci where :teacher MEMBER OF ci.teachers");
        q.setParameter("teacher", teacher);
        return (List<ClassInfo>) q.getResultList();
    }

    @Override
    public List<ClassInfo> getClassesByLevel(Level level) {
        Query q = entityManager.createQuery(
                "SELECT ci FROM " + ClassInfo.class.getSimpleName()
                + " AS ci WHERE ci.level = :level");
        q.setParameter("level", level);
        return (List<ClassInfo>) q.getResultList();
    }
}
