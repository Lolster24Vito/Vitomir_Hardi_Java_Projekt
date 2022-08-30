/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.MovieArchive;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.StringHelperUtils;
import hr.algebra.utils.StringHtmlTagUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.lang.model.element.NestingKind.LOCAL;

/**
 *
 * @author vitom
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets/moviePosters";
    
    
    
    private static final String STRING_SEPERATOR=",";

    private MovieParser() {

    }

    public static MovieArchive parse() throws IOException, XMLStreamException, ParseException {
    

        

        
        DateFormat dateFormatReleased = new SimpleDateFormat ("dd.MM.yyyy");
         
         
        
        List<Movie> movies = new ArrayList<>();
        Set<Actor> actors = new HashSet<>();
        Set<Director> directors = new HashSet<>();
        Set<Genre> genres = new HashSet<>();



        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);
            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

               // System.out.println("FULLEVENT:" + event.toString());

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        if (tagType.isPresent() && tagType.get().equals((TagType.ITEM))) {
                            movie = new Movie();
                        }
                      //  System.out.println("STARTELEMENT qname:" + qName);

                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent() && movie != null) {

                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            //switch case tag type get
                            if (!data.isEmpty()) {

                                switch (tagType.get()) {
                                    case TITLE:
                                        movie.setTitle(data);
                                        break;
                                    case PUB_DATE:
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                         movie.setPubDate(publishedDate);
                                        //PUB DATE FORMAT
                                        break;
                                    case DESCRIPTION:
                                        movie.setDescription(StringHtmlTagUtil.RemoveHTMLTags(data));
                                        //description remove img href src stuff
                                        break;
                                    case ORIGINAL_NAME:
                                        movie.setOriginalName(data);
                                        break;
                                    case DURATION:
                                        try {
                                            movie.setDuration(Integer.parseInt(data));
                                        } catch (NumberFormatException e) {
                                            System.out.println("Error in duration:" + e.getMessage());
                                        }
                                        break;
                                    case POSTER:
                                        if(startElement!=null&&movie.getPosterPath()==null){
                                        handlePicture(movie,data);
                                        }
                                        break;
                                    case LINK:
                                        movie.setLink(data);
                                        break;
                                    case GUID:
                                        movie.setId(StringHelperUtils.getIdFromLink(data));
                                        break;
                                    case RELEASED:
                                        Date releasedDate = dateFormatReleased.parse(data);

                                        movie.setReleased(releasedDate);
                                        break;
                                    case DIRECTOR:
                                        List<String> directorsNames=StringHelperUtils.seperateStringToList(data, STRING_SEPERATOR);
                                        for (String var : directorsNames) {
                                            movie.addDirector(var);
                                            directors.add(new Director(var));
                                        }

                                        break;
                                    case ACTORS:
                                      List<String> actorsNames=StringHelperUtils.seperateStringToList(data, STRING_SEPERATOR);
                                        for (String var : actorsNames) {
                                            movie.addActor(var);
                                            actors.add(new Actor(var));
                                        }
                                        break;
                                    case GENRE:
                                        List<String> genreNames=StringHelperUtils.seperateStringToList(data, STRING_SEPERATOR);
                                        for (String var : genreNames) {
                                            movie.addGenre(var);
                                            genres.add(new Genre(var));
                                        }
                                        break;
                                    default:
                                        throw new AssertionError(tagType.get().name());
                                }
                            }
                            if (!data.isEmpty()) {
                                System.out.println("data :" + data);

                            }
                        }

                        break;
                    case XMLStreamConstants.CDATA:
                        // System.out.println("CDDATA:" + reader.getElementText());

//CDDATA.add(EXT)
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String endName = endElement.getName().getLocalPart();
                        Optional<TagType> tag = TagType.from(endName);
                        if (tag.isPresent() && tag.get().equals(TagType.ITEM)) {
                            movies.add(movie);
                          //  System.out.println("ENDElement:" + event.asEndElement().toString());

                        }
                        break;
                }
            }
        }
        MovieArchive movieArchive=new MovieArchive(movies, actors, directors, genres);
        return movieArchive;
       // return movies;
    }
    
 private static void handlePicture(Movie movie, String pictureUrl) {

        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
String pictureNameExtension = pictureUrl.substring( pictureUrl.lastIndexOf('/')+1, pictureUrl.length() );
String pictureName = pictureNameExtension.substring(19, pictureNameExtension.lastIndexOf('.'))+EXT;

//String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            movie.setPosterPath(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private enum TagType {

        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIGINAL_NAME("orignaziv"),
        DIRECTOR("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        POSTER("plakat"),
        LINK("link"),
        GUID("guid"),
        RELEASED("pocetak");
        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

}
