package ru.javamentor.springbootmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;
import ru.javamentor.springbootmvc.service.UserServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private UserServiceImpl userService;

    @Autowired
    public RestController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity<Set<User>> showAllUsers() {
        Set<User> users = userService.listUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable int id) {
        User user = userService.findByUsername(userService.findById(id).getUsername());
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/viewUser")
    public ResponseEntity<User> showUser(Authentication auth) {
        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> getAllRoles() {
        return new ResponseEntity<>(userService.listRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") int id){
        return new ResponseEntity<>(userService.findByIdRole(id), HttpStatus.OK);
    }
}
