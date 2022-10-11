/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Generic2ForeignKeyDB;
import hr.algebra.models.Genre;
import hr.algebra.models.Movie;
import hr.algebra.models.MovieArchive;
import hr.algebra.utils.FileUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import javax.sql.DataSource;
import hr.algebra.dal.MovieRepository;
import hr.algebra.models.GenericDbEntity;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitom
 */
public class MovieSqlRepository implements MovieRepository {

    private static final String ID_MOVIE = "Id";
    private static final String ID_GENERIC = "Id";

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

    private static final String MOVIE_ID = "MovieId";
    private static final String ACTOR_ID = "ActorId";
    private static final String DIRECTOR_ID = "DirectorId";
    private static final String GENRE_ID = "GenreId";

    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?,?) }";

    private static final String SET_MOVIE_ACTOR = "{ CALL SetMovieActor (?,?) }";
    private static final String SET_MOVIE_DIRECTOR = "{ CALL SetMovieDirector (?,?) }";
    private static final String SET_MOVIE_GENRE = "{ CALL SetMovieGenre (?,?) }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies () }";
    private static final String SELECT_ACTORS_IN_MOVIE = "{ CALL SelectActorsInMovie (?) }";
    private static final String SELECT_MOVIES_FROM_ACTOR = "{ CALL SelectMoviesFromActor (?) }";
    private static final String SELECT_DIRECTORS_IN_MOVIE = "{ CALL SelectDirectorsInMovie (?) }";
    private static final String SELECT_GENRES_IN_MOVIE = "{ CALL SelectGenresInMovie (?) }";

    private static final String SELECT_MOVIE_ACTOR = "{ CALL SelectMovieActor () }";
    private static final String SELECT_MOVIE_DIRECTOR = "{ CALL SelectMovieDirector () }";
    private static final String SELECT_MOVIE_GENRE = "{ CALL SelectMovieGenre () }";
    private static final String GET_ACTOR_NAME = "{ CALL GetActorName(?) }";

    private static final String SELECT_ACTORS = "{ CALL SelectActors () }";
    private static final String SELECT_DIRECTORS = "{ CALL SelectDirectors () }";
    private static final String SELECT_GENRES = "{ CALL SelectGenres () }";

    private static final String DELETE_ALL_DATA = "{ CALL DeleteAllData () }";

    private static final String POSTER_DIR = "assets/moviePosters";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString("@" + TITLE, movie.getTitle());
            stmt.setString("@" + PUBLISH_DATE, movie.getPubDate().toString());

            stmt.setString("@" + DESCRIPTION, movie.getDescription());
            stmt.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
            stmt.setInt("@" + DURATION, movie.getDuration());
            stmt.setString("@" + LINK, movie.getLink());
            stmt.setString("@" + POSTER_PATH, movie.getPosterPath());
            java.sql.Date releaseDate = new java.sql.Date(movie.getReleased().getTime());
            stmt.setDate("@" + RELEASED_DATE, releaseDate);
            stmt.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);

            stmt.executeUpdate();
            int insertedId = stmt.getInt("@" + ID_MOVIE);
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
                stmt.setString("@" + PUBLISH_DATE, movie.getPubDate().toString());

                stmt.setString("@" + DESCRIPTION, movie.getDescription());
                stmt.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
                stmt.setInt("@" + DURATION, movie.getDuration());
                stmt.setString("@" + LINK, movie.getLink());
                stmt.setString("@" + POSTER_PATH, movie.getPosterPath());
                java.sql.Date releaseDate = new java.sql.Date(movie.getReleased().getTime());
                stmt.setDate("@" + RELEASED_DATE, releaseDate);
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
            for (Actor actor : movie.getActors()) {
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
            for (Director director : movie.getDirectors()) {
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
            for (Genre genre : movie.getGenres()) {
                stmt.setString("@" + MOVIE_TITLE, movie.getTitle());
                stmt.setString("@" + NAME, genre.getName());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void addMovieArchive(MovieArchive moviearchive) throws Exception {
        createMovies(moviearchive.getMovies());
        for (Movie movie : moviearchive.getMovies()) {
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
        MovieArchive movieArchive = new MovieArchive();
        DataSource dataSource = DataSourceSingleton.getInstance();

        List<Movie> movies = getMovies();

        movieArchive.setActors(getActors());
        

        movieArchive.setDirectors(getDirectors());

        movieArchive.setGenres(getGenres());

        for (Movie movie : movies) {
            List<Actor> actorsInMovie = getActorsInMovie(movie.getId());
            movie.addActors(actorsInMovie);

            List<Director> directorsInMovie = getDirectorsInMovie(movie.getId());
            movie.addDirectors(directorsInMovie);

            List<Genre> genresInMovie = getGenresInMovie(movie.getId());
            movie.addGenres(genresInMovie);

        }

        movieArchive.setMovies(movies);

        return movieArchive;
    }

    @Override
    public Set<Actor> getActors() throws SQLException {
        List<GenericDbEntity> actorsGeneric = getGenericDatabase(SELECT_ACTORS);
        Set<Actor> actorSet = new HashSet<>();
        actorsGeneric.forEach(it -> actorSet.add(new Actor(it.getId(), it.getName())));
        return actorSet;
    }

    @Override
    public Set<Director> getDirectors() throws SQLException {
        List<GenericDbEntity> actorsGeneric = getGenericDatabase(SELECT_DIRECTORS);
        Set<Director> directorSet = new HashSet<>();
        actorsGeneric.forEach(it -> directorSet.add(new Director(it.getId(), it.getName())));
        return directorSet;
    }

    @Override
    public Set<Genre> getGenres() throws SQLException {
        List<GenericDbEntity> genresGeneric = getGenericDatabase(SELECT_GENRES);
        Set<Genre> genreSet = new HashSet<>();
        genresGeneric.forEach(it -> genreSet.add(new Genre(it.getId(), it.getName())));
        return genreSet;
    }

    @Override
    public List<Movie> getMovies() throws SQLException {
        //get movies
                DataSource dataSource = DataSourceSingleton.getInstance();
        List<Movie> movies = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt(ID_MOVIE));
                movie.setTitle(rs.getString(TITLE));
                movie.setDescription(rs.getString(DESCRIPTION));
                movie.setOriginalName(rs.getString(ORIGINAL_NAME));
                movie.setDuration(rs.getInt(DURATION));
                movie.setPosterPath(rs.getString(POSTER_PATH));
                movie.setLink(rs.getString(LINK));
                movie.setReleased(rs.getDate(RELEASED_DATE));
                //publish date is a nvarchar in the SQL
                movie.setPubDate(LocalDateTime.parse(rs.getString(PUBLISH_DATE)));

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
        return movies;
    }
//OLD (String procedureName,String foreignKeyColumnName, DataSource dataSource) throws SQLException
    //NEW (String procedureName, String foreignKeyColumnName,String parameterName,int parameterValue,String returnColumnName)
    
    @Override
    public List<Actor> getActorsInMovie(int movieId) throws SQLException {
        List<Actor> actorsInMovie = new ArrayList<>();
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_ACTORS_IN_MOVIE, ACTOR_ID,MOVIE_ID, movieId, "Name");
        objects.forEach(object -> actorsInMovie.add(new Actor(object.foreignKeyId, object.name)));
        return actorsInMovie;
    }

    @Override
    public List<Director> getDirectorsInMovie(int movieId) throws SQLException {
        List<Director> directorsInMovie = new ArrayList<>();
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_DIRECTORS_IN_MOVIE, DIRECTOR_ID,MOVIE_ID, movieId,"Name");
        objects.forEach(object -> directorsInMovie.add(new Director(object.foreignKeyId, object.name)));
        return directorsInMovie;
    }

    @Override
    public List<Genre> getGenresInMovie(int movieId) throws SQLException {
        List<Genre> genresInMovies = new ArrayList<>();
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_GENRES_IN_MOVIE, GENRE_ID,MOVIE_ID,movieId,"Name");
        objects.forEach(object -> genresInMovies.add(new Genre(object.foreignKeyId, object.name)));
        return genresInMovies;
    }

  

    private Map<Integer, String> getGenericDatabase(String procedureName, DataSource dataSource) throws SQLException {

        Map<Integer, String> items = new HashMap<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(procedureName);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                items.put(rs.getInt(ID_GENERIC), rs.getString(NAME));

            }
        }
        return items;

    }

    private List<GenericDbEntity> getGenericDatabase(String procedureName) throws SQLException {

        DataSource dataSource = DataSourceSingleton.getInstance();
        List<GenericDbEntity> items = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(procedureName);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                items.add(new GenericDbEntity(rs.getInt(ID_GENERIC), rs.getString(NAME)));

            }
        }
        return items;

    }

   

    public List<Generic2ForeignKeyDB> getGeneric2ForeignKeys(String procedureName, String foreignKeyColumnName,String parameterName,int parameterValue,String returnColumnName) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Generic2ForeignKeyDB> objectInMovies = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(procedureName)) {
            stmt.setInt("@"+parameterName, parameterValue);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

               // Generic2ForeignKeyDB item = new Generic2ForeignKeyDB(rs.getInt(MOVIE_ID), rs.getInt(foreignKeyColumnName), rs.getString("Name"));
               Generic2ForeignKeyDB item = new Generic2ForeignKeyDB(rs.getInt(MOVIE_ID), rs.getInt(foreignKeyColumnName), rs.getString(returnColumnName));

                objectInMovies.add(item);

            }
        }
        return objectInMovies;
    }

    @Override
    public List<GenericDbEntity> getMoviesOfActor(int actorId) throws SQLException {
        //SelectMoviesFromActor
         List<GenericDbEntity> moviesOfActor = new ArrayList<>();
         //(SELECT_MOVIES_FROM_ACTOR, ACTOR_ID,ACTOR_ID,);
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_MOVIES_FROM_ACTOR, ACTOR_ID, ACTOR_ID, actorId, TITLE);
        //objects.forEach(object -> moviesOfActor.add(new Movie(object.movieId, object.name)));
         objects.forEach(object -> moviesOfActor.add(new GenericDbEntity(object.movieId, object.name)));

        return moviesOfActor;
    }

    @Override
    public Movie getMovie(int movieId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
