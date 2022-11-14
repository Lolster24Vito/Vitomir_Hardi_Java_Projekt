/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author vitom
 */
public class MovieArchive {
    private List<Movie> movies;
    private Set<Actor> actors;

    public MovieArchive() {
    }


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
       public Set<Director> getDirectors() {
        return directors;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
        public void setDirectors(Set<Director> directors) {
        this.directors=directors;
    }
            public void setGenres(Set<Genre> genres) {
        this.genres=genres;
    }
    
    public void addActors(Collection<Actor> actors){
    this.actors.addAll(actors);
    }
    public void addActor(Actor actor){
        this.actors.add(actor);
    }

    public void addDirectors(Collection<Director> directors) {
        this.directors.addAll(directors);
    }

    public void addGenres(Collection<Genre> genres) {
        this.genres.addAll(genres);
    }
    public Optional<Movie> getMovie(int movieId){
        Optional<Movie> movie=movies.stream().filter(m->m.getId()==movieId).findFirst();
        return movie;
        /*
        Optional<Person> matchingObject = objects.stream().
    filter(p -> p.email().equals("testemail")).
    findFirst();
        */
    }
    


}
