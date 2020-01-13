package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"points", "password", "hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(name = "login", nullable = false)
    @Getter
    @Setter
    private String login;

    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @Getter
    @Setter
    private Set<Point> points;

    public User(String login, String password) {
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.points = new LinkedHashSet<>();
    }

    public User() {
    }
}
