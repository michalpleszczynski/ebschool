package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.*;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public <T extends User> T getById(long id) {
        return (T)entityManager.find(User.class, id);
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
    public <T extends User> Set<T> getAll(Class<T> type) {
        return new HashSet<T>(entityManager.createQuery("From " + type.getSimpleName() + " c").getResultList());
    }

    @Override
    public <T extends User> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(T, namedQueryName, parameters, 0);
    }

    @Override
    public <T extends User> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = entityManager.createNamedQuery(namedQueryName, T);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
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
