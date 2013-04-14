package com.ebschool.repo;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Level;
import com.ebschool.model.Teacher;

import javax.persistence.Query;
import java.util.List;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:08 PM
 */
public class ClassInfoRepositoryImpl extends GenericRepositoryImpl<ClassInfo, Long> implements ClassInfoRepository{

    public ClassInfoRepositoryImpl(Class<ClassInfo> clazz) {
        super(ClassInfo.class);
    }

    @Override
    public List<ClassInfo> getClassesByTeacher(Teacher teacher) {
        Query q = entityManager.createQuery("select * from " + ClassInfo.class.getSimpleName()
                + " ci where :teacher in ci.teachers");
        q.setParameter("teacher", teacher);
        return (List<ClassInfo>) q.getResultList();
    }

    @Override
    public List<ClassInfo> getClassesByLevel(Level level) {
        Query q = entityManager.createQuery("select * from " + ClassInfo.class.getSimpleName()
                + " ci where ci.level = :level");
        q.setParameter("level", level);
        return (List<ClassInfo>) q.getResultList();
    }
}
