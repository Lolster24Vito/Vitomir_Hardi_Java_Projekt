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
    int createMovie(Movie movie) throws SQLException;
    int createActor(String name) throws SQLException;
    int createGenre(String name) throws SQLException;
    int createDirector(String name) throws SQLException;

    void createMovies(List<Movie> articles) throws Exception;
    void createActors(List<Actor> actors) throws Exception;
        void createGenres(List<Genre> genre) throws Exception;
    void createDirectors(List<Director> director) throws Exception;

   // delete
    void deleteActor(int id)throws SQLException;

    //select get
    Set<Actor> getActors() throws SQLException;

    Set<Director> getDirectors() throws SQLException;

    Set<Genre> getGenres() throws SQLException;

    List<Movie> getMovies() throws SQLException;
    
    //Movie properties get
    List<Actor> getActorsInMovie(int movieId) throws SQLException;

    List<Director> getDirectorsInMovie(int movieId) throws SQLException;

    List<Genre> getGenresInMovie(int movieId) throws SQLException;

    List<Movie> getMoviesOfActor(int actorId) throws SQLException;


    //movie set
    void setMovieActors(Movie movie) throws Exception;

    void setMovieActor(String actorName, String movieTitle) throws Exception;

    void setMovieDirectors(Movie movie) throws Exception;

    void setMovieGenres(Movie movie) throws Exception;
    
    

    void addMovieArchive(MovieArchive moviearchive) throws Exception;

    void deleteAllData() throws Exception;

  
    Movie getMovie(int movieId) throws SQLException;

    MovieArchive getMovieData() throws Exception;
    //void updateArticle(int id, Article data) throws Exception;
    // void deleteArticle(int id) throws Exception;
    // Optional<Article> selectArticle(int id) throws Exception;
    // List<Article> selectArticles() throws Exception;

    public void addMoviesToActor(List<Generic2ForeignKeyDB> moviesWithActorAdded) throws SQLException;
}
