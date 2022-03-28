import java.text.ParseException;

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

    }
}



