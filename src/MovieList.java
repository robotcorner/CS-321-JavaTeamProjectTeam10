import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/**
 * In order to implement the MovieList in a data efficient manner, I will use the imdDb ID's as a way to track what is saved.
 */
public class MovieList {

    // Used by an Instance
    // TODO: make private later when we can right getter / setters for necessary functions
    public ArrayList<Movie> mediaList;

    MovieList() {
        this.mediaList = new ArrayList<Movie>();
    }

    MovieList(ArrayList<Movie> medias) {
        this.mediaList = medias;
    }

    public void add(Movie movie) {
        mediaList.add(movie);
    }




    // Create member function to allow maninpulation of a media list
}



