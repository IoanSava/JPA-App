package app;

import abstract_repo.*;
import factory.*;
import jdbc.db.Database;
import jpa.entity.*;
import jpa.util.PersistenceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * An application that allows to connect
 * to a relational database. The application
 * will use JDBC or JPA depending on a
 * parameter given in an initialization file.
 *
 * @author Ioan Sava
 */
public class AlbumManager {
    private final String INIT_FILE = "init.txt";

    private static Connection connection;

    private AbstractArtistRepo artistRepository;
    private AbstractAlbumRepo albumRepository;
    private AbstractChartRepo chartRepository;
    private AbstractChartAlbumRepo chartAlbumRepository;
    private AbstractMusicGenreRepo musicGenreRepository;

    public static void main(String[] args) {
        AlbumManager albumManager = new AlbumManager();
        int parameter = albumManager.getParameter();
        albumManager.initializeConnection(parameter);
        albumManager.initializeRepositories(parameter);
        albumManager.performOperations();
        albumManager.clear(parameter);
    }

    /**
     * The application will use
     * JDBC or JPA depending on
     * a parameter given in an
     * initialization file.
     *
     * @return 1 if the application uses JDBC
     * anything else, the application uses JPA
     */
    private int getParameter() {
        int parameter = 0;
        try {
            File file = new File(INIT_FILE);
            Scanner scanner = new Scanner(file);
            parameter = scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parameter;
    }

    private void initializeConnection(int parameter) {
        if (parameter == 1) {
            try {
                connection = Database.getInstance().getConnection();
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeRepositories(int type) {
        ArtistRepositoryFactory artistRepositoryFactory = new ArtistRepositoryFactory();
        artistRepository = (AbstractArtistRepo) artistRepositoryFactory.createRepository(type);

        AlbumRepositoryFactory albumRepositoryFactory = new AlbumRepositoryFactory();
        albumRepository = (AbstractAlbumRepo) albumRepositoryFactory.createRepository(type);

        ChartRepositoryFactory chartRepositoryFactory = new ChartRepositoryFactory();
        chartRepository = (AbstractChartRepo) chartRepositoryFactory.createRepository(type);

        ChartAlbumRepositoryFactory chartAlbumRepositoryFactory = new ChartAlbumRepositoryFactory();
        chartAlbumRepository = (AbstractChartAlbumRepo) chartAlbumRepositoryFactory.createRepository(type);

        MusicGenreRepositoryFactory musicGenreRepositoryFactory = new MusicGenreRepositoryFactory();
        musicGenreRepository = (AbstractMusicGenreRepo) musicGenreRepositoryFactory.createRepository(type);
    }

    private void clear(int parameter) {
        if (parameter == 1) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } else {
            PersistenceUtil.getInstance().getEntityManagerFactory().close();
        }
    }

    private void showAvailableOperations() {
        System.out.println("0 - Show operations");
        System.out.println("1 - Create an artist (name + country)");
        System.out.println("2 - Find an artist by his id");
        System.out.println("3 - Find an artist by his name");
        System.out.println("4 - Create an album (name + artistId + genreId + releaseYear)");
        System.out.println("5 - Find an album by his id");
        System.out.println("6 - Find an album by his name");
        System.out.println("7 - Find the list of albums of a given artist");
        System.out.println("8 - Create a chart (name)");
        System.out.println("9 - Create a chart record (chartId + albumId + rank)");
        System.out.println("10 - Display ranking by id");
        System.out.println("11 - Create genre (name)");
        System.out.println("12 - Quit");
    }

    private void createArtist(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Country: ");
        String country = scanner.next();

        Artist artist = new Artist(name, country);
        artistRepository.create(artist);
    }

    private void findArtistById(Scanner scanner) {
        System.out.print("Id of the artist: ");
        int id = scanner.nextInt();
        System.out.println(artistRepository.findById(id));
    }

    private void findArtistByName(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.println(artistRepository.findByName(name));
    }

    private void createAlbum(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Id of the artist: ");
        int artistId = scanner.nextInt();
        System.out.print("Id of the genre: ");
        int genreId = scanner.nextInt();
        System.out.print("Release year: ");
        int releaseYear = scanner.nextInt();

        Artist artist = artistRepository.findById(artistId);
        MusicGenre musicGenre = musicGenreRepository.findById(genreId);
        Album album = new Album(name, artist, musicGenre, releaseYear);
        albumRepository.create(album);
    }

    private void findAlbumById(Scanner scanner) {
        System.out.print("Id of the album: ");
        int id = scanner.nextInt();
        System.out.println(albumRepository.findById(id));
    }

    private void findAlbumByName(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.println(albumRepository.findByName(name));
    }

    private void findAlbumByArtist(Scanner scanner) {
        System.out.print("Id of the artist: ");
        int artistId = scanner.nextInt();
        Artist artist = artistRepository.findById(artistId);
        System.out.println(albumRepository.findByArtist(artist));
    }

    private void createChartRecord(Scanner scanner) {
        System.out.print("Id of the chart: ");
        int chartId = scanner.nextInt();
        System.out.print("Id of the album: ");
        int albumId = scanner.nextInt();
        System.out.print("Rank: ");
        int rank = scanner.nextInt();

        Chart chart = chartRepository.findById(chartId);
        Album album = albumRepository.findById(albumId);
        ChartAlbum chartAlbum = new ChartAlbum(chart, album, rank);
        chartAlbumRepository.create(chartAlbum);
    }

    private void displayRanking(Scanner scanner) {
        System.out.print("Id of the chart: ");
        int chartId = scanner.nextInt();
        chartRepository.displayRanking(chartId);
    }

    private void createChart(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();

        Chart chart = new Chart(name);
        chartRepository.create(chart);
    }

    private void createGenre(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();

        MusicGenre musicGenre = new MusicGenre(name);
        musicGenreRepository.create(musicGenre);
    }

    private void performSelectedOperation(int operation, Scanner scanner) {
        switch (operation) {
            case 0:
                showAvailableOperations();
                break;
            case 1:
                createArtist(scanner);
                break;
            case 2:
                findArtistById(scanner);
                break;
            case 3:
                findArtistByName(scanner);
                break;
            case 4:
                createAlbum(scanner);
                break;
            case 5:
                findAlbumById(scanner);
                break;
            case 6:
                findAlbumByName(scanner);
                break;
            case 7:
                findAlbumByArtist(scanner);
                break;
            case 8:
                createChart(scanner);
                break;
            case 9:
                createChartRecord(scanner);
                break;
            case 10:
                displayRanking(scanner);
                break;
            case 11:
                createGenre(scanner);
                break;
        }
    }

    private void performOperations() {
        showAvailableOperations();
        int operation = 0;
        Scanner scanner = new Scanner(System.in);
        while (operation != 12) {
            System.out.print("Operation: ");
            operation = scanner.nextInt();
            while (operation < 0 || operation > 12) {
                System.out.println("Invalid operation. Choose between 0-12");
                System.out.print("Operation: ");
                operation = scanner.nextInt();
            }
            performSelectedOperation(operation, scanner);
        }
    }
}