import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MovieBlock extends JPanel {

    private Movie movie;

    MovieBlock(Movie m, boolean heartStatus, boolean isLoggedin, String count) {
        super();


        boolean login;

        final int[] screenX = {0};
        final int[] screenY = {0};
        final int[] myX = {0};
        final int[] myY = {0};

        final int[] originalx = new int[1];
        final int[] originaly = new int[1];

        this.movie = m;

        JLabel title = new JLabel(m.getTitle());
        this.setLayout(new FlowLayout());
        this.add(title);
        String imdbID = m.getImdbID();
        Color origColor = this.getBackground();
        Color hoverColor = new Color(210, 210, 210, 80);

        if(isLoggedin) {
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
                    setOpaque(true);
                    setBackground(hoverColor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setOpaque(false);
                    setBackground(origColor);
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

            JLabel countFav = new JLabel(count);
            Font f = countFav.getFont();
            countFav.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
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
                            countFav.setText(Integer.toString(Integer.parseInt(countFav.getText())+1));
                        }
                    } else {
                        heart.setText("♡");
                        heart.setForeground(Color.black);
                        heartToggled[0] = false;
                        MovieManagerView.userRemoveMovieFromCollection("Favorites", m);
                        countFav.setText(Integer.toString(Integer.parseInt(countFav.getText())-1));
                    }
                }
            });
            this.add(heart);
            this.add(countFav);
            this.setVisible(true);
        }
        else {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MovieManagerView.updateMovieDetails(imdbID);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    setOpaque(true);
                    setBackground(hoverColor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setOpaque(false);
                    setBackground(origColor);
                }
            });
        }


    }

    public Movie getMovie(){
        return movie;
    }



}