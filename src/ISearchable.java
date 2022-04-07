import java.util.ArrayList;

public interface ISearchable {
    ArrayList<Movie> search(ArrayList<Movie> movieList, String term);
}
