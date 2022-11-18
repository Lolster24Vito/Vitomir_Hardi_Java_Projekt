/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author vitom
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Director {

    public Director(int id, String name) {
        this.id = id;
        this.name = name;
    }
        private int id;

    public String getName() {
        return name;
    }
    private String name;

    public Director(String name) {
        this.name = name.trim();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
public int hashCode() {
    return name.hashCode();
}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Director other = (Director) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}

