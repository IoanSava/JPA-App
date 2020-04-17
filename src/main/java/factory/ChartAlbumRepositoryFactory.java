package factory;

import abstract_repo.AbstractRepo;
import jdbc.dao.ChartAlbumController;
import jpa.repo.ChartAlbumRepository;

/**
 * AbstractFactory in order to create
 * the DAO objects (the repositories).
 *
 * @author Ioan Sava
 */
public class ChartAlbumRepositoryFactory implements RepositoryAbstractFactory {
    @Override
    public AbstractRepo createRepository(int type) {
        if (type == 1) { //jdbc
            return new ChartAlbumController();
        } else { //jpa
            return new ChartAlbumRepository();
        }
    }
}
