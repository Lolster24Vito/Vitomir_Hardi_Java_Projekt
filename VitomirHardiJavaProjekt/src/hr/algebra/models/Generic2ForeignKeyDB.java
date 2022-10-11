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
public class Generic2ForeignKeyDB {
    public int movieId;
    public int foreignKeyId;
    public String name;

    public Generic2ForeignKeyDB(int MovieId, int ForeignKeyId, String Name) {
        this.movieId = MovieId;
        this.foreignKeyId = ForeignKeyId;
        this.name = Name;
    }

    
    
}
