package com.ebschool.repo;

import com.ebschool.utils.Identifiable;
import org.apache.commons.lang.ArrayUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 2:49 PM
 */
public class GenericRepositoryImpl<T extends Identifiable, PK> implements GenericRepository<T, PK> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;

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
        if ((Long)entityManager.createQuery("select count(*) from " + clazz.getSimpleName()).getSingleResult() > 0){
            entityManager.createQuery("delete * from " + clazz.getSimpleName()).executeUpdate();
        }
    }

    @Override
    public Set<T> getAll() {
        return new HashSet<T>(entityManager.createQuery("from " + clazz.getSimpleName() + " c").getResultList());
    }
}
