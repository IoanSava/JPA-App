package abstract_repo;

import jpa.entity.Album;
import jpa.entity.Artist;

import java.util.List;

public interface AbstractAlbumRepo extends AbstractRepo {
    void create(Album album);
    Album findById(int id);
    List findByName(String name);
    List findByArtist(Artist artist);
}
