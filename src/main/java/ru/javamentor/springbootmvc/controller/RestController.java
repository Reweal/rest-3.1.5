package ru.javamentor.springbootmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;
import ru.javamentor.springbootmvc.service.UserServiceImpl;

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


//    public User(String firstname, String lastname, String age, String email, String username, String password, Set<Role> roles) {
    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable int id) {
        User user = userService.findById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User newUser = new User("firstname", "firstname", "firstname", "22", "firstname@firstname", "firstname", Set.of(new Role("ROLE_ADMIN")));
        userService.add(newUser);
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
    public ResponseEntity<LinkedHashSet<Role>> getAllRoles() {
        return new ResponseEntity<>(userService.listRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") int id){
        return new ResponseEntity<>(userService.findByIdRole(id), HttpStatus.OK);
    }
}
