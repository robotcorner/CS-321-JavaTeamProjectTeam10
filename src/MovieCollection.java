import java.util.ArrayList;

/**
 * This holds a movie collection that the user has
 * The id ArrayList is from the json file
 * The Movie ArrayList is built from the id ArrayList
 * (use the Movie arraylist for everything)
 */
public class MovieCollection {
    private ArrayList<String> ids = new ArrayList<String>(); // array of movie IDs
    private transient ArrayList<Movie> movies = new ArrayList<Movie>(); // array of movies
    private String name;

    MovieCollection(String name) {
        this.name = name;
    }

    public void addMovie(Movie m) {
        ids.add(m.getImdbID());
        movies.add(m);
    }

    public void removeMovie(Movie m) {
        if(ids.contains(m.getImdbID()))
            ids.remove(m.getImdbID());
        if(movies.contains(m))
            movies.remove(m);
    }

    // This function allows us to store IDs in JSON and get movielist in memory
    public void buildMovieCollectionFromIds(ArrayList<Movie> allMovies) {
        movies = new ArrayList<Movie>();
        for (String id: ids) {
            for (Movie m: allMovies) {
                if(id.equals(m.getImdbID())) {
                    movies.add(m);
                }
            }
        }
    }

    // Get a movies list
    public ArrayList<Movie> getMovieList() {
        return movies;
    }


    public String getName() {
        return name;
    }
}
