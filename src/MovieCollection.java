import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This holds a movie collection that the user has
 * The id ArrayList is from the json file
 * The Movie ArrayList is built from the id ArrayList
 * (use the Movie arraylist for everything)
 */
public class MovieCollection extends Searchable {
    private ArrayList<String> ids = new ArrayList<String>(); // array of movie IDs
    private transient ArrayList<Movie> mediaList = new ArrayList<Movie>(); // array of movies
    private String name;

    MovieCollection(String name) {
        this.name = name;
    }

    MovieCollection(ArrayList<Movie> m) {
        mediaList = m;
    }

    public void addMovie(Movie m) {
        if(!ids.contains(m))
            ids.add(m.getImdbID());
        if(!mediaList.contains(m))
            mediaList.add(m);
    }

    public void removeMovie(Movie m) {
        if(ids.contains(m.getImdbID()))
            ids.remove(m.getImdbID());
        if(mediaList.contains(m))
            mediaList.remove(m);
    }

    public boolean hasID(String imdbID) {
        if(ids.contains(imdbID))
            return true;
        return false;
    }

    // This function allows us to store IDs in JSON and get movielist in memory
    public void buildMovieCollectionFromIds(ArrayList<Movie> allMovies) {
        mediaList = new ArrayList<Movie>();
        for (String id: ids) {
            for (Movie m: allMovies) {
                if(id.equals(m.getImdbID())) {
                    mediaList.add(m);
                }
            }
        }
    }

    public ArrayList<Movie> search(String term) {
        return super.search(mediaList, term);
    }

    public ArrayList<Movie> searchTitle(String term) {
        return super.searchTitle(mediaList, term);
    }

    public ArrayList<Movie> searchGenre(String term) {
        return super.searchGenre(mediaList, term);
    }

    public ArrayList<Movie> searchCast(String term) {
        return super.searchCast(mediaList, term);
    }

    public ArrayList<Movie> searchDirector(String term) {
        return super.searchDirector(mediaList, term);
    }

    // Get a movies list
    public ArrayList<Movie> getMovieList() {
        return mediaList;
    }


    public String getName() {
        return name;
    }
    public void setName(String n) { name = n; }
}
