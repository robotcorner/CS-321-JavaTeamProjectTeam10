import java.util.ArrayList;

public interface ISearchable {
    ArrayList<Movie> search(ArrayList<Movie> movieList, String term);
    ArrayList<Movie> searchTitle(ArrayList<Movie> mediaList, String term);
    ArrayList<Movie> searchGenre(ArrayList<Movie> mediaList, String term);
    ArrayList<Movie> searchCast(ArrayList<Movie> mediaList, String term);
    ArrayList<Movie> searchDirector(ArrayList<Movie> mediaList, String term);
}
