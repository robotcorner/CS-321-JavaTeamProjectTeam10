import java.util.ArrayList;
import java.util.Locale;

public class Searchable implements ISearchable {
    public ArrayList<Movie> search(ArrayList<Movie> mediaList, String term) {
        if(term.isEmpty())
            return mediaList;

        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : mediaList) {
            if(m.getTitle().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
            else if(m.getDirector().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
            else if(m.getActors().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
            else if(m.getGenre().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
        }

        return result;
    }
}
