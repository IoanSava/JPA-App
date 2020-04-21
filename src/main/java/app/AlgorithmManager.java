package app;

import com.github.javafaker.Faker;
import jpa.entity.Album;
import jpa.entity.Artist;
import jpa.entity.MusicGenre;
import jpa.repo.AlbumRepository;
import jpa.repo.ArtistRepository;
import jpa.repo.MusicGenreRepository;
import jpa.util.PersistenceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implement an efficient algorithm
 * that returns the largest set
 * of albums such that no two albums
 * have the same artist or belong
 * to the same genre.
 *
 * @author Ioan Sava
 */
public class AlgorithmManager {
    private static int NUMBER_OF_ALBUMS = 150;

    private ArtistRepository artistRepository = new ArtistRepository();
    private AlbumRepository albumRepository = new AlbumRepository();
    private MusicGenreRepository musicGenreRepository = new MusicGenreRepository();

    public static void main(String[] args) {
        AlgorithmManager algorithmManager = new AlgorithmManager();
        //algorithmManager.insertRandomAlbums(NUMBER_OF_ALBUMS);

        List<Album> listOfAlbums = algorithmManager.solve(algorithmManager.getAllAlbums());
        for (Album album : listOfAlbums) {
            System.out.println(album);
        }

        PersistenceUtil.getInstance().getEntityManagerFactory().close();
    }

    /**
     * Generate fake data in order to populate
     * your database with a large number of albums.
     */
    private void insertRandomAlbums(int numberOfRows) {
        Random random = new Random();
        Faker faker = new Faker();
        for (int i = 0; i < numberOfRows; ++i) {
            String fakeAlbumName = faker.music().instrument();
            Artist artist = artistRepository.getRandom();
            MusicGenre musicGenre = musicGenreRepository.getRandom();
            int randomYear = random.nextInt(20) + 2000;
            albumRepository.create(new Album(fakeAlbumName, artist, musicGenre, randomYear));
        }
    }

    public List<Album> getAllAlbums() {
        return (ArrayList<Album>) albumRepository.getAll();
    }

    /**
     * A greedy iterative algorithm works. In each iteration,
     * select an element 'album' in 'albums' arbitrarily randomly, removed
     * 'album', together with all the elements which is conflicted with 'a',
     * that is the elements which 'artist' is the same
     * with 'album', or 'musicGenre'. Obviously this result
     * is valid, since all elements which can contain conflict are removed in advance.
     *
     * @see <a href="https://en.wikipedia.org/wiki/3-dimensional_matching">3-dimensional matching</a>
     */
    public List<Album> solve(List<Album> albums) {
        int numberOfAlbums = albums.size();
        List<Album> listOfAlbums = new ArrayList<>();

        while (numberOfAlbums > 0) {
            Album album = albums.get(0);
            listOfAlbums.add(album);

            albums.removeIf(currentAlbum -> currentAlbum.getArtist().getId().equals(album.getArtist().getId()) ||
                    currentAlbum.getMusicGenre().getId().equals(album.getMusicGenre().getId()));
            numberOfAlbums = albums.size();
        }

        return listOfAlbums;
    }
}
