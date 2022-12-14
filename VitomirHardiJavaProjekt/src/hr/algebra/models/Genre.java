/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.Objects;

/**
 *
 * @author vitom
 */
public class Genre {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
        private int id;
    private String name;

    public Genre(String name) {
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return  name;
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
        final Genre other = (Genre) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
