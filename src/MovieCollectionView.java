import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MovieCollectionView {

    private LoginManager loginManager;
    private ArrayList<MovieCollection> movieCollectionList;

    public MovieCollectionView(LoginManager loginManager) {
        this.loginManager = loginManager;
        if (loginManager.getCurrentUser() != null) { // they are signed in
            this.movieCollectionList = loginManager.getCurrentUser().getallCollections();
        }
    }

    public JPanel refreshCollectionView() {
        JPanel cBlockList = new JPanel();       // collection blocks list
        cBlockList.setLayout(new GridLayout(0, 1));

        if(loginManager.getCurrentUser() != null) {
            movieCollectionList = loginManager.getCurrentUser().getallCollections();
            for(MovieCollection mc: movieCollectionList) {
                String cName = mc.getName();
                JLabel cBlockLabel = new JLabel(cName);
                cBlockLabel.setVisible(true);
                JPanel cBlock = new JPanel();
                cBlock.setLayout(new FlowLayout());
                cBlock.setName(cName);
                cBlock.add(cBlockLabel);
                cBlock.revalidate();
                cBlock.setVisible(true);
                cBlockList.add(cBlock);
            }
        } else {
            String cName = "mustLogIn";
            JLabel cBlockLabel = new JLabel(cName);
            cBlockLabel.setVisible(true);
            JPanel cBlock = new JPanel();
            cBlock.setLayout(new FlowLayout());
            cBlock.setName(cName);
            cBlock.add(cBlockLabel);
            cBlock.revalidate();
            cBlock.setVisible(true);
            cBlockList.add(cBlock);
        }
        cBlockList.revalidate();
        cBlockList.setVisible(true);
        return cBlockList;
    }
}
