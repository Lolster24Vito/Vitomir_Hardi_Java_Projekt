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
public class Actor {


    private int id;
    private String name;

    public Actor(String name) {
        this.name = name.trim();
    }

    public Actor() {
    }

    public Actor(int id, String name) {
        this.id = id;
        this.name = name;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
        final Actor other = (Actor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
        @Override
    public String toString() {
        return  getName();
    }
}
