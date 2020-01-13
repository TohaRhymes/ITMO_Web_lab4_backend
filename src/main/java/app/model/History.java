package app.model;

import app.plot.Checker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Getter
    private int id;

    @Column(name = "timestamp", nullable = false)
    @Getter
    private long date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point_id", nullable = false)
    private Point point;

    @Column(name = "x", nullable = false)
    @Getter
    private Double x;

    @Column(name = "y", nullable = false)
    @Getter
    private Double y;

    @Column(name = "ch", nullable = false)
    @Getter
    private boolean isCheck;

    public History() {
    }

    public History(Point point, double x, double y) {
        this.point = point;
        this.x = x;
        this.y = y;
        double r = point.getR();
        this.isCheck = Checker.checkHitArea(x, y, r);
        this.date = new Date().getTime();
    }


}
