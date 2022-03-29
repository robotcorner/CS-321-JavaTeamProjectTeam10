import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;


/**
 * This is a Movie Object Template
 *      Each constructor will be populated from the JSON file.
 *      We don't need setters for everything because we expect the contructor
 *          to do that.
 */
public class Movie implements Comparable<Movie>, Comparator<Movie> {

    // Instance Variables
    private String imdbID;   // uniquely identifies the movie.
    private String Title;
    private int Year;
    private String Rated;
    private String Released;
    private Date dateReleased;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Languages;
    private String Poster;
    private String Metascore;
    private float imdbRating;
    private String imdbVotes;
    private String Type;

    Movie(String title, String year, String rated, String released, String runtime, String genre, String director, String actors, String languages, String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String typeOfContent) throws ParseException {
        this.Title = title;
        this.Year = Integer.parseInt(year);
        this.Rated = rated;
        this.Genre = genre;
        this.Runtime = runtime;
        this.dateReleased = new SimpleDateFormat("dd MMM yyyy").parse(released);
        this.Director = director;
        this.Actors = actors;
        this.Languages = languages;
        this.Poster = poster;
        this.Metascore = metascore;
        this.imdbRating = Float.parseFloat(imdbRating);
        this.imdbVotes = imdbVotes;
        this.Type = typeOfContent;
        this.imdbID = imdbID;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public int getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public Date getDateReleased() {
        return dateReleased;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    public String getLanguage() {
        return Languages;
    }

    public String getPoster() {
        return Poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getTypeOfContent() {
        return Type;
    }

    /**
     * Prints out the movie to the console
     * @param media
     */
    public static void printSimpleMovieToConsole(Movie media) {
        // This is independent of the instances and tests a single movie object passed in.
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Title | IMDbID | Year | Rated | Released");
        System.out.printf("%s | %s | %d | %s | %tc", media.Title, media.imdbID, media.Year, media.Rated, media.dateReleased);
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
    }


    /*
    Compare by movie title
     */
    public int compareTo(Movie o) {
        int result = o.getTitle().toLowerCase(Locale.ROOT).compareTo(getTitle().toLowerCase(Locale.ROOT));
        if(result != 0) {
            return -1 * result;
        }

        return result;
    }


    @Override
    public int compare(Movie o1, Movie o2) {
        return 0;
    }
}

