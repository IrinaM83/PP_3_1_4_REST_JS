package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    public List<User> printUsers();

    public User getById(Long id);

    public void save(User user);

    public void update(User user);

    public void delete(Long id);

    public User findUserByEmail(String email);
}
