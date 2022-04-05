import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * In order to implement the MovieList in a data efficient manner, I will use the imdDb ID's as a way to track what is saved.
 */
public class MovieManager {

    // Used by an Instance
    // TODO: make private later when we can right getter / setters for necessary functions
    public ArrayList<Movie> mediaList;

    MovieManager() {
        this.mediaList = new ArrayList<Movie>();
    }

    MovieManager(ArrayList<Movie> medias) {
        this.mediaList = medias;
    }

    public void add(Movie movie) {
        mediaList.add(movie);
    }

    public Movie get(String imdbID) {
        for (Movie movie: mediaList)
            if(movie.getImdbID().equals(imdbID))
                return movie;
        return null;
    }

    // this returns reference, perhaps should return copy
    public ArrayList<Movie> getMediaList() {
        return mediaList;
    }

    /**
     * Searches a few interesting fields and returns a list of all matches
     * @param term
     * @return ArrayList<Movie> result
     */
    public ArrayList<Movie> search(String term) {
        if(term.isEmpty())
            return mediaList;

        ArrayList<Movie> result = new ArrayList<Movie>();
        for (Movie m : mediaList) {
            if(m.getTitle().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
            else if(m.getDirector().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
            else if(m.getActors().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
            else if(m.getGenre().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
        }

        return result;
    }


    public ArrayList<Movie> sortByName() {
        // Sort mediaList like Project5 and Slides 4a3
        //return Collections.sort(mediaList, Movie.compareByName());
        return mediaList;
    }

    /**
     * Sort the given list (perhaps the one returned by Search)
     * I don't think this really needs its own function, its one line
     * Collections.sort(list, list.sortfunction)
     * @param m
     * @return sorted m
     */
    public ArrayList<Movie> sortByName(ArrayList<Movie> m) {
        return m;
    }

    /**
     *  A function that displays every movie title
     */

    public void displayList()
    {
        for (Movie movie: mediaList)
            System.out.println(movie.getTitle());
    }


    /**
     *
     * @return how many movies are in the list
     */
    public int size() {
        return mediaList.size();
    }


    /**
     * Sorts media list by title alphabetically
     */
    public void sortbyName(){
        Collections.sort(mediaList, Movie.sortByName());
    }

    /**
     * Sorts media list by year released
     */
    public void sortbyYear(){
        Collections.sort(mediaList, Movie.sortByYear());
    }

    /**
     * Sees if a movie title is in the media list
     */
    public boolean find(String title){
        if(mediaList.contains(title))
            return true;
        else
            return false;
    }




}

    // Create member function to allow maninpulation of a media list
