package factory;

import abstract_repo.AbstractRepo;
import jdbc.dao.ChartController;
import jpa.repo.ChartRepository;

/**
 * AbstractFactory in order to create
 * the DAO objects (the repositories).
 *
 * @author Ioan Sava
 */
public class ChartRepositoryFactory implements RepositoryAbstractFactory {

    @Override
    public AbstractRepo createRepository(int type) {
        if (type == 1) { //jdbc
            return new ChartController();
        } else { //jpa
            return new ChartRepository();
        }
    }
}
