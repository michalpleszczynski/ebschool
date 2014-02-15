package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.StudentTask;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grade.class)
public abstract class Grade_ {

	public static volatile SingularAttribute<Grade, Long> id;
	public static volatile SingularAttribute<Grade, Byte> weight;
	public static volatile SingularAttribute<Grade, Student> student;
	public static volatile SingularAttribute<Grade, StudentTask> studentTask;
	public static volatile SingularAttribute<Grade, Byte> percentage;
	public static volatile SingularAttribute<Grade, String> comment;

}

