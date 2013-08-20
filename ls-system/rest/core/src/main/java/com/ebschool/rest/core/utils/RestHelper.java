package com.ebschool.rest.core.utils;

import com.sun.jersey.api.NotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String convertLongDate(long lDate){
        return convertLongDate(lDate, "EE - HH:mm");
    }

    public static String convertLongDate(long lDate, String format){
        Date date = new Date(lDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
