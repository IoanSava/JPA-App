package abstract_repo;

import jpa.entity.MusicGenre;

public interface AbstractMusicGenreRepo extends AbstractRepo {
    void create(MusicGenre musicGenre);
    MusicGenre findById(int id);
}
