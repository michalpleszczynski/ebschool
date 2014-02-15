package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.User;
import com.ebschool.ejb.model.User.UserType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> phoneNumber;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, UserType> type;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> password;

}

