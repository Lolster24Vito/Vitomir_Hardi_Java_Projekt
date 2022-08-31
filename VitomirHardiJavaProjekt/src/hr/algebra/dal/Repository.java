/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Movie;
import hr.algebra.model.MovieArchive;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author vitom
 */
public interface Repository {
    int createMovie(Movie movie) throws Exception;
    void createMovies(List<Movie> articles) throws Exception;    
    void addMovieArchive(MovieArchive moviearchive) throws Exception;
    void setMovieActors(Movie movie) throws Exception ;
    void setMovieActor(String actorName, String movieTitle) throws Exception;
    void setMovieDirectors(Movie movie) throws Exception ;
    void setMovieGenres(Movie movie) throws Exception ;
    void deleteAllData() throws Exception;
    MovieArchive getMovieData() throws Exception;
    //void updateArticle(int id, Article data) throws Exception;
   // void deleteArticle(int id) throws Exception;
   // Optional<Article> selectArticle(int id) throws Exception;
   // List<Article> selectArticles() throws Exception;
}
