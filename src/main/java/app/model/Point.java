package app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="points")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Setter
    @Getter
    private int id;

    @Column(name = "timestamp", nullable = false)
    @Setter
    @Getter
    private long date;

    @Column(name = "r", nullable = false)
    @Setter
    @Getter
    private Double r;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner", nullable = false)
    @Setter
    @Getter
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "point", cascade = CascadeType.ALL)
    @Setter
    @Getter
    private Set<History> histories;

    public Point(Double r, User owner){
        this.histories = new LinkedHashSet<>();
        this.r = r;
        this.date = new Date().getTime();
        this.owner = owner;
    }

    public Point(){}

}
