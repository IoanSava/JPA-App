package abstract_repo;

import jpa.entity.ChartAlbum;

public interface AbstractChartAlbumRepo extends AbstractRepo {
    void create(ChartAlbum chartAlbum);
}
