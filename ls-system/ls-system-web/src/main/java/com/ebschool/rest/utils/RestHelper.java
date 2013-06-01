package com.ebschool.rest.utils;

import com.sun.jersey.api.NotFoundException;

/**
 * User: michau
 * Date: 5/23/13
 */
public class RestHelper {

    // TODO: change this to jersey exceptions
    public static <T> T throw404IfNull(T object) {
        if (object == null) {
            throw new NotFoundException("Resource not found: " + object);
        }
        return object;
    }

    public static <T> T throw404IfNull(T object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }
        return object;
    }

}
