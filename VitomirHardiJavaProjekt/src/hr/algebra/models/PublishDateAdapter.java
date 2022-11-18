/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author vitom
 */
class PublishDateAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v, DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        ZonedDateTime zdt = v.atZone(ZoneId.of("GMT"));
        return zdt.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

}
