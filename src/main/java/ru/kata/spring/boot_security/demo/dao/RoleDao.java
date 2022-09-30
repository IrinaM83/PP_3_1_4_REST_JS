package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    public List<Role> printRole();

    public Role getById(Long id);

    public Set<Role> findByIdRoles(List<Long> roles);

    public Role addRole(String name);
}
