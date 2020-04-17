package jdbc.dao;

import abstract_repo.AbstractMusicGenreRepo;
import jpa.entity.MusicGenre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO class responsible
 * to get data regarding
 * music genres from database.
 *
 * @author Ioan Sava
 */
public class MusicGenreController extends Controller implements AbstractMusicGenreRepo {
    public MusicGenreController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Insert a new music genre in database.
     */
    public void create(MusicGenre musicGenre) {
        try {
            String query = "insert into music_genres(name)" +
                    " values(?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, musicGenre.getName());

            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public MusicGenre findById(int id) {
        MusicGenre musicGenre = null;
        try {
            String query = "select * from music_genres where " +
                    "id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                musicGenre = new MusicGenre(name);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return musicGenre;
    }
}
