package com.ebschool.rest.core.utils.adapters;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * User: michau
 * Date: 9/24/13
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return new LocalDate(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }

}
