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
    }
}
