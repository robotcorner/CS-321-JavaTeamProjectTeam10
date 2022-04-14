import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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

        if (loginManager.verifyLogin()) {
            movieCollectionList = loginManager.getCurrentUser().getallCollections();
            labels.clear();
            for (MovieCollection mc : movieCollectionList) {
                String cName = mc.getName();
                JPanel panel = new JPanel();

                JLabel cBlockLabel = new JLabel(cName);
                cBlockLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        System.out.println("released!");
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        MovieManagerView.drop = cBlockLabel;
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        MovieManagerView.drop = null;
                    }
                });
                JLabel deleteBtn = new JLabel();
                try {
                    File url = new File("data/trashcan.png");
                    Image image = ImageIO.read(url);
                    if (image == null) {
                        deleteBtn.setText("[delete]");
                    } else {
                        deleteBtn.setIcon(new ImageIcon(image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)));
                    }
                } catch (IOException e) {
                    deleteBtn.setText("[delete]");
                }
                deleteBtn.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("clicked");
                        loginManager.getCurrentUser().deleteCollection(cName);
                        MovieManagerView.updateCollectionPanel();
                    }

                    public void mousePressed(MouseEvent e) {}
                    public void mouseReleased(MouseEvent e) {}
                    public void mouseEntered(MouseEvent e) {}
                    public void mouseExited(MouseEvent e) {}
                });

                labels.add(cBlockLabel);
                cBlockLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cBlockLabel.getForeground().equals(Color.red)) {
                            MovieManagerView.resetCollection();
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

                panel.add(cBlockLabel);
                panel.add(deleteBtn);
                cBlockList.add(panel);
            }
        }
        cBlockList.revalidate();
        return cBlockList;
    }
}
