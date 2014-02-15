package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.*;
import com.ebschool.ejb.model.metamodel.Student_;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:31 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public <T extends User> T getById(Long id) {
        TypedQuery<User> query = entityManager.createNamedQuery(User.USER_BY_ID, User.class).setParameter("id", id);
        return (T)query.getSingleResult();
//        return (T)entityManager.find(User.class, id);
    }

    @Override
    public <T extends User> T create(T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public <T extends User> T update(T object) {
        return entityManager.merge(object);
    }

    @Override
    public <T extends User> void delete(T... objects) {
        if (ArrayUtils.isEmpty(objects)){
            throw new IllegalArgumentException("At least one object to delete must be specified");
        }
        for (T object : objects){
            entityManager.remove(object);
        }
    }

    @Override
    public <T extends User> void deleteAll(Class<T> type) {
        if ((Long)entityManager.createQuery("SELECT COUNT(e) FROM " + type.getSimpleName() + " e").getSingleResult() > 0){
            Set<T> entities = getAll(type);
            for (T entity : entities){
                delete(entity);
            }
        }
    }

    @Override
    public <T extends User> Set<T> getAll(Class<T> type, Student.Related... related) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        for (Student.Related relation : related){
            root.fetch(relation.toString(), JoinType.LEFT);
        }
        query.select(root);
        TypedQuery<T> q = entityManager.createQuery(query);
        return new HashSet(q.getResultList());
    }

    @Override
    public <T extends User> List<T> findWithNamedQuery(Class T, String namedQuery, Map<String, Object> parameters) {
        return findWithNamedQuery(T, namedQuery, parameters, 0);
    }

    @Override
    public <T extends User> List<T> findWithNamedQuery(Class T, String namedQuery, Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, T);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public Student getStudentById(Long id, Student.Related... related) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> student = query.from(Student.class);
        for (Student.Related relation : related){
            student.fetch(relation.toString(), JoinType.LEFT);
        }
        ParameterExpression<Long> p = builder.parameter(Long.class);
        query.select(student).where(builder.equal(student.get(Student_.id), p));
        TypedQuery<Student> q = entityManager.createQuery(query);
        q.setParameter(p, id);
        return q.getSingleResult();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public Parent getParentById(Long id) {
        return entityManager.find(Parent.class, id);
    }
}
