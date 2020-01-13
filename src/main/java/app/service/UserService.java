package app.service;

import app.model.User;

public interface UserService {

    User getUser(String name);

    boolean addUser(User user);

}
