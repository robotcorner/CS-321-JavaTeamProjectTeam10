import java.text.ParseException;

class Tests {
    static void testMovie() throws ParseException {
        // Create a movie from JSON...
        // TODO: Use the JSON Class to get the formated version to input in the future.
        Movie movie1 = new Movie("Dune", "2021", "PG-13", "22 Oct 2021", "155 min", "Action, Adventure, Drama", "Denis Villeneuve", "Timothée Chalamet, Rebecca Ferguson, Zendaya", "English, Mandarin", "https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg", "74", "8.1","444,702", "tt1160419", "movie");
        Movie.printMovieToConsole(movie1);
    }
}



