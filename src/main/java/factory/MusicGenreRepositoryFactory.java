package factory;

import abstract_repo.AbstractRepo;
import jdbc.dao.ChartController;
import jdbc.dao.MusicGenreController;
import jpa.repo.ChartRepository;
import jpa.repo.MusicGenreRepository;

/**
 * AbstractFactory in order to create
 * the DAO objects (the repositories).
 *
 * @author Ioan Sava
 */
public class MusicGenreRepositoryFactory implements RepositoryAbstractFactory {
    @Override
    public AbstractRepo createRepository(int type) {
        if (type == 1) { //jdbc
            return new MusicGenreController();
        } else { //jpa
            return new MusicGenreRepository();
        }
    }
}
