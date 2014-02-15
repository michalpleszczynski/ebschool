package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.*;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ extends User_ {

	public static volatile SetAttribute<Student, ClassInfo> classes;
	public static volatile SingularAttribute<Student, Level> level;
	public static volatile SingularAttribute<Student, Parent> parent;
	public static volatile SetAttribute<Student, Grade> grades;
	public static volatile SingularAttribute<Student, DetailedInfo> detailedInfo;

}

