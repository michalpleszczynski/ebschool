package com.ebschool.ejb.repo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 2:44 PM
 */
public interface GenericRepository<T, PK> {

    public T getById(PK id);
    public T getByIdWithRelated(Long id, Enum... related);
    public T create(T object);
    public T update(T object);
    public void delete(T... objects);
    public void deleteAll();
    public Set<T> getAll();
    public Set<T> getAllWithRelated(Enum... related);

    public <T> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters);

    public <T> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters, int resultLimit);

}
