// This is a default file for the sake of starting version control.
// The main file would use the class structure definitions as needed.

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

        // create movie list and user list
        MovieJsonOperator operator = new MovieJsonOperator("data/SampleMovieFile.json", "data/users.json");
        ArrayList<Movie> movielist = operator.GetAllMovies();
        ArrayList<User> userlist = operator.GetAllUsers();

        // login - this test assumes data is present
        User u1 = userlist.get(0);
        // build collections from the id list that gets saved
        u1.buildCollections(movielist);

        // new user
        User u = new User("user2", "test123");
        u.newCollection("watchlist");
        u.newCollection("watchlist"); // won't add twice
        u.getCollection("watchlist").addMovie(movielist.get(5));
        u.getCollection("watchlist").removeMovie(movielist.get(5));
        userlist.add(u);
        operator.SaveAllUsers(userlist);

        // Create Movie List Object for the Movie List View to display
        MovieList movieList = new MovieList(movielist);
        movieList.displayList();
        // Prints out the number of movies in the database
        System.out.println(movieList.size());





        // Run Tests
        Tests.testMovie();
    }
}