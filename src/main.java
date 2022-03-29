// This is a default file for the sake of starting version control.
// The main file would use the class structure definitions as needed.

/*
Course:         CS-321
Project:        Build an app with GUI in swing for managing a book or movie watch later personal list.
Requirements:
    Use version control.
    Have 3-4 members.

Authors:    Stephen Stammen, Daniel Mills, Caleb Bagwell,
 */

import java.io.File;
import java.text.ParseException;

/**
 * The HelloWorldApp class implements an application that
 * simply prints "Hello World!" to standard output.
 * This type of comment is considered documentation.
 * This part will be removed and is just for an example.
 */
class HelloWorldApp {
    public static void main(String[] args) throws ParseException {
        System.out.println(new File("data/SampleMovieFile.json").getAbsolutePath()); // correct path
        MovieJsonOperator operator = new MovieJsonOperator("data/SampleMovieFile.json"); // still not working
        operator.Get("tt1785572"); // Should see spiderman movie details printed to console

        // Run Tests
        Tests.testMovie();
    }
}