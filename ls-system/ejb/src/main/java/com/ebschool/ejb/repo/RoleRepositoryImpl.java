package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.User;
import com.ebschool.ejb.security.Roles;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * User: michau
 * Date: 6/12/13
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
// TODO: maybe solve it in some better way
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public void assignRole(User user, Roles.Role role) {
        Query q = em.createNativeQuery("INSERT INTO roles (role, user_id) VALUES (:role, :id)")
                .setParameter("role", role.name().toLowerCase())
                .setParameter("id", user.getId());
        q.executeUpdate();
    }

    @Override
    public void unassignRole(User user, Roles.Role role) {
        Query q = em.createNativeQuery("DELETE FROM roles WHERE user_id = :id AND role = :role")
                .setParameter("id", user.getId())
                .setParameter("role", role.name().toLowerCase());
        q.executeUpdate();
    }

}
