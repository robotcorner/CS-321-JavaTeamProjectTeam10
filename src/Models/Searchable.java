// Decorator Design Pattern
package Models;

import Models.ISearchable;
import Models.Movie;

import java.util.ArrayList;
import java.util.Locale;

public class Searchable implements ISearchable {
    /**
     * Searches a few interesting fields and returns a list of all matches
     * @param term whatever the user enters in the GUI
     * @return ArrayList of Movies
     */
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

    public ArrayList<Movie> searchTitle(ArrayList<Movie> mediaList, String term) {
        if(term.isEmpty())
            return mediaList;

        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : mediaList) {
            if(m.getTitle().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
        }

        return result;
    }

    public ArrayList<Movie> searchGenre(ArrayList<Movie> mediaList, String term) {
        if(term.isEmpty())
            return mediaList;

        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : mediaList) {
            if(m.getGenre().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
        }

        return result;
    }

    public ArrayList<Movie> searchCast(ArrayList<Movie> mediaList, String term) {
        if(term.isEmpty())
            return mediaList;

        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : mediaList) {
            if(m.getActors().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
        }

        return result;
    }

    public ArrayList<Movie> searchDirector(ArrayList<Movie> mediaList, String term) {
        if(term.isEmpty())
            return mediaList;

        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : mediaList) {
            if(m.getDirector().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT)))
                result.add(m);
        }

        return result;
    }
}
