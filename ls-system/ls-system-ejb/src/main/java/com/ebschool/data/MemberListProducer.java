package com.ebschool.data;

import com.ebschool.model.BasicUser;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class MemberListProducer {
    @Inject
    private EntityManager em;

    private List<BasicUser> basicUsers;

    // @Named provides access the return value via the EL variable name "basicUsers" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<BasicUser> getBasicUsers() {
        return basicUsers;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final BasicUser basicUser) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BasicUser> criteria = cb.createQuery(BasicUser.class);
        Root<BasicUser> member = criteria.from(BasicUser.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        basicUsers = em.createQuery(criteria).getResultList();
    }
}
