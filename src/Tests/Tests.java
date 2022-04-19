package Tests;

import Controllers.LoginManager;
import Models.Movie;
import Models.User;
import Main.MovieJsonOperator;

import java.text.ParseException;
import java.util.ArrayList;

class Tests {
    static void testMovie() throws ParseException {

        // TEST: PROPERLY IMPORT MOVIE INTO MOVIE OBJECT FROM JSON FILE
        MovieJsonOperator operator = new MovieJsonOperator("data/SampleMovieFile.json", "data/users.json");
        ArrayList<Movie> movielist = operator.GetAllMovies();
        Movie movie0 = movielist.get(1);

        // TEST: MOVIE CONSTRUCTOR
        Movie movie1 = new Movie("Dune", "2021", "PG-13", "22 Oct 2021", "155 min", "Action, Adventure, Drama", "Denis Villeneuve", "Timothée Chalamet, Rebecca Ferguson, Zendaya", "English, Mandarin", "https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg", "74", "8.1","444,702", "tt1160419", "movie");

        // TEST: MOVIE PRINT TO CONSOLE
        Movie.printSimpleMovieToConsole(movie0);
        Movie.printSimpleMovieToConsole(movie1);

        // TEST: MOVIE GETTER FUNCTIONS
        System.out.println("Models.Movie Title: " + movie1.getTitle() + "|  Models.Movie Genre: " + movie1.getYear());

        // TEST: MOVIE GETTER FUNCTION - STRING WITH COMMAS
        System.out.println(movie1.getGenre());

        // TEST: TEST THE VIEW
        // MovieListView thing = new MovieListView();


    }

    static void loginFlow() {
        System.out.println("\nBegin LoginFlow Test\n\n");
        // Initialize dependencies for login
        MovieJsonOperator operator = new MovieJsonOperator("data/SampleMovieFile.json", "data/users.json");
        ArrayList<Movie> movielist = operator.GetAllMovies();
        ArrayList<User> userlist = operator.GetAllUsers();
        LoginManager loginManager = new LoginManager(userlist, movielist, operator);

        // login with bad credentials
        System.out.println("Login with bad credentials");
        System.out.println("Logged in: " +
            loginManager.login("incorrect", "credentials") // returns false
        );

        System.out.println("Logged in: " +
                loginManager.verifyLogin() // returns false
        );

        // signup for a new account
        System.out.println("Signup for a new account: " +
            loginManager.signup("new", "guy") // returns false if user already exists
        );
        if(loginManager.verifyLogin()) { // returns false if user already exists
            loginManager.getCurrentUser().newCollection("To Watch");
            loginManager.getCurrentUser().getCollection("Watched").addMovie(movielist.get(5));
            loginManager.getCurrentUser().getCollection("Watched").addMovie(movielist.get(25));
            loginManager.getCurrentUser().getCollection("Watched").addMovie(movielist.get(32));
            loginManager.save();
            System.out.println("Check users.json to see the data that changed!");
        }

        System.out.println("Logged out: " +
            loginManager.logout() // returns false if not logged in
        );

        // log back in and modify account
        if(loginManager.login("new", "guy")) {
            loginManager.getCurrentUser().newCollection("successful login");
            loginManager.getCurrentUser().getCollection("Watched").removeMovie(movielist.get(25));
            loginManager.getCurrentUser().getCollection("Watched").removeMovie(movielist.get(9));
            loginManager.getCurrentUser().getCollection("Watched").addMovie(movielist.get(2));
            loginManager.getCurrentUser().addComment(movielist.get(5).getImdbID(), "my comment!");
            loginManager.getCurrentUser().addComment(movielist.get(5).getImdbID(), "updated comment!");
            String blah = loginManager.getCurrentUser().getComment(movielist.get(5).getImdbID());
            loginManager.save();
            System.out.println("Logged back in. Check users.json to see the data that changed!");
        }
    }
}
