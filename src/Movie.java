import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This is a Movie Object Template
 *      Each constructor will be populated from the JSON file.
 *      We don't need setters for everything because we expect the contructor
 *          to do that.
 */
public class Movie {

    // Instance Variables
    private String imdbID;   // uniquely indentifies the movie.
    private String title;
    private int year;
    private String rated;
    private Date dateReleased;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String languages;
    private String poster;
    private int metascore;
    private float imdbRating;
    private String imdbVotes;
    private String typeOfContent;

    Movie(String title, String year, String rated, String released, String runtime, String genre, String director, String actors, String languages, String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String typeOfContent) throws ParseException {
        this.title = title;
        this.year = Integer.parseInt(year);
        this.rated = rated;
        this.genre = genre;
        this.runtime = runtime;
        this.dateReleased = new SimpleDateFormat("dd MMM yyyy").parse(released);
        this.director = director;
        this.actors = actors;
        this.languages = languages;
        this.poster = poster;
        this.metascore = Integer.parseInt(metascore);
        this.imdbRating = Float.parseFloat(imdbRating);
        this.imdbVotes = imdbVotes;
        this.typeOfContent = typeOfContent;
        this.imdbID = imdbID;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public Date getDateReleased() {
        return dateReleased;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getLanguage() {
        return languages;
    }

    public String getPoster() {
        return poster;
    }

    public int getMetascore() {
        return metascore;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getTypeOfContent() {
        return typeOfContent;
    }

    /**
     * Prints out the movie to the console
     * @param media
     */
    public static void printSimpleMovieToConsole(Movie media) {
        // This is independent of the instances and tests a single movie object passed in.
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Title | IMDbID | Year | Rated | Released");
        System.out.printf("%s | %s | %d | %s | %tc", media.title, media.imdbID, media.year, media.rated, media.dateReleased);
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
    }

}


