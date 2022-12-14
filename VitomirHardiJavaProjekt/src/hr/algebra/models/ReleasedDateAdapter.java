/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author vitom
 */
public class ReleasedDateAdapter extends XmlAdapter<String, Date>{


    @Override
    public Date unmarshal(String v) throws Exception {
        DateFormat dateFormatReleased = new SimpleDateFormat ( Movie.dateFormatReleasedPattern);
                return dateFormatReleased.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
                DateFormat dateFormatReleased = new SimpleDateFormat ( Movie.dateFormatReleasedPattern);
                 return dateFormatReleased.format(v);
    }
    
}
