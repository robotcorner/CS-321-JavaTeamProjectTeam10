import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieJsonOperator implements IJsonOperator {
    private String filename;

    MovieJsonOperator(String filename) {
        this.filename = filename;
    }
    //public String Get(int id);

    /**
     * Get movie by imdbID
     * @param imdbID
     * @return movie
     */
    public Movie Get(String imdbID) {
        Scanner sc = new Scanner(filename);
        sc.nextLine(); // first line is empty ...
        Movie movie;
        while(sc.hasNextLine()) {
            movie = gson.fromJson(sc.nextLine(), Movie.class);
            if(movie.getImdbID() == imdbID) {
                Movie.printSimpleMovieToConsole(movie); // debug
                return movie;
            }
        }

        return null; // not found
    }

    /**
     * Dump the entire contents of a json file into a list
     * @return array of movies
     */
    public Movie[] GetAll() {
        try {
            return gson.fromJson(new FileReader(filename), Movie[].class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    // GSON is a serializer, not a JSON parser...
    public String Search(String key, String value) {
        return "";
    }

    // This should probably be delegated to the movie class similar to program 5
    public String Sort(String key) {
        return "";
    }
}
