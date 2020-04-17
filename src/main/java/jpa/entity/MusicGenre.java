package jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ioan Sava
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "music_genres")
@NamedQueries({
        @NamedQuery(name = "MusicGenre.getRandom",
                query = "SELECT m FROM MusicGenre m ORDER BY rand()")})
public class MusicGenre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "musicGenre")
    private List<Album> listOfAlbums = new ArrayList<>();

    public MusicGenre(String name) {
        this.name = name;
    }
}
