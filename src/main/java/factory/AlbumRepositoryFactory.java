package factory;

import abstract_repo.AbstractRepo;
import jdbc.dao.AlbumController;
import jpa.repo.AlbumRepository;

/**
 * AbstractFactory in order to create
 * the DAO objects (the repositories).
 *
 * @author Ioan Sava
 */
public class AlbumRepositoryFactory implements RepositoryAbstractFactory {
    @Override
    public AbstractRepo createRepository(int type) {
        if (type == 1) { //jdbc
            return new AlbumController();
        } else { //jpa
            return new AlbumRepository();
        }
    }
}
