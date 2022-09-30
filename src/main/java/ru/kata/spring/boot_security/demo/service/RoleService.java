package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public List<Role> printRole();

    public Role addRole(String name);

    public Role getById(Long id);

    public Set<Role> findByIdRoles(List<Long> roles);

    public void getUserRoles(User user);
}
