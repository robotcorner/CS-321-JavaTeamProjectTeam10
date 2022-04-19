/*
Course:         CS-321
Project:        Build an app with GUI in swing for managing a book or movie watch later personal list.
Requirements:
    Use version control.
    Have 3-4 members.

Authors:    Stephen Stammen, Daniel Mills, Caleb Bagwell, Braden Willingham
 */

import java.io.*;
import java.util.ArrayList;

class Config {
    public String moviePath;
    public String userPath;
}

class Main {
    static String configPath = "data/config.json";

    public static void main(String[] args) {
        // Try to create if config file does not exist
        try {
            if (new File(configPath).createNewFile()) {
                FileOutputStream writer = new FileOutputStream(configPath);
                writer.write(
                        ("{\"moviePath\":\"data/SampleMovieFile.json\", \"userPath\":\"data/users.json\"}").getBytes()
                );
                writer.close();
            }
        } catch(IOException e) {
            System.out.println(e);
            return;
        }
        if(! new File("data/config.json").exists()) {
            System.out.println("No config.json. Exiting...");
            return;
        }
        // config is hard coded. Everything else depends upon it
        Config configuration = new MovieJsonOperator().GetConfig("data/config.json");

        // Exit if movie file does not exist
        if(! new File(configuration.moviePath).exists()) {
            System.out.println("No SampleMovieFile.json. Exiting...");
            return;
        }

        // Create user file if it does not exist
        try {
            if (new File(configuration.userPath).createNewFile()) {
                FileOutputStream writer = new FileOutputStream(configuration.userPath);
                writer.write(("[]").getBytes());
                writer.close();
            }
        } catch(IOException e) {
            System.out.println(e);
            return;
        }

        // Initialize dependencies for our app
        MovieJsonOperator operator = new MovieJsonOperator(configuration.moviePath, configuration.userPath);
        ArrayList<Movie> movieList = operator.GetAllMovies();
        ArrayList<User> userList = operator.GetAllUsers();
        LoginManager loginManager = new LoginManager(userList, movieList, operator);

        // Create Movie List Object for the Movie List View to display
        MovieManager movieManager = new MovieManager(movieList);
        MovieManagerView.Initialize(loginManager, movieManager);
        MovieManagerView.main();

        // Run Tests
        // Tests.testMovie(); // Tests that movies get loaded and prints to console
        // Tests.loginFlow(); // Tests the basic login flow
    }
}