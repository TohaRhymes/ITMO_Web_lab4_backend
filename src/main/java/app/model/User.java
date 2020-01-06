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
    @Size(min = 4, message = "Minimum username length: 4 characters.")
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Minimum password length: 8 characters.")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Point> points;

    public User(){}

    public User(String login, String password){
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.points = new LinkedHashSet<>();
    }

}
