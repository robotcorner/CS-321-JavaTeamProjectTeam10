import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieJsonOperator {
    private Gson gson = new Gson();
    private String movie_filename;
    private String user_filename;

    MovieJsonOperator() {}

        MovieJsonOperator(String movie_filename, String user_filename) {
        this.movie_filename = movie_filename;
        this.user_filename  = user_filename;
    }

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
     * @return list of all movies
     */
    public ArrayList<Movie> GetAllMovies() {
        try {
            Movie[] movies = gson.fromJson(new FileReader(movie_filename), Movie[].class);
            return new ArrayList<Movie>(List.of(movies));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Dump the entire contents of a users.json into a list
     * @return list of all users
     */
    public ArrayList<User> GetAllUsers() {
        try {
            User[] users = gson.fromJson(new FileReader(user_filename), User[].class);
            return new ArrayList<User>(List.of(users));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Get the contents of a config.json
     * @return config
     */
    public Config GetConfig(String filename) {
        try {
            return gson.fromJson(new FileReader(filename), Config.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Writes data back to users.json
     * Should be called from loginManager only!
     * @param u list of ALL users
     * @return true if no error occurs
     */
    public boolean SaveAllUsers(ArrayList<User> u) {
        try {
            FileWriter writer = new FileWriter(user_filename, false);
            writer.write(gson.toJson(u));
            writer.close();
            return true;
        } catch(IOException e) {
            System.out.println(e);
            return false;
        }
    }
}
