import javax.swing.*;
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
        System.out.println("Movie Title: " + movie1.getTitle() + "|  Movie Genre: " + movie1.getYear());

        // TEST: MOVIE GETTER FUNCTION - STRING WITH COMMAS
        System.out.println(movie1.getGenre());

        //MovieList myList = new MovieList();
        //myList.add("IDMovie1");

        ArrayList<String> IDs = new ArrayList<String>();
        IDs.add("IDMovie1");
        IDs.add("IDMovie2");
        IDs.add("IDMovie3");
        System.out.println(IDs);

        /*
        Form1 form = new Form1();
        MovieListView myMovieList = new MovieListView();
        myMovieList.setVisibile(true);
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.open(myMovieList);
        frame.setSize(300,300);
        frame.setVisible(true);

         */
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
            loginManager.getCurrentUser().newCollection("watched list");
            loginManager.getCurrentUser().newCollection("watchlist");
            loginManager.getCurrentUser().getCollection("watched list").addMovie(movielist.get(5));
            loginManager.getCurrentUser().getCollection("watchlist").addMovie(movielist.get(25));
            loginManager.getCurrentUser().getCollection("watchlist").addMovie(movielist.get(32));
            System.out.println("Check users.json to see the data that changed!");
        }

        System.out.println("Logged out: " +
            loginManager.logout() // automatically saves. returns false if not logged in
        );

        // log back in and modify account
        if(loginManager.login("new", "guy")) {
            loginManager.getCurrentUser().newCollection("successful login");
            loginManager.getCurrentUser().getCollection("watchlist").removeMovie(movielist.get(25));
            loginManager.getCurrentUser().getCollection("watchlist").removeMovie(movielist.get(9));
            loginManager.getCurrentUser().getCollection("watchlist").addMovie(movielist.get(2));
            loginManager.save();
            System.out.println("Logged back in. Check users.json to see the data that changed!");
        }
    }
}
