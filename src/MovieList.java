import java.util.ArrayList;

/**
 * In order to implement the MovieList in a data efficient manner, I will use the imdDb ID's as a way to track what is saved.
 */
public class MovieList {

    // Used by an Instance
    // TODO: make private later when we can right getter / setters for necessary functions
    public ArrayList<String> mediaList;

    MovieList() {
        this.mediaList = new ArrayList<String>();
    }

    MovieList(ArrayList<String> medias) {
        this.mediaList = medias;
    }

    public void add(String idMovie1) {
    }

    // Create member function to allow maninpulation of a media list
}
