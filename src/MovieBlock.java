import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieBlock extends JPanel {
    MovieBlock(Movie m) {
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
        final boolean[] heartToggled = {false};
        JLabel heart = new JLabel("♡");
        heart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!heartToggled[0]) {
                    heart.setText("❤");
                    heartToggled[0] = true;
                } else {
                    heart.setText("♡");
                    heartToggled[0] = false;
                }
            }
        });
        this.add(heart);
    }
}
