package app.request;

import app.model.User;
import lombok.Setter;


public class UserAddRequest {
    @Setter
    private String login;
    @Setter
    private String password;

    public User createUser(){
        return new User(login, password);
    }
}
