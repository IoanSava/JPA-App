package factory;

import abstract_repo.AbstractRepo;
import jdbc.dao.ArtistController;
import jpa.repo.ArtistRepository;

/**
 * AbstractFactory in order to create
 * the DAO objects (the repositories).
 *
 * @author Ioan Sava
 */
public class ArtistRepositoryFactory implements RepositoryAbstractFactory {
    @Override
    public AbstractRepo createRepository(int type) {
        if (type == 1) { //jdbc
            return new ArtistController();
        } else { //jpa
            return new ArtistRepository();
        }
    }
}
