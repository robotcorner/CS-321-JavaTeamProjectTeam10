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

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * The HelloWorldApp class implements an application that
 * simply prints "Hello World!" to standard output.
 * This type of comment is considered documentation.
 * This part will be removed and is just for an example.
 */
class HelloWorldApp {
    public static void main(String[] args) throws ParseException {
        MovieJsonOperator operator = new MovieJsonOperator("data/SampleMovieFile.json");
        ArrayList<Movie> movielist = operator.GetAll();
        // Run Tests
        Tests.testMovie();
    }
}