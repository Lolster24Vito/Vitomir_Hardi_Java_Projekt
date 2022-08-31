/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.MovieArchive;
import hr.algebra.utils.FileUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author vitom
 */
public class SqlRepository implements Repository {

    private static final String ID_MOVIE = "Id";
    private static final String TITLE = "Title";
    private static final String PUBLISH_DATE = "PublishDate";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_NAME = "OriginalName";
    private static final String DURATION = "Duration";
    private static final String LINK = "Link";
    private static final String POSTER_PATH = "PosterPath";
    private static final String RELEASED_DATE = "ReleasedDate";
    
    private static final String MOVIE_TITLE = "MovieTitle";
    private static final String NAME = "name";
    



    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?,?) }"; 

    private static final String SET_MOVIE_ACTOR = "{ CALL SetMovieActor (?,?) }";
    private static final String SET_MOVIE_DIRECTOR = "{ CALL SetMovieDirector (?,?) }"; 
    private static final String SET_MOVIE_GENRE = "{ CALL SetMovieGenre (?,?) }"; 
    private static final String SELECT_MOVIES = "{ CALL SelectMovies () }";

    private static final String DELETE_ALL_DATA = "{ CALL DeleteAllData () }";


    private static final String POSTER_DIR = "assets/moviePosters";



    
    
    
    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString("@" + TITLE, movie.getTitle());
            stmt.setString("@"+PUBLISH_DATE, movie.getPubDate().toString());
            
            stmt.setString("@" + DESCRIPTION, movie.getDescription());
            stmt.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
            stmt.setInt("@" + DURATION, movie.getDuration());
            stmt.setString("@" + LINK, movie.getLink());
            stmt.setString("@" + POSTER_PATH, movie.getPosterPath());
             java.sql.Date releaseDate=new java.sql.Date(movie.getReleased().getTime());
            stmt.setDate("@"+RELEASED_DATE, releaseDate);
            stmt.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);

            stmt.executeUpdate();
            int insertedId=stmt.getInt("@" + ID_MOVIE);
movie.setId(insertedId);
            return insertedId;

        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            for (Movie movie : movies) {

                 stmt.setString("@" + TITLE, movie.getTitle());
            stmt.setString("@"+PUBLISH_DATE, movie.getPubDate().toString());
            
            stmt.setString("@" + DESCRIPTION, movie.getDescription());
            stmt.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
            stmt.setInt("@" + DURATION, movie.getDuration());
            stmt.setString("@" + LINK, movie.getLink());
            stmt.setString("@" + POSTER_PATH, movie.getPosterPath());
             java.sql.Date releaseDate=new java.sql.Date(movie.getReleased().getTime());
            stmt.setDate("@"+RELEASED_DATE, releaseDate);
            stmt.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);
                stmt.executeUpdate();
             movie.setId(stmt.getInt("@" + ID_MOVIE));
            }
        }
        
        
        
        
    }

    @Override
    public void setMovieActor(String actorName, String movieTitle) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SET_MOVIE_ACTOR)) {
            
            stmt.setString("@" + MOVIE_TITLE, movieTitle);
            stmt.setString("@" + NAME, actorName);
            stmt.executeUpdate();

        }
    }
    @Override
    public void setMovieActors(Movie movie) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SET_MOVIE_ACTOR)) {
            for(Actor actor:movie.getActors()){
            stmt.setString("@" + MOVIE_TITLE, movie.getTitle());
            stmt.setString("@" + NAME, actor.getName());
            stmt.executeUpdate();
            }
        }
    }
    
   
    @Override
    public void setMovieDirectors(Movie movie) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SET_MOVIE_DIRECTOR)) {
            for(Director director:movie.getDirectors()){
            stmt.setString("@" + MOVIE_TITLE, movie.getTitle());
            stmt.setString("@" + NAME, director.getName());
            stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void setMovieGenres(Movie movie) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SET_MOVIE_GENRE)) {
            for(Genre genre:movie.getGenres()){
            stmt.setString("@" + MOVIE_TITLE, movie.getTitle());
            stmt.setString("@" + NAME, genre.getName());
            stmt.executeUpdate();
            }
        }
    }    
    

    @Override
    public void addMovieArchive(MovieArchive moviearchive) throws Exception {
        createMovies(moviearchive.getMovies());
        for(Movie movie:moviearchive.getMovies()){
        setMovieActors(movie);
            setMovieDirectors(movie);
            setMovieGenres(movie);
        }
        
    }

    @Override
    public void deleteAllData() throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL_DATA)) {
            stmt.executeUpdate();
            }
        
        FileUtils.deleteFilesFromDirectory(POSTER_DIR);
        
    }

    @Override
    public MovieArchive getMovieData() throws Exception {
        MovieArchive movieArchive=new MovieArchive();
        List<Movie> movies=new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) 
            
            {
                Movie movie=new Movie();
                movie.setId(rs.getInt(ID_MOVIE));
                movie.setTitle(rs.getString(TITLE));
                movie.setDescription(rs.getString(DESCRIPTION));
                movie.setOriginalName(rs.getString(ORIGINAL_NAME));
                movie.setDuration(rs.getInt(DURATION));
                movie.setPosterPath(rs.getString(POSTER_PATH));
                movie.setLink(rs.getString(LINK));
                
                
                movies.add(movie);
                
              /*  movies.add(new Movie{
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(LINK),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Article.DATE_FORMATTER)));*/
            }
        }
        //SAME THING BUT WITH ACTORS GENRE AND DIRECTORS
        
        
        movieArchive.setMovies(movies);
        //TODO napravi
        
        return movieArchive;
    }
    
        
    }




