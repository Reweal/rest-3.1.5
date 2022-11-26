package ru.javamentor.springbootmvc.dao;

import ru.javamentor.springbootmvc.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User getByName(String username);
    void delete(int id);
    void update(User user);
    boolean add(User user);
    Set<User> getListUsers();
    User getById(int id);
}
