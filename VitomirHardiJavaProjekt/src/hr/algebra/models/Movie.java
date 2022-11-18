/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author vitom
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"title","pubDate", "description", "originalName", "directorsString","actorsString","duration","genresString","posterPath","link","released"})

public class Movie {

    public static String dateFormatReleasedPattern = "dd.MM.yyyy";

    private int id;
    @XmlElement(name = "title")
    private String title;
    @XmlJavaTypeAdapter(PublishDateAdapter.class)
    @XmlElement
    private LocalDateTime pubDate;
    @XmlElement
    private String description;
    @XmlElement(name = "orignaziv")
    private String originalName;

    @XmlElement(name = "redatelj")
    private String directorsString;
    private Set<Director> directors = new HashSet<>();
    
    @XmlElement(name = "glumci")
    private String actorsString;
    private Set<Actor> actors = new HashSet<>();
    
    @XmlElement(name = "trajanje")
    private int duration;
    @XmlElement(name = "zanr")
     private String genresString;
    private Set<Genre> genres = new HashSet<>();
    @XmlElement(name = "plakat")
    private String posterPath;
            @XmlElement(name = "link")
    private String link;
        @XmlJavaTypeAdapter(ReleasedDateAdapter.class)
        @XmlElement(name = "pocetak")
    private Date released;

    public Movie(int id, String title, LocalDateTime pubDate, String description, String originalName, int duration, String posterPath, String link, Date released) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalName = originalName;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.released = released;
    }


    public Movie() {
    }

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie(String title, List<Actor> addedMovieActors, List<Director> addedMovieDirectors, List<Genre> addedMovieGenre) {
       this.title=title;
        addActors(addedMovieActors);
        addDirectors(addedMovieDirectors);
        addGenres(addedMovieGenre);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
        
    }

    public void addDirector(String name) {
        Director director = new Director(name);
        directors.add(director);
        stringDirectorsRefresh();
    }
    public void addDirector(Director director) {
        directors.add(director);
        stringDirectorsRefresh();
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(String name) {
        Actor actor = new Actor(name);
        actors.add(actor);
        stringActorsRefresh();
    }

    public void addActor(Actor actor) {
        actors.add(actor);
                stringActorsRefresh();

    }
    public void addActors(List<Actor> actorsList){
        this.actors.addAll(actorsList);
                stringActorsRefresh();

    }
    
    public void addDirectors(List<Director> directorsList) {
        this.directors.addAll(directorsList);
                stringDirectorsRefresh();

    }
    
    public void addGenres(List<Genre> genresList){
        this.genres.addAll(genresList);
                stringGenresRefresh();
    }
    

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
                        stringGenresRefresh();
    }

    public void addGenre(String name) {
        Genre genre = new Genre(name);
        genres.add(genre);
                stringGenresRefresh();

    }
     public void addGenre(Genre genre) {
        genres.add(genre);
                stringGenresRefresh();

    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String poster) {
        this.posterPath = poster;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return title;
//return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    public GenericDbEntity toGenericDbEntity(){
        return new GenericDbEntity(id, title);
    }
    
    public void stringDirectorsRefresh(){
         directorsString=directors.stream().map(d->d.getName()).collect(Collectors.joining(","));
    }
    public void stringActorsRefresh(){
                 actorsString=actors.stream().map(d->d.getName()).collect(Collectors.joining(","));

    }
    public void stringGenresRefresh(){
     genresString=genres.stream().map(d->d.getName()).collect(Collectors.joining(","));

    }

    

}
