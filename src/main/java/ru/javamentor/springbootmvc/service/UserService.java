package ru.javamentor.springbootmvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<Role> getListRoles();
    List<Role> getListByRole(List<String> name);
    boolean add(User user);
    Set<User> getListUsers();
    void delete(int id);
    void update(User user);
    User getById(int id);
    User getByUsername(String userName);
}
