package com.ebschool.ejb.exception;

/**
 * User: michau
 * Date: 9/7/13
 */
public class DuplicatedUserException extends Exception {

    public DuplicatedUserException(String message){
        super(message);
    }

}
