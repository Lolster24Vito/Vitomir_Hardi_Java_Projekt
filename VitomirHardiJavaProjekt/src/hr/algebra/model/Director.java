/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.Objects;

/**
 *
 * @author vitom
 */
public class Director {
        private int id;

    public String getName() {
        return name;
    }
    private String name;

    public Director(String name) {
        this.name = name;
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