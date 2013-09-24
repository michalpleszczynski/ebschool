package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 3:23 PM
 */
public interface UserRepository {

    public <T extends User> T getById(Long id);
    public <T extends User> T create(T object);
    public <T extends User> T update(T object);
    public <T extends User> void delete(T... objects);
    public <T extends User> void deleteAll(Class<T> type);
    public <T extends User> Set<T> getAll(Class<T> type);

    public <T extends User> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters);

    public <T extends User> List<T> findWithNamedQuery(Class T, String namedQueryName, Map<String, Object> parameters, int resultLimit);

    public Student getStudentById(Long id);

    public Teacher getTeacherById(Long id);

    public Parent getParentById(Long id);

}
