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
public class Generic2ForeignKeyDB {
    public int MovieId;
    public int ForeignKeyId;
    public String Name;

    public Generic2ForeignKeyDB(int MovieId, int ForeignKeyId, String Name) {
        this.MovieId = MovieId;
        this.ForeignKeyId = ForeignKeyId;
        this.Name = Name;
    }

    
    
}
