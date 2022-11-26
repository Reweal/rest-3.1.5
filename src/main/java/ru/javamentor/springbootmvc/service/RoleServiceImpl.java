package ru.javamentor.springbootmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springbootmvc.dao.RoleDao;
import ru.javamentor.springbootmvc.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public List<Role> getListRoles() {
        return roleDao.getListRoles();
    }

    @Override
    public List<Role> getRolesListById(List<Long> id) {
        return roleDao.getRolesListById(id);
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getByIdRole(id);
    }
}