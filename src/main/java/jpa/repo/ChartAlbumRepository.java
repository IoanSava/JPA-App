package jpa.repo;

import abstract_repo.AbstractChartAlbumRepo;
import jpa.entity.ChartAlbum;

/**
 * @author Ioan Sava
 */
public class ChartAlbumRepository extends AbstractRepository<ChartAlbum> implements AbstractChartAlbumRepo {

    public ChartAlbumRepository() {
        super();
    }
}
