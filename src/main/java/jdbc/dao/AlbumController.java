package jdbc.dao;

import abstract_repo.AbstractAlbumRepo;
import jpa.entity.Album;
import jpa.entity.Artist;
import jpa.entity.MusicGenre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class responsible
 * to get data regarding Albums
 * from database.
 *
 * @author Ioan Sava
 */
public class AlbumController extends Controller implements AbstractAlbumRepo {

    public AlbumController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Insert a new Album in database.
     */
    public void create(Album album) {
        try {
            String query = "insert into albums(name, artist_id, genre_id, release_year)" +
                    " values(?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, album.getName());
            preparedStatement.setInt(2, album.getArtist().getId());
            preparedStatement.setInt(3, album.getMusicGenre().getId());
            preparedStatement.setInt(4, album.getReleaseYear());
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Return the Album which has the
     * specified @param id
     */
    public Album findById(int id) {
        Album album = null;
        try {
            String query = "select * from albums where " +
                    "id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int artistId = resultSet.getInt("artist_id");
                int genreId = resultSet.getInt("genre_id");
                int releaseYear = resultSet.getInt("release_year");

                ArtistController artistController = new ArtistController();
                Artist artist = artistController.findById(artistId);

                MusicGenreController musicGenreController = new MusicGenreController();
                MusicGenre musicGenre = musicGenreController.findById(genreId);

                album = new Album(id, name, artist, musicGenre, releaseYear);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return album;
    }

    /**
     * Return a list of Artists which have the
     * specified @param name
     */
    public List<Album> findByName(String name) {
        List<Album> listOfAlbums = new ArrayList<>();
        try {
            String query = "select * from albums where " +
                    "upper(trim(name)) = upper(trim(?));";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int artistId = resultSet.getInt("artist_id");
                int genreId = resultSet.getInt("genre_id");
                int releaseYear = resultSet.getInt("release_year");

                ArtistController artistController = new ArtistController();
                Artist artist = artistController.findById(artistId);

                MusicGenreController musicGenreController = new MusicGenreController();
                MusicGenre musicGenre = musicGenreController.findById(genreId);

                listOfAlbums.add(new Album(id, name, artist, musicGenre, releaseYear));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return listOfAlbums;
    }

    /**
     * Return a list of albums composed
     * by a specified artist.
     */
    public List<Album> findByArtist(Artist artist) {
        List<Album> listOfAlbums = new ArrayList<>();
        try {
            String query = "select * from albums where " +
                    "artist_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, artist.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int genreId = resultSet.getInt("genre_id");
                int releaseYear = resultSet.getInt("release_year");

                MusicGenreController musicGenreController = new MusicGenreController();
                MusicGenre musicGenre = musicGenreController.findById(genreId);

                listOfAlbums.add(new Album(id, name, artist, musicGenre, releaseYear));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return listOfAlbums;
    }
}
