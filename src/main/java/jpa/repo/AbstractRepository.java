package jpa.repo;


import jpa.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Generic AbstractRepository using
 * generics in order to simplify the
 * creation of the repository classes.
 *
 * @param <T>  Entity type
 */
public abstract class AbstractRepository<T> {
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;

    protected AbstractRepository() {
        entityManager = PersistenceUtil.getInstance().getEntityManagerFactory().createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void create(T entity) {
        beginTransaction();
        entityManager.persist(entity);
        commitTransaction();
    }

    protected void beginTransaction() {
        try {
            entityTransaction.begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() {
        try {
            entityTransaction.commit();
            //entityManager.close();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
