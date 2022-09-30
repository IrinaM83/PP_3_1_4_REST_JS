package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public List<Role> printRole();

    public Role addRole(String name);

    public Role getById(Long id);

    Set<Role> findByIdRoles(List<Long> roles);
}
