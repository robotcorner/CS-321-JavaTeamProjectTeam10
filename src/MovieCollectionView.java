import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.jar.JarEntry;

public class MovieCollectionView {

    private LoginManager loginManager;
    private ArrayList<MovieCollection> movieCollectionList;
    private ArrayList<JPanel> collectionList;

    public MovieCollectionView(LoginManager loginManager) {
        if (loginManager.getCurrentUser() != null) {
            this.movieCollectionList = loginManager.getCurrentUser().getallCollections();
        }
        this.loginManager = loginManager;
    }

    public JScrollPane openCollectionView() {

        JPanel cBlockList = new JPanel();
        cBlockList.setLayout(new GridLayout(0, 1));

        // Collection Panel
        if (movieCollectionList != null) {
            for(MovieCollection mc: movieCollectionList){
                String cName = mc.getName();
                JLabel cBlockLabel = new JLabel(cName);
                cBlockLabel.setVisible(true);

                JPanel cBlock = new JPanel();
                cBlock.setLayout(new FlowLayout());
                cBlock.setName(cName);
                cBlock.add(cBlockLabel);
                cBlock.revalidate();
                cBlock.setVisible(true);
                /*
                for(Movie movie: mc.getMovieList()){
                    cBlock.add(new JLabel(movie.getTitle()));
                }
                */
                collectionList.add(cBlock);
            }

            // This will be the main JPanel that holds collections which will also be JPanels
            // Sets the layout so that components will be added from top to bottom


            // Adds all the movie collection panels to the main collection view panel
            for(JPanel p: collectionList){
                cBlockList.add(p);
            }

            cBlockList.revalidate();
            cBlockList.setVisible(true);
        }

        JScrollPane scrollPane = new JScrollPane(cBlockList);
        scrollPane.setVisible(true);
        return scrollPane;
    }
}
