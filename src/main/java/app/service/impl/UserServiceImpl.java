package app.service.impl;


import app.model.User;
import app.repository.UserRepository;
import app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(String name) {
        if (repository.existsById(name)) {
            return repository.getOne(name);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        if (repository.existsById(user.getLogin())) {
            return false;
        }
        repository.saveAndFlush(user);
        return true;
    }


}
