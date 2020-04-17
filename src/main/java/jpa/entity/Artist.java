package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ioan Sava
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findByName",
                query = "SELECT ar FROM Artist ar WHERE UPPER(TRIM(ar.name))=UPPER(TRIM(:name))"),
        @NamedQuery(name = "Artist.getRandom",
                query = "SELECT ar FROM Artist ar ORDER BY rand()")})
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Artist(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id.equals(artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
