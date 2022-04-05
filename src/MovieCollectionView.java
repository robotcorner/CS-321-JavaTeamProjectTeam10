import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MovieCollectionView {

    private LoginManager loginManager;
    private MovieCollection movieCollection;

    private User user = loginManager.getCurrentUser();
    private ArrayList<MovieCollection> userCollections = user.getallCollections();
    private ArrayList<JPanel> collectionList;

    public MovieCollectionView(MovieCollection movieCollection, LoginManager loginManager) {
        this.movieCollection = movieCollection;
        this.loginManager = loginManager;
    }

    public void openCollectionView() {
        // This will be the main JPanel that holds collections which will also be JPanels
        JPanel userCollectionView = new JPanel();

        // Sets the layout so that components will be added from top to bottom
        userCollectionView.setLayout(new BoxLayout(userCollectionView,BoxLayout.Y_AXIS));



        // Collection Panel
        for(MovieCollection m: userCollections){
            JPanel l = new JPanel();
            l.setName(m.getName());
            for(Movie movie: m.getMovieList()){
                l.add(new JLabel(movie.getTitle()));
            }
            collectionList.add(l);
        }

        // Adds all the movie collection panels to the main collection view panel
        for(JPanel p: collectionList){
            userCollectionView.add(p);
        }

        










    }





}
