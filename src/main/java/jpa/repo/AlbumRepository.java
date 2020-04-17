package jpa.repo;

import abstract_repo.AbstractAlbumRepo;
import jpa.entity.Album;
import jpa.entity.Artist;

import java.util.List;

/**
 * @author Ioan Sava
 */
public class AlbumRepository extends AbstractRepository<Album> implements AbstractAlbumRepo {

    public AlbumRepository() {
        super();
    }

    /**
     * Returns an Album jpa.util.entity
     * based on its primary key (id)
     */
    public Album findById(int id) {
        return entityManager.find(Album.class, id);
    }

    /**
     * Returns a list of Album entities
     * that match a given name pattern.
     */
    public List findByName(String name) {
        return entityManager.createNamedQuery("Album.findByName")
                .setParameter("name", name)
                .getResultList();
    }

    /**
     * Returns the list of albums
     * of a given artist.
     */
    public List findByArtist(Artist artist) {
        return entityManager.createNamedQuery("Album.findByArtist")
                .setParameter("artist", artist)
                .getResultList();
    }

    public List getAll() {
        return entityManager.createNamedQuery("Album.getAll")
                .getResultList();
    }
}
