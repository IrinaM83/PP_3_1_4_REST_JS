package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    public List<User> printUsers();

    public User getById(int id);
    public User getByName(String username);

    public void save(User user);

    public void update(User user);

    public void delete(User user);
}
