package ru.javamentor.springbootmvc.dao;

import ru.javamentor.springbootmvc.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role getByIdRole(int id);
    List<Role> getListRoles();
    Role getByName(String name);
    List<Role> getListByName(List<String> name);
    boolean add(Role user);

    List<Role> getRolesListById(List<Long> roles);
}
