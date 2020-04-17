package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ioan Sava
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findByName",
                query = "SELECT al FROM Album al WHERE UPPER(TRIM(al.name))=UPPER(TRIM(:name))"),
        @NamedQuery(name = "Album.findByArtist",
                query = "SELECT al FROM Album al WHERE al.artist=:artist"),
        @NamedQuery(name = "Album.getAll",
                query = "SELECT al FROM Album al")})
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private MusicGenre musicGenre;

    public Album(String name, Artist artist, MusicGenre musicGenre) {
        this.name = name;
        this.artist = artist;
        this.musicGenre = musicGenre;
    }

    public Album(String name, Artist artist, MusicGenre musicGenre, int releaseYear) {
        this.name = name;
        this.artist = artist;
        this.musicGenre = musicGenre;
        this.releaseYear = releaseYear;
    }

    public Album(int id, String name, Artist artist, MusicGenre musicGenre, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.musicGenre = musicGenre;
        this.releaseYear = releaseYear;
    }
}
