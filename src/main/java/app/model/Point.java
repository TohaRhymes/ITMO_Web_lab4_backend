package app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import lombok.*;


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

    @Column(name = "check", nullable = false)
    private boolean check;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "r", nullable = false)
    private Double r;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator", nullable = false)
    private User creator;


}
