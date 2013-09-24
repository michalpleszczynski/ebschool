package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.StudentTask;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

import static com.ebschool.ejb.utils.QueryParameter.with;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:29 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class StudentTaskRepositoryImpl extends GenericRepositoryImpl<StudentTask, Long> implements StudentTaskRepository {

    @Override
    public void delete(StudentTask... studentTasks){
        if (ArrayUtils.isEmpty(studentTasks)){
            throw new IllegalArgumentException("At least one object to delete must be specified");
        }
        for (StudentTask task : studentTasks) {
            TypedQuery<Grade> query = entityManager.createNamedQuery(Grade.GRADES_BY_STUDENT_TASK, Grade.class).setParameter("studentTask", task);
            List<Grade> grades = query.getResultList();
            for (Grade grade : grades) {
                grade.setStudentTask(null);
            }
        }
        for (StudentTask task : studentTasks){
            entityManager.remove(task);
        }
    }

    @Override
    public void deleteAll() {
        Set<StudentTask> tasks = getAll();
        delete(tasks.toArray(new StudentTask[tasks.size()]));
    }

    public StudentTaskRepositoryImpl() {
        super(StudentTask.class);
    }
}
