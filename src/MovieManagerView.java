import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MovieManagerView {
    // Views
    static LoginView accountView;
    static MovieCollectionView movieCollectionView;
    static SimpleMessage s = new SimpleMessage();

    // Dependencies
    static LoginManager loginManager;
    static MovieManager movieManager;

    // UI Components
    static JPanel moviePanel;
    static JPanel movieDetails;
    static JPanel loginSection;
    static JScrollPane cListWidget;

    /**
     * sets the instances of LoginManager, MovieManager, and MovieCollectionView so that the methods
     * in those classes can be used here
     * @param loginManager
     * @param movieManager
     * @param movieCollection
     */
    public MovieManagerView(LoginManager loginManager, MovieManager movieManager, MovieCollectionView movieCollection) {
        this.accountView = new LoginView(loginManager, this);
        this.loginManager = loginManager;
        this.movieManager = movieManager;
        this.movieCollectionView = movieCollection;
        System.out.println("initialized account view");
    }

    public static void updateCollectionPanel() {
        // clear old view
        if (cListWidget != null) {
            cListWidget.removeAll();
        }

        // create new view
        movieCollectionView = new MovieCollectionView(loginManager);
        cListWidget = new JScrollPane(movieCollectionView.refreshCollectionView());

        // revalidate view
        cListWidget.setMinimumSize(new Dimension(80, 40));
        cListWidget.revalidate();
        cListWidget.setVisible(true);
    }

    public static void updateMoviepanel(String term) {
        moviePanel.removeAll();
        if(movieManager.search(term).isEmpty()) {
            s.message("None found", "Error: No movies match your search.");
        } else {
            for (Movie m : movieManager.search(term)) {
                JPanel movieBlock = new JPanel();
                JLabel title = new JLabel(m.getTitle());
                movieBlock.setLayout(new FlowLayout());
                movieBlock.add(title);
                moviePanel.add(movieBlock);
                String imdbID = m.getImdbID();
                movieBlock.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // add movie panel to side
                        updateMovieDetails(imdbID);
                    }
                });
            }
        }
        moviePanel.add(new JPanel());
        moviePanel.revalidate();
    }

    public static void updateMovieDetails(String imdbID) {
        System.out.println(imdbID);
        Movie movie = movieManager.get(imdbID);
        movieDetails.setVisible(false);
        movieDetails.removeAll();
        //movieDetails.setLayout(new GridLayout(0, 1));
        movieDetails.setLayout(new BoxLayout(movieDetails, BoxLayout.PAGE_AXIS));
        movieDetails.setMaximumSize(new Dimension(100, 100));

        // get movie poster
        try {
            URL url = new URL(movie.getPoster());
            Image image = ImageIO.read(url);
            if(image == null) {
                movieDetails.add(new JLabel(movie.getPoster()));
            } else {
                JLabel icon = new JLabel();
                icon.setIcon(new ImageIcon(image));
                movieDetails.add(icon);
            }
        } catch(IOException e) {
            movieDetails.add(new JLabel("Image could not be displayed"));
        }

        // add other movie details
        movieDetails.add(new JLabel(movie.getTitle()));
        movieDetails.add(new JLabel("Released: " + String.valueOf(movie.getYear())));
        movieDetails.add(new JLabel("Genre: " + String.valueOf(movie.getGenre())));
        movieDetails.add(new JLabel("Rating: " + String.valueOf(movie.getImdbRating())));
        movieDetails.add(new JLabel("Metascore: " + String.valueOf(movie.getMetascore())));
        movieDetails.add(new JLabel("Director: \n" + movie.getDirector()));
        JLabel label = new JLabel("Actors: " + movie.getActors());
        movieDetails.add(label);
        movieDetails.setVisible(true);
        movieDetails.revalidate();
    }

    public static void updateLoginSection() {
        loginSection.removeAll();
        loginSection.add(new JLabel("Login/Signup: "));
        JButton loginBtn;

        JButton signupBtn = new JButton("Sign-up");
        signupBtn.addActionListener(e -> {
            System.out.println("Pressed Sign-Up Button");
            accountView.openSignUpView();
        });

        if (loginManager.verifyLogin() == false) {
            loginBtn = new JButton("Login");
            loginBtn.addActionListener(e -> {
                System.out.println("Pressed Login Button");
                accountView.openLogInView();
            });
            signupBtn.setVisible(true);
        }  else {
            loginBtn = new JButton("Logout");
            loginBtn.addActionListener(e -> {
                System.out.println("Pressed Logout Button");
                int save = s.confirmed("Warning", "Do you want to save your progress?");
                if (save == 0 && !s.checkConfirm()) {
                    loginManager.save();
                }
                loginManager.logout();
                updateLoginSection();
            });
            updateCollectionPanel();
            signupBtn.setVisible(false);
        }
        loginSection.add(loginBtn);
        loginSection.add(signupBtn);
        loginSection.revalidate();
        updateCollectionPanel();
    }

    public static void main() {
        JFrame frame = new JFrame("Main");
        JPanel frameP = new JPanel();
        frameP.setLayout(new BorderLayout(5, 5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        // Build out the TopBar
        // The SEARCH bar for the top middle section
        // JLabel searchBarIcon = new JLabel("Search: ");🔎
        JLabel searchBarIcon = new JLabel("Search: ");
        JTextField searchBar = new JTextField("Search Here");
        searchBar.setMinimumSize(searchBar.getSize());
        searchBar.setPreferredSize(new Dimension(225, 30));
        searchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBar.setText("");
            }
        });

        searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                updateMoviepanel(searchBar.getText());
            }
        });

        searchBarIcon.setVisible(true);
        searchBar.setLocation(frame.getWidth() / 2, 20);
        searchBar.setVisible(true);


        loginSection = new JPanel();
        loginSection.add(new JLabel("Login/Signup: "));
        updateLoginSection();
        loginSection.setLocation((frame.getWidth() - 60), 20);
        loginSection.setVisible(true);


        JPanel topBar = new JPanel();
        topBar.add(searchBarIcon);
        topBar.add(searchBar);
        topBar.add(loginSection);
        topBar.setVisible(true);

        // create movie panel
        moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0, 1));
        updateMoviepanel("");

        JScrollPane movieScroll = new JScrollPane(moviePanel);

        updateCollectionPanel();

        movieDetails = new JPanel();

        frameP.add(topBar, BorderLayout.PAGE_START);
        frameP.add(movieScroll, BorderLayout.CENTER);
        frameP.add(cListWidget, BorderLayout.LINE_START);
        frameP.add(movieDetails, BorderLayout.LINE_END);
        frameP.setVisible(true);
        frame.setContentPane(frameP);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
