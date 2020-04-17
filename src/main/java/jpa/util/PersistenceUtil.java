package jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton class responsible of
 * creating/returning an
 * EntityManagerFactory object
 *
 * @author Ioan Sava
 */
public class PersistenceUtil {
    private static PersistenceUtil instance;
    private EntityManagerFactory entityManagerFactory;

    private final String PERSISTENCE_UNIT = "MusicAlbumsPU";

    private PersistenceUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    /**
     * Restricts the instantiation of PersistenceUtil class
     * to one "single" instance
     */
    public static PersistenceUtil getInstance() {
        if (instance == null) {
            instance = new PersistenceUtil();
        }

        return instance;
    }
}
