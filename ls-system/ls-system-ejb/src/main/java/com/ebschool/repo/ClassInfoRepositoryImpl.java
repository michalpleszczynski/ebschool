package com.ebschool.repo;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Level;
import com.ebschool.model.Teacher;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.util.List;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:08 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClassInfoRepositoryImpl extends GenericRepositoryImpl<ClassInfo, Long> implements ClassInfoRepository{

    public ClassInfoRepositoryImpl() {
        super(ClassInfo.class);
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
