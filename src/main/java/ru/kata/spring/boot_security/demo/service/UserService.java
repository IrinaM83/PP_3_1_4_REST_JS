package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> printUsers();

    public User getById(int id);

    public User findByUserName(String username);

    public void save(User user);

    public void update(User user);

    public void delete(User user);

}
