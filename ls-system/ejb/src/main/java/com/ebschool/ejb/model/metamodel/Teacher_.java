package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.DetailedInfo;
import com.ebschool.ejb.model.Teacher;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Teacher.class)
public abstract class Teacher_ extends User_ {

	public static volatile SetAttribute<Teacher, ClassInfo> classes;
	public static volatile SingularAttribute<Teacher, byte[]> avatar;
	public static volatile SingularAttribute<Teacher, DetailedInfo> detailedInfo;

}

