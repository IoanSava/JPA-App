package jpa.repo;

import abstract_repo.AbstractMusicGenreRepo;
import jpa.entity.MusicGenre;

/**
 * @author Ioan Sava
 */
public class MusicGenreRepository extends AbstractRepository<MusicGenre> implements AbstractMusicGenreRepo {
    public MusicGenreRepository() {
        super();
    }

    public MusicGenre findById(int id) {
        return entityManager.find(MusicGenre.class, id);
    }

    public MusicGenre getRandom() {
        return (MusicGenre) entityManager.createNamedQuery("MusicGenre.getRandom")
                .setMaxResults(1)
                .getSingleResult();
    }
}
