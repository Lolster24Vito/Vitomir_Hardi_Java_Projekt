/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models.viewModels;

import hr.algebra.models.GenericDbEntity;
import hr.algebra.models.Movie;
import java.util.List;

/**
 *
 * @author vitom
 */
public class EntityInMovies {
    
    private final List<GenericDbEntity> entities;
    private final List<Movie> moviesWithEntity;

   public EntityInMovies(List<GenericDbEntity> entities, List<Movie> moviesWithEntity) {
        this.entities = entities;
        this.moviesWithEntity = moviesWithEntity;
    }
    public List<GenericDbEntity> getEntities() {
        return entities;
    }

    public List<Movie> getMoviesWithEntity() {
        return moviesWithEntity;
    }


}
