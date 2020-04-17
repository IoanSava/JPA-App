package jpa.repo;

import abstract_repo.AbstractArtistRepo;
import jpa.entity.Artist;

import java.util.List;

/**
 * @author Ioan Sava
 */
public class ArtistRepository extends AbstractRepository<Artist> implements AbstractArtistRepo {

    public ArtistRepository() {
        super();
    }

    /**
     * Returns an Artist jpa.util.entity
     * based on its primary key (id)
     */
    public Artist findById(int id) {
        return entityManager.find(Artist.class, id);
    }

    /**
     * Returns a list of Artist entities
     * that match a given name pattern.
     */
    public List findByName(String name) {
        return entityManager.createNamedQuery("Artist.findByName")
                .setParameter("name", name)
                .getResultList();
    }

    public Artist getRandom() {
        return (Artist) entityManager.createNamedQuery("Artist.getRandom")
                .setMaxResults(1)
                .getSingleResult();
    }
}
