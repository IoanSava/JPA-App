import app.AlgorithmManager;
import jpa.entity.Album;
import jpa.repo.AlbumRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AlgorithmManagerTest {
    @Test
    public void testLengthOfResult() {
        AlgorithmManager algorithmManager = new AlgorithmManager();
        List<Album> listOfAlbums = algorithmManager.solve(algorithmManager.getAllAlbums());
        Assert.assertEquals(9, listOfAlbums.size());
    }

    @Test
    public void testArtistBelongToResult() {
        AlgorithmManager algorithmManager = new AlgorithmManager();
        List<Album> listOfAlbums = algorithmManager.solve(algorithmManager.getAllAlbums());
        AlbumRepository albumRepository = new AlbumRepository();
        Album album = (Album) albumRepository.findByName("Revival").get(0);
        Assert.assertTrue(listOfAlbums.contains(album));
    }
}
