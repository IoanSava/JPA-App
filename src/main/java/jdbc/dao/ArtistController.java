package jdbc.dao;

import abstract_repo.AbstractArtistRepo;
import jpa.entity.Artist;

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
public class ArtistController extends Controller implements AbstractArtistRepo {

    public ArtistController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Insert a new Artist in database.
     */
    public void create(Artist artist) {
        try {
            String query = "insert into artists(name, country)" +
                    " values(?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getCountry());

            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Return the Artist which has the
     * specified @param id
     */
    public Artist findById(int id) {
        Artist artist = null;
        try {
            String query = "select * from artists where " +
                    "id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                artist = new Artist(id, name, country);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return artist;
    }

    /**
     * Return a list of Artists which have the
     * specified @param name
     */
    public List<Artist> findByName(String name) {
        List<Artist> listOfArtists = new ArrayList<>();
        try {
            String query = "select * from artists where " +
                    "upper(trim(name)) = upper(trim(?));";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String country = resultSet.getString("country");
                listOfArtists.add(new Artist(id, name, country));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return listOfArtists;
    }
}
