package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.model.Student;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:29 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class LevelRepositoryImpl extends GenericRepositoryImpl<Level, Long> implements LevelRepository {

    @Inject
    ClassInfoRepository classInfoRepository;

    @Inject
    UserRepository userRepository;

    @Override
    public void delete(Level... levels) {
        if (ArrayUtils.isEmpty(levels)){
            throw new IllegalArgumentException("At least one object to delete must be specified");
        }
        for (Level level : levels) {
            if (level == null)
                continue;
            // set null in students
            TypedQuery<Student> query = entityManager.createNamedQuery(Student.STUDENTS_BY_LEVEL, Student.class).setParameter("level", level);
            List<Student> students = query.getResultList();
            for (Student student : students) {
                student.setLevel(null);
            }

            // delete classes
            Set<ClassInfo> classes = level.getClasses();
            classInfoRepository.delete(classes.toArray(new ClassInfo[classes.size()]));

            // remove level
            entityManager.remove(level);
        }
    }

    @Override
    public void deleteAll() {
        Set<Level> levels = getAll();
        delete(levels.toArray(new Level[levels.size()]));
    }

    public LevelRepositoryImpl() {
        super(Level.class);
    }
}
