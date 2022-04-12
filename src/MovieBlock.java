import javax.swing.*;
import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MovieBlock extends JPanel {
    MovieBlock(Movie m, boolean heartStatus, boolean isLoggedin) {
        super();


        boolean login;

        final int[] screenX = {0};
        final int[] screenY = {0};
        final int[] myX = {0};
        final int[] myY = {0};

        final int[] originalx = new int[1];
        final int[] originaly = new int[1];



        JLabel title = new JLabel(m.getTitle());
        this.setLayout(new FlowLayout());
        this.add(title);
        String imdbID = m.getImdbID();



        if(isLoggedin == true) {

            addMouseListener(new MouseAdapter() {

                public void mousePressed(MouseEvent e) {
                    screenX[0] = e.getXOnScreen();
                    screenY[0] = e.getYOnScreen();

                    originalx[0] = screenX[0];
                    originaly[0] = screenY[0];


                    myX[0] = getX();
                    myY[0] = getY();

                    MovieManagerView.updateMovieDetails(imdbID);

                }


                @Override
                public void mouseReleased(MouseEvent e) {

                    MovieManagerView.updateMoviePanel("");

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });

            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX[0];
                    int deltaY = e.getYOnScreen() - screenY[0];

                    setLocation(myX[0] + deltaX, myY[0] + deltaY);
                }


                @Override
                public void mouseMoved(MouseEvent e) {
                }

            });


            final boolean[] heartToggled = {heartStatus};
            JLabel heart;
            if (heartStatus) {
                heart = new JLabel("❤");
                heart.setForeground(Color.red);
            } else {
                heart = new JLabel("♡");
                heart.setForeground(Color.black);
            }
            heart.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!heartToggled[0]) {
                        boolean success = MovieManagerView.userAddMovieToCollection("Favorites", m);
                        if (success) {
                            heart.setText("❤");
                            heart.setForeground(Color.red);
                            heartToggled[0] = true;
                        }
                    } else {
                        heart.setText("♡");
                        heart.setForeground(Color.black);
                        heartToggled[0] = false;
                        MovieManagerView.userRemoveMovieFromCollection("Favorites", m);
                    }
                }
            });
            this.add(heart);


        }
        else {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MovieManagerView.updateMovieDetails(imdbID);
                }
            });
        }
    }
}
