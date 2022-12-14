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
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import hr.algebra.dal.MovieRepository;
import hr.algebra.models.GenericDbEntity;
import java.util.Optional;

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
    private static final String CREATE_ACTORMOVIE = "{ CALL CreateActorMovie (?,?) }";
    private static final String CREATE_ACTOR = "{ CALL CreateActor (?,?) }";
    private static final String CREATE_DIRECTOR = "{ CALL CreateDirector (?,?) }";
    private static final String CREATE_GENRE = "{ CALL CreateGenre (?,?) }";

//CREATE_DIRECTOR
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?,?,?) }";

//CreateActorMovie
    private static final String SET_MOVIE_ACTOR = "{ CALL SetMovieActor (?,?) }";
    private static final String SET_MOVIE_DIRECTOR = "{ CALL SetMovieDirector (?,?) }";
    private static final String SET_MOVIE_GENRE = "{ CALL SetMovieGenre (?,?) }";

    private static final String SELECT_ACTORS = "{ CALL SelectActors () }";
    private static final String SELECT_DIRECTORS = "{ CALL SelectDirectors () }";
    private static final String SELECT_GENRES = "{ CALL SelectGenres () }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies () }";

    private static final String SELECT_MOVIE = "{ CALL SelectMovie (?) }";
    private static final String SELECT_ACTORS_IN_MOVIE = "{ CALL SelectActorsInMovie (?) }";
    private static final String SELECT_MOVIES_FROM_ACTOR = "{ CALL SelectMoviesFromActor (?) }";
    private static final String SELECT_DIRECTORS_IN_MOVIE = "{ CALL SelectDirectorsInMovie (?) }";
    private static final String SELECT_GENRES_IN_MOVIE = "{ CALL SelectGenresInMovie (?) }";

    private static final String DELETE_ACTOR = "{ CALL DeleteActor(?) }";
    private static final String DELETE_DIRECTOR = "{ CALL DeleteDirector(?) }";
    private static final String DELETE_GENRE = "{ CALL DeleteGenre(?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie(?) }";

    private static final String DELETE_ALL_DATA = "{ CALL DeleteAllData () }";

    private static final String POSTER_DIR = "assets/moviePosters";

    @Override
    public int createMovie(Movie movie) throws SQLException {
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
        if (movie.getActors().isEmpty()) {
            return;
        }
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
        if (movie.getDirectors().isEmpty()) {
            return;
        }
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
        if (movie.getGenres().isEmpty()) {
            return;
        }
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

//        movieArchive.setActors(getActors());
        //  movieArchive.setDirectors(getDirectors());
        //movieArchive.setGenres(getGenres());
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
    public MovieArchive getAllMData() throws Exception {
        MovieArchive movieArchive = new MovieArchive();
        DataSource dataSource = DataSourceSingleton.getInstance();

        List<Movie> movies = getMovies();
        movieArchive.setMovies(movies);

        movieArchive.setActors(getActors());

        movieArchive.setDirectors(getDirectors());

        movieArchive.setGenres(getGenres());

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
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_ACTORS_IN_MOVIE, ACTOR_ID, MOVIE_ID, movieId, "Name");
        objects.forEach(object -> actorsInMovie.add(new Actor(object.foreignKeyId, object.name)));
        return actorsInMovie;
    }

    @Override
    public List<Director> getDirectorsInMovie(int movieId) throws SQLException {
        List<Director> directorsInMovie = new ArrayList<>();
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_DIRECTORS_IN_MOVIE, DIRECTOR_ID, MOVIE_ID, movieId, "Name");
        objects.forEach(object -> directorsInMovie.add(new Director(object.foreignKeyId, object.name)));
        return directorsInMovie;
    }

    @Override
    public List<Genre> getGenresInMovie(int movieId) throws SQLException {
        List<Genre> genresInMovies = new ArrayList<>();
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_GENRES_IN_MOVIE, GENRE_ID, MOVIE_ID, movieId, "Name");
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

    public List<Generic2ForeignKeyDB> getGeneric2ForeignKeys(String procedureName, String foreignKeyColumnName, String parameterName, int parameterValue, String returnColumnName) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Generic2ForeignKeyDB> objectInMovies = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(procedureName)) {
            stmt.setInt("@" + parameterName, parameterValue);
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
    public List<Movie> getMoviesOfActor(int actorId) throws SQLException {
        //SelectMoviesFromActor
        //OLD List<GenericDbEntity> moviesOfActor = new ArrayList<>();
        //NEW
        List<Movie> moviesOfActor = new ArrayList<>();
        //(SELECT_MOVIES_FROM_ACTOR, ACTOR_ID,ACTOR_ID,);
        List<Generic2ForeignKeyDB> objects = getGeneric2ForeignKeys(SELECT_MOVIES_FROM_ACTOR, ACTOR_ID, ACTOR_ID, actorId, TITLE);
        //objects.forEach(object -> moviesOfActor.add(new Movie(object.movieId, object.name)));
        objects.forEach(object -> moviesOfActor.add(new Movie(object.movieId, object.name)));

        return moviesOfActor;
    }

    @Override
    public Optional<Movie> getMovie(int movieId) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {
            stmt.setInt("@" + ID_GENERIC, movieId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
                    return Optional.of(movie);
                } else {
                    return Optional.empty();
                }

            }

        }
    }

    @Override
    public void addMoviesToActor(List<Generic2ForeignKeyDB> moviesActors) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTORMOVIE)) {
            for (Generic2ForeignKeyDB ma : moviesActors) {

                stmt.setInt("@" + MOVIE_ID, ma.movieId);
                stmt.setInt("@" + ACTOR_ID, ma.foreignKeyId);

                stmt.executeUpdate();
            }
        }
    }

    //return int change TODO: VITO
    @Override
    public int createActor(String name) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {

            stmt.setString("@" + NAME, name);
            stmt.registerOutParameter("@" + ID_GENERIC, Types.INTEGER);

            stmt.executeUpdate();
            int insertedId = stmt.getInt("@" + ID_GENERIC);
            return insertedId;

        }

    }

    @Override
    public int createGenre(String name) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {

            stmt.setString("@" + NAME, name);
            stmt.registerOutParameter("@" + ID_GENERIC, Types.INTEGER);

            stmt.executeUpdate();
            int insertedId = stmt.getInt("@" + ID_GENERIC);
            return insertedId;

        }
    }

    @Override
    public int createDirector(String name) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {

            stmt.setString("@" + NAME, name);
            stmt.registerOutParameter("@" + ID_GENERIC, Types.INTEGER);

            stmt.executeUpdate();
            int insertedId = stmt.getInt("@" + ID_GENERIC);
            return insertedId;

        }
    }

    @Override
    public void deleteActor(int Id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {

            stmt.setInt("@" + ID_GENERIC, Id);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteMovie(int Id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt("@" + ID_GENERIC, Id);

            stmt.executeUpdate();

        }
    }

    @Override
    public void updateMovie(Movie updatedMovie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

            stmt.setInt("@" + ID_GENERIC, updatedMovie.getId());
            stmt.setString("@" + TITLE, updatedMovie.getTitle());
            stmt.setString("@" + PUBLISH_DATE, updatedMovie.getPubDate().toString());

            stmt.setString("@" + DESCRIPTION, updatedMovie.getDescription());
            stmt.setString("@" + ORIGINAL_NAME, updatedMovie.getOriginalName());
            stmt.setInt("@" + DURATION, updatedMovie.getDuration());
            stmt.setString("@" + LINK, updatedMovie.getLink());
            stmt.setString("@" + POSTER_PATH, updatedMovie.getPosterPath());
            java.sql.Date releaseDate = new java.sql.Date(updatedMovie.getReleased().getTime());
            stmt.setDate("@" + RELEASED_DATE, releaseDate);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {

            stmt.setInt("@" + ID_GENERIC, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteGenre(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_GENRE)) {

            stmt.setInt("@" + ID_GENERIC, id);

            stmt.executeUpdate();

        }
    }

}
