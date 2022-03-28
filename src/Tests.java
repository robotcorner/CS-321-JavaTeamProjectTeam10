import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;

class Tests {
    static void testMovie() throws ParseException {

        // TODO: TEST: PROPERLY IMPORT MOVIE INTO MOVIE OBJECT FROM JSON FILE


        // TEST: MOVIE CONSTRUCTOR
        Movie movie1 = new Movie("Dune", "2021", "PG-13", "22 Oct 2021", "155 min", "Action, Adventure, Drama", "Denis Villeneuve", "Timothée Chalamet, Rebecca Ferguson, Zendaya", "English, Mandarin", "https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg", "74", "8.1","444,702", "tt1160419", "movie");

        // TEST: MOVIE PRINT TO CONSOLE
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
}



