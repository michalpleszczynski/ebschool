package com.ebschool.ejb.repo;

import com.ebschool.ejb.utils.Identifiable;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 2:49 PM
 */
@Stateless
@LocalBean
//@RolesAllowed({"RegisteredUsers"})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GenericRepositoryImpl<T extends Identifiable, PK> implements GenericRepository<T, PK> {

    @PersistenceContext(unitName = "dev")
    protected EntityManager entityManager;

    private Class<T> clazz;

    // needed for DI
    public GenericRepositoryImpl(){

    }

    public GenericRepositoryImpl(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T getById(PK id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public T create(T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public T update(T object) {
        return entityManager.merge(object);
    }

    @Override
    public void delete(T... objects) {
        if (ArrayUtils.isEmpty(objects)){
            throw new IllegalArgumentException("At least one object to delete must be specified");
        }
        for (T object : objects){
            entityManager.remove(object);
        }
    }

    @Override
    public void deleteAll(){
        if ((Long)entityManager.createQuery("SELECT COUNT(e) FROM " + clazz.getSimpleName() + " e").getSingleResult() > 0){
            Set<T> entities = getAll();
            for (T entity : entities){
                delete(entity);
            }
//            entityManager.createQuery("DELETE FROM " + clazz.getSimpleName()).executeUpdate();
        }
    }

    @Override
    public Set<T> getAll() {
        return new HashSet<T>(entityManager.createQuery("From " + clazz.getSimpleName() + " c").getResultList());
    }

    @Override
    public <T> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters){
        return findWithNamedQuery(T, namedQueryName, parameters, 0);
    }

    @Override
    public <T> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters, int resultLimit){
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = entityManager.createNamedQuery(namedQueryName, T);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}
