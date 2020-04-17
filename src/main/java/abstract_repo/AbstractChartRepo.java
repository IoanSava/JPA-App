package abstract_repo;

import jpa.entity.Chart;

public interface AbstractChartRepo extends AbstractRepo {
    void create(Chart chart);
    Chart findById(int id);
    void displayRanking(int id);
}
