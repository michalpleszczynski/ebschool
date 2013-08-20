package com.ebschool.security;

/**
 * User: michau
 * Date: 6/3/13
 */
public interface Roles {

    public enum Role {
        TEACHER,
        STUDENT,
        PARENT
    }

    String TEACHER = "teacher";
    String STUDENT = "student";
    String PARENT = "parent";

}
