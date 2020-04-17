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
@Table(name = "charts")
@NamedQueries({
        @NamedQuery(name = "Chart.getRanking",
                query = "SELECT ch FROM ChartAlbum ch WHERE chart_id = :id ORDER BY rank")})
public class Chart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chart")
    private List<ChartAlbum> chartAlbums = new ArrayList<>();

    public Chart(String name) {
        this.name = name;
    }
}
