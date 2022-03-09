import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {

    // Instance Variables
    private String imdbID;   // uniquely indentifies the movie.
    private String title;
    private int year;
    private String rated;
    private Date released;
    private int runtime;
    private String genre;
    private String director;
    private String actors;
    private String language;
    private String poster;
    private int metascore;
    private float imdbRating;
    private String imdbVotes;
    private String typeOfContent;

    Movie(String title, String year, String rated, String released, String runtime, String genre, String director, String actors, String language, String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String typeOfContent) throws ParseException {
        this.imdbID = imdbID;
        this.title = title;
        this.year = Integer.parseInt(year);
        this.rated = rated;
        this.released = new SimpleDateFormat("dd MMM yyyy").parse(released);
    }

    public static void printMovieToConsole(Movie media) {
        // This is independent of the instances and tests a single movie object passed in.
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Title | IMDbID | Year | Rated | Released");
        System.out.printf("%s | %s | %d | %s | %tc", media.title, media.imdbID, media.year, media.rated, media.released);
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
    }
}


