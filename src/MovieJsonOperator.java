import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieJsonOperator implements IJsonOperator {
    private Gson gson = new Gson();
    private String movie_filename;
    private String user_filename;

    MovieJsonOperator(String movie_filename, String user_filename) {
        this.movie_filename = movie_filename;
        this.user_filename  = user_filename;
    }
    //public String Get(int id);

    /**
     * Get movie by imdbID
     * @param imdbID
     * @return movie
     */
    public Movie Get(String imdbID) {
        try {
            Scanner sc = new Scanner(new File(movie_filename));
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
    public ArrayList<Movie> GetAllMovies() {
        try {
            Movie[] movies = gson.fromJson(new FileReader(movie_filename), Movie[].class);
            return new ArrayList<Movie>(List.of(movies));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public ArrayList<User> GetAllUsers() {
        try {
            User[] users = gson.fromJson(new FileReader(user_filename), User[].class);
            return new ArrayList<User>(List.of(users));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void SaveAllUsers(ArrayList<User> u) {
        try {
            FileWriter writer = new FileWriter(user_filename, false);
            writer.write(gson.toJson(u));
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
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
