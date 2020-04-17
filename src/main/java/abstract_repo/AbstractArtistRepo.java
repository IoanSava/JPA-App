package abstract_repo;

import jpa.entity.Artist;

import java.util.List;

public interface AbstractArtistRepo extends AbstractRepo {
    void create(Artist artist);
    Artist findById(int id);
    List findByName(String name);
}
