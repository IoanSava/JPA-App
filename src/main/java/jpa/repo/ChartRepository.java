package jpa.repo;

import abstract_repo.AbstractChartRepo;
import jpa.entity.Chart;
import jpa.entity.ChartAlbum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ioan Sava
 */
public class ChartRepository extends AbstractRepository<Chart> implements AbstractChartRepo {

    public ChartRepository() {
        super();
    }

    public Chart findById(int id) {
        return entityManager.find(Chart.class, id);
    }

    public void displayRanking(int id) {
        List<ChartAlbum> chartAlbumList = (ArrayList<ChartAlbum>)
                entityManager.createNamedQuery("Chart.getRanking")
                        .setParameter("id", id)
                        .getResultList();

        for (ChartAlbum chartAlbum : chartAlbumList) {
            System.out.println(chartAlbum.getRank() + ". " + chartAlbum.getAlbum().getName() + " - " +
                    chartAlbum.getAlbum().getArtist().getName());
        }
    }
}
