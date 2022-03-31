/*
Course:         CS-321
Project:        Build an app with GUI in swing for managing a book or movie watch later personal list.
Requirements:
    Use version control.
    Have 3-4 members.

Authors:    Stephen Stammen, Daniel Mills, Caleb Bagwell, Braden Willingham
 */

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The HelloWorldApp class implements an application that
 * simply prints "Hello World!" to standard output.
 * This type of comment is considered documentation.
 * This part will be removed and is just for an example.
 */
class HelloWorldApp {

    public static void main(String[] args) throws ParseException {
        // Exit if movie file does not exist
        if(! new File("data/SampleMovieFile.json").exists()) {
            System.out.println("No SampleMovieFile.json. Exiting...");
            return;
        }

        // Create user file if it does not exist
        try {
            if (new File("data/users.json").createNewFile()) {
                FileOutputStream writer = new FileOutputStream("data/users.json");
                writer.write(("[]").getBytes());
                writer.close();
            }
        } catch(IOException e) {
            System.out.println(e);
            return;
        }

        // Initialize dependencies for our app
        MovieJsonOperator operator = new MovieJsonOperator("data/SampleMovieFile.json", "data/users.json");
        ArrayList<Movie> movieListFromJson = operator.GetAllMovies();
        ArrayList<User> userList = operator.GetAllUsers();
        LoginManager loginManager = new LoginManager(userList, movieListFromJson, operator);

        // TODO: GUI entry here


        // Run Tests
        Tests.testMovie(); // Tests that movies get loaded and prints to console
        Tests.loginFlow(); // Tests the basic login flow

        // Create Movie List Object for the Movie List View to display
        MovieList movieList = new MovieList(movieListFromJson);

        // Prints out the number of movies in the database
        System.out.println(movieList.size());

        // Prints out the medialist sorted by movie titles alphabetically
        //movieList.sortbyTitleAlphabetically();
        //movieList.displayList();

        // Prints out the medialist sorted by their release data
        movieList.sortbyYearReleased();
        movieList.displayList();

    }
}