package com.ebschool.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.ebschool.model.BasicUser;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<BasicUser> memberEventSrc;

    private BasicUser newBasicUser;

    @Produces
    @Named
    public BasicUser getNewBasicUser() {
        return newBasicUser;
    }

    public void register() throws Exception {
        log.info("Registering " + newBasicUser.getName());
        em.persist(newBasicUser);
        memberEventSrc.fire(newBasicUser);
        initNewMember();
    }

    @PostConstruct
    public void initNewMember() {
        newBasicUser = new BasicUser();
    }
}
