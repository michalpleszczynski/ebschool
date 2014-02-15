package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Level;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Level.class)
public abstract class Level_ {

	public static volatile SetAttribute<Level, ClassInfo> classes;
	public static volatile SingularAttribute<Level, Long> id;
	public static volatile SingularAttribute<Level, String> name;

}

