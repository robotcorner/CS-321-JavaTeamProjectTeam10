import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieBlock extends JPanel {
    MovieBlock(Movie m, boolean heartStatus) {
        super();

        JLabel title = new JLabel(m.getTitle());
        this.setLayout(new FlowLayout());
        this.add(title);
        String imdbID = m.getImdbID();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // add movie panel to side
                MovieManagerView.updateMovieDetails(imdbID);
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
                    if(success) {
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
}
