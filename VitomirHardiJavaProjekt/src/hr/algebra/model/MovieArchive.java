/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.List;
import java.util.Set;

/**
 *
 * @author vitom
 */
public class MovieArchive {
    private List<Movie> movies;
    private Set<Actor> actors;


    private Set<Director> directors;
    private Set<Genre> genres;

    public MovieArchive(List<Movie> movies, Set<Actor> actors, Set<Director> directors, Set<Genre> genres) {
        this.movies = movies;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }
    public List<Movie> getMovies() {
        return movies;
    }


}
