import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.nextLine();
            Movie movie;
            while (sc.hasNextLine()) {
                movie = gson.fromJson(sc.nextLine(), Movie.class);
                System.out.println("in while loop");
                if (movie.getImdbID() == imdbID) {
                    Movie.printSimpleMovieToConsole(movie); // debug
                    return movie;
                }
            }
            return null; // not found
        } catch(FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Dump the entire contents of a json file into a list
     * @return array of movies
     */
    public ArrayList<Movie> GetAll() {
        try {
            Movie[] movies = gson.fromJson(new FileReader(filename), Movie[].class);
            return new ArrayList<Movie>(List.of(movies));
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
