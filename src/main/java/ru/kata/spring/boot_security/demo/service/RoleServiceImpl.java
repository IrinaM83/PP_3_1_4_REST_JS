package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;


import java.util.List;
import java.util.Set;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Role> printRole() {
        return roleDao.printRole();
    }

    @Override
    public Role addRole(String name) {
        return roleDao.addRole(name);
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return roleDao.findByIdRoles(roles);
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }
}
