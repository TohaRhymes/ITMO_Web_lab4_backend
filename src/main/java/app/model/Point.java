package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "points")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "timestamp", nullable = false)
    private long date;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator", nullable = false)
    private User creator;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "point", cascade = CascadeType.ALL)
    private Set<History> history;

    public Point(){}

    public Point(String name, User creator){
        this.name = name;
        this.creator = creator;
        this.history = new LinkedHashSet<>();
        this.date = new Date().getTime();
    }


}
