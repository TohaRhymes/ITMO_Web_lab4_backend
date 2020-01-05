package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Data
@Table(name = "users")
@JsonIgnoreProperties({"points", "password","hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Size(min = 4, message = "Minimum username length: 4 characters.")
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Minimum password length: 8 characters.")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    @Getter
    @Setter
    private Set<Point> point;

}
