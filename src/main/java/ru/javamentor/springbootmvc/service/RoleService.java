package ru.javamentor.springbootmvc.service;

import ru.javamentor.springbootmvc.model.Role;

import java.util.List;

public interface RoleService {

    void add(Role role);

    List<Role> getListRoles();

    List<Role> getRolesListById(List<Long> id);

    Role getRoleById(int id);

}