package com.ebschool.rest.core.utils.adapters;

import org.joda.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * User: michau
 * Date: 9/24/13
 */
public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        return new LocalTime(v);
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return v.toString();
    }

}
