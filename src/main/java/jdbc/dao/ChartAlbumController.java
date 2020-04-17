package jdbc.dao;

import abstract_repo.AbstractChartAlbumRepo;
import jpa.entity.ChartAlbum;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO class responsible
 * to get data regarding Charts
 * records from database.
 *
 * @author Ioan Sava
 */
public class ChartAlbumController extends Controller implements AbstractChartAlbumRepo {

    public ChartAlbumController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Insert a new chart record in database.
     */
    public void create(ChartAlbum chartAlbum) {
        try {
            String query = "insert into charts_albums(chart_id, album_id, rank)" +
                    " values(?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chartAlbum.getChart().getId());
            preparedStatement.setInt(2, chartAlbum.getAlbum().getId());
            preparedStatement.setInt(3, chartAlbum.getRank());

            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
