package app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "point_history")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "timestamp", nullable = false)
    private long date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point_id")
    private Point point;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "r", nullable = false)
    private Double r;

    @Column(name = "check", nullable = false)
    private boolean check;


    public History() {
    }

    public History(Point point, double x, double y, double r, boolean check) {
        this.point = point;
        this.x = x;
        this.y = y;
        this.r = r;
        this.check = check;
        this.date = new Date().getTime();
    }

}
