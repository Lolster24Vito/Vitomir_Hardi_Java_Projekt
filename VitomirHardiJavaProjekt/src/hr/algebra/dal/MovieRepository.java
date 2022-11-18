/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Generic2ForeignKeyDB;
import hr.algebra.models.GenericDbEntity;
import hr.algebra.models.Genre;
import hr.algebra.models.Movie;
import hr.algebra.models.MovieArchive;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author vitom
 */
public interface MovieRepository {

    //Create
    int createMovie(Movie movie) throws Exception;
    int createActor(String name) throws Exception;
    int createGenre(String name) throws Exception;
    int createDirector(String name) throws Exception;

    void createMovies(List<Movie> articles) throws Exception;

   // delete
    void deleteActor(int id)throws Exception;
        void deleteDirector(int id)throws Exception;
    void deleteGenre(int id)throws Exception;

void deleteMovie(int Id) throws Exception ;
    //select get
    Set<Actor> getActors() throws Exception;

    Set<Director> getDirectors() throws Exception;

    Set<Genre> getGenres() throws Exception;

    List<Movie> getMovies() throws Exception;
    
    //Movie properties get
    List<Actor> getActorsInMovie(int movieId) throws Exception;

    List<Director> getDirectorsInMovie(int movieId) throws Exception;

    List<Genre> getGenresInMovie(int movieId) throws Exception;

    List<Movie> getMoviesOfActor(int actorId) throws Exception;


    //movie set
    void setMovieActors(Movie movie) throws Exception;

    void setMovieActor(String actorName, String movieTitle) throws Exception;

    void setMovieDirectors(Movie movie) throws Exception;

    void setMovieGenres(Movie movie) throws Exception;
    
    

    void addMovieArchive(MovieArchive moviearchive) throws Exception;

    void deleteAllData() throws Exception;

  
    Optional<Movie> getMovie(int movieId) throws Exception;

    MovieArchive getMovieData() throws Exception;
        MovieArchive getAllMData() throws Exception;

    //void updateArticle(int id, Article data) throws Exception;
    // void deleteArticle(int id) throws Exception;
    // Optional<Article> selectArticle(int id) throws Exception;
    // List<Article> selectArticles() throws Exception;

    public void addMoviesToActor(List<Generic2ForeignKeyDB> moviesWithActorAdded) throws SQLException;

    public void updateMovie(Movie updatedMovie) throws Exception;
}
