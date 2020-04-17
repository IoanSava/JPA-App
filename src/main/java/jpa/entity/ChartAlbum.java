package jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "charts_albums")
public class ChartAlbum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "chart_id", referencedColumnName = "id")
    @ManyToOne
    private Chart chart;

    @JoinColumn(name = "album_id", referencedColumnName = "id")
    @ManyToOne
    private Album album;

    @Basic(optional = false)
    @Column(name = "rank")
    private Integer rank;

    public ChartAlbum(Chart chart, Album album, int rank) {
        this.chart = chart;
        this.album = album;
        this.rank = rank;
    }
}
