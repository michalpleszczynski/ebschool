package com.ebschool.repo;

import java.util.Set;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 2:44 PM
 */
public interface GenericRepository<T, PK> {

    public T getById(PK id);
    public T create(T object);
    public T update(T object);
    public void delete(T... objects);
    public void deleteAll();
    public Set<T> getAll();

}
