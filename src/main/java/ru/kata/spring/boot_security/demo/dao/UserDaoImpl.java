package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> printUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUserName(String username) {
        return entityManager
                .createQuery("from User u JOIN FETCH u.roles where u.username =: username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void save(User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        user.setRoles(user.getRoles());
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(getById(user.getId()));
    }
}
