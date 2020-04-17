package jdbc.dao;

import abstract_repo.AbstractChartRepo;
import jpa.entity.Artist;
import jpa.entity.Chart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * DAO class responsible
 * to get data regarding Charts
 * from database.
 *
 * @author Ioan Sava
 */
public class ChartController extends Controller implements AbstractChartRepo {
    private String RANK_KEY = "rank";
    private String ALBUM_NAME_KEY = "albumName";
    private String ARTIST_NAME_KEY = "artistName";

    public ChartController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void create(Chart chart) {
        try {
            String query = "insert into charts (name) values(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, chart.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Chart findById(int id) {
        Chart chart = null;
        try {
            String query = "select * from charts where " +
                    "id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                chart = new Chart(name);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return chart;
    }

    /**
     * Generate the ranking of the artists,
     * considering their positions in the specified chart.
     */
    private List<Map<String, Object>> generateRanking(int id) {
        List<Map<String, Object>> ranking = new ArrayList<>();
        try {
            String query = "select ch.rank, al.name as albumName, ar.name as artistName " +
                    "from artists ar join albums al on ar.id = al.artist_id join charts_albums ch on al.id = ch.album_id " +
                    "where chart_id = ? " +
                    "order by ch.rank;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                ranking.add(new HashMap<>());
                int rank = resultSet.getInt(RANK_KEY);
                ranking.get(i).put(RANK_KEY, rank);
                String albumName = resultSet.getString(ALBUM_NAME_KEY);
                ranking.get(i).put(ALBUM_NAME_KEY, albumName);
                String artistName = resultSet.getString(ARTIST_NAME_KEY);
                ranking.get(i).put(ARTIST_NAME_KEY, artistName);
                ++i;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return ranking;
    }

    private void displayRow(int rank, String name, String country) {
        System.out.println(rank + ". " + name + " - " + country);
    }

    public void displayRanking(int id) {
        List<Map<String, Object>> ranking = generateRanking(id);
        for (Map<String, Object> stringObjectMap : ranking) {
            displayRow((int) stringObjectMap.get(RANK_KEY), (String) stringObjectMap.get(ALBUM_NAME_KEY),
                    (String) stringObjectMap.get(ARTIST_NAME_KEY));
        }
    }
}
