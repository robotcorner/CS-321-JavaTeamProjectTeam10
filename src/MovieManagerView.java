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
    static SaveMessage saveMessage;
    static SimpleMessage s = new SimpleMessage();

    // Dependencies
    static LoginManager loginManager;
    static MovieManager movieManager;
    static MovieCollection currentCollection;

    // UI Components
    static JPanel moviePanel;
    static JPanel movieDetails;
    static JPanel loginSection;
    static JPanel collectionsPanel;
    static JLabel accountSection;

    /**
     * sets the instances of LoginManager, MovieManager, and MovieCollectionView so that the methods
     * in those classes can be used here
     * @param loginManager
     * @param movieManager
     */
    public MovieManagerView(LoginManager loginManager, MovieManager movieManager) {
        this.accountView = new LoginView(loginManager, this);
        this.loginManager = loginManager;
        this.movieManager = movieManager;
        this.saveMessage = new SaveMessage(loginManager);
        this.movieCollectionView = new MovieCollectionView(loginManager);
        currentCollection = new MovieCollection(movieManager.getMediaList());

        System.out.println("initialized account view");
    }

    /**
     * updates the collection panel
     */
    public static void updateCollectionPanel() {
        collectionsPanel.removeAll();
        collectionsPanel.add(movieCollectionView.refreshCollectionView());
        collectionsPanel.revalidate();
    }

    /**
     * updates the movie panel
     * searches within the current collection
     */
    public static void updateMoviePanel(String term) {
        moviePanel.removeAll();
        if(currentCollection.search(term).isEmpty()) {
            s.message("None found", "Error: No movies match your search.");
        } else {
            MovieCollection temp;
            if (loginManager.getCurrentUser() !=null) {
                temp = loginManager.getCurrentUser().getCollection("Favorites");
            } else {
                temp = new MovieCollection("None");
            }
            for (Movie m : currentCollection.search(term)) {
                boolean heartStatus = temp.getMovieList().contains(m);
                moviePanel.add(new MovieBlock(m, heartStatus));
            }
        }
        moviePanel.add(new JPanel());
        moviePanel.revalidate();
    }

    public static void updateMoviePanel(MovieCollection term) {
        currentCollection = term;
        updateMoviePanel("");
    }

    /**
     * updates the details regarding a certain movie
     */
    public static void updateMovieDetails(String imdbID) {
        System.out.println(imdbID);
        Movie movie = movieManager.get(imdbID);
        movieDetails.setVisible(false);
        movieDetails.removeAll();
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

    /**
     * adds a movie to the collection
     */
    public static boolean userAddMovieToCollection(String name, Movie movie) {
        if (loginManager.getCurrentUser() == null) {
            s.message("Must Log In", "You're not allowed to favorite movies or add to the collection until you log in.");
            return false;
        } else {
            loginManager.getCurrentUser().getCollection(name).addMovie(movie);
            return true;
        }
    }

    /**
     * removes a movie from the collection
     */
    public static void userRemoveMovieFromCollection(String name, Movie movie) {
        loginManager.getCurrentUser().getCollection(name).removeMovie(movie);
    }

    /**
     * updates the login section whenever the user logs in or logs out
     */
    public static void updateLoginSection() {
        loginSection.removeAll();
        loginSection.add(new JLabel("Login/Signup: "));
        JButton loginBtn;

        JButton signupBtn = new JButton("Sign-up");
        signupBtn.addActionListener(e -> {
            System.out.println("Pressed Sign-Up Button");
            accountView.openSignUpView();
        });

        if (!loginManager.verifyLogin()) {      // user failed login
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
                saveMessage.save();
                accountSection.setText("guest");
                updateLoginSection();
            });
            signupBtn.setVisible(false);
            accountSection.setText(loginManager.getCurrentUser().getUsername());
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
        frame.setSize(1200, 1000);

        // Build out the TopBar
        // The SEARCH bar for the top middle section
        JLabel searchBarIcon = new JLabel("Search: ");
        JTextField searchBar = new JTextField("Search Here");
        searchBar.setMinimumSize(searchBar.getSize());
        searchBar.setPreferredSize(new Dimension(200, 30));
        searchBar.setMaximumSize(new Dimension(250, 30));
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
                updateMoviePanel(searchBar.getText());
            }
        });

        searchBar.setLocation(frame.getWidth() / 2, 20);

        // create collections panel before it gets updated with the login section
        collectionsPanel = new JPanel();
        collectionsPanel.setLayout(new GridLayout(0, 1));


        loginSection = new JPanel();
        loginSection.add(new JLabel("Login/Signup: "));
        updateLoginSection();
        loginSection.setLocation((frame.getWidth() - 60), 20);
        loginSection.setVisible(true);

        JLabel appTitle = new JLabel("MAML - My Awesome Movie Library");
        appTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pressed app title - trigger app detail overview window");
            }
        });
        appTitle.setLocation(0, 0);

        accountSection = new JLabel("guest");
        accountSection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pressed account name - trigger account view for logged in user");
            }
        });

        JPanel searchSection = new JPanel(new FlowLayout());
        searchSection.add(searchBarIcon);
        searchSection.add(searchBar);

        JPanel rightSection = new JPanel(new FlowLayout());
        rightSection.add(accountSection);
        rightSection.add(loginSection);

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout(10, 3));
        topBar.add(appTitle, BorderLayout.LINE_START);
        topBar.add(searchSection, BorderLayout.CENTER);
        topBar.add(rightSection, BorderLayout.LINE_END);
        topBar.setVisible(true);

        // Creates a Mouse Listener that refreshes the movie panel
        topBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentCollection = new MovieCollection(movieManager.getMediaList());
                updateMoviePanel("");
            }
        });

        // create movie panel
        moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0, 1));
        updateMoviePanel("");

        JScrollPane movieScroll = new JScrollPane(moviePanel);
        JScrollPane cListWidget = new JScrollPane(collectionsPanel);

        movieDetails = new JPanel();

        frameP.add(topBar, BorderLayout.PAGE_START);
        frameP.add(movieScroll, BorderLayout.CENTER);
        frameP.add(cListWidget, BorderLayout.LINE_START);
        frameP.add(movieDetails, BorderLayout.LINE_END);
        frameP.setVisible(true);
        frame.setContentPane(frameP);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
};
