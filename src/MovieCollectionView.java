import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MovieCollectionView {

    private LoginManager loginManager;
    private ArrayList<MovieCollection> movieCollectionList;
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();


    public MovieCollectionView(LoginManager loginManager) {
        this.loginManager = loginManager;
        if (loginManager.verifyLogin()) { // signed in
            this.movieCollectionList = loginManager.getCurrentUser().getallCollections();
        }
    }

    public JPanel refreshCollectionView() {
        JPanel cBlockList = new JPanel();       // collection blocks list
        cBlockList.setLayout(new GridLayout(0, 1));

        if (loginManager.getCurrentUser() != null) {
            movieCollectionList = loginManager.getCurrentUser().getallCollections();
            labels.clear();
            for (MovieCollection mc : movieCollectionList) {
                String cName = mc.getName();
                JLabel cBlockLabel = new JLabel(cName);
                labels.add(cBlockLabel);
                cBlockLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cBlockLabel.getForeground().equals(Color.red)) {
                            MovieManagerView.updateMoviePanel(new MovieCollection(MovieManagerView.movieManager.getMediaList()));
                            MovieManagerView.updateMovieDetails("");
                            cBlockLabel.setForeground(Color.black);
                        } else {
                            MovieManagerView.updateMoviePanel(mc);
                            for (JLabel l : labels) {
                                if (cBlockLabel.equals(l)) cBlockLabel.setForeground(Color.red);
                                else l.setForeground(Color.black);
                            }
                        }
                    }
                });


                cBlockList.add(cBlockLabel);
            }
        }

        cBlockList.revalidate();
        return cBlockList;
    }
}
