package factory;

import abstract_repo.AbstractRepo;

/**
 * AbstractFactory in order to create
 * the DAO objects (the repositories).
 *
 * @author Ioan Sava
 */
public interface RepositoryAbstractFactory {
    public AbstractRepo createRepository(int type);
}
