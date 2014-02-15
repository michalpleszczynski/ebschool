package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.Parent;
import com.ebschool.ejb.model.Student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parent.class)
public abstract class Parent_ extends User_ {

	public static volatile SetAttribute<Parent, Student> childrenAccounts;

}

