package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "users")
@JsonIgnoreProperties({"points", "password", "hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Point> points;


    public User(String login, String password){
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.points = new LinkedHashSet<>();
    }

    public User(){}

}
