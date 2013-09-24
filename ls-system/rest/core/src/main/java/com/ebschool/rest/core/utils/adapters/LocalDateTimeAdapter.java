package com.ebschool.rest.core.utils.adapters;

import org.joda.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * User: michau
 * Date: 9/24/13
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>{

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return new LocalDateTime(v);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }

}
