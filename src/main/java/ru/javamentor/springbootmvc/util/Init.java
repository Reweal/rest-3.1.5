//package ru.javamentor.springbootmvc.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.javamentor.springbootmvc.model.Role;
//import ru.javamentor.springbootmvc.model.User;
//import ru.javamentor.springbootmvc.service.UserServiceImpl;
//
//import javax.annotation.PostConstruct;
//import java.util.Set;
//
//@Component
//public class Init {
//
//    private UserServiceImpl userService;
//
//    @Autowired
//    public Init(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Role role1 = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//
//        userService.addRole(role1);
//
//        User user1 = new User("admin", "admin", "111", "admin@gmail.com", "admin", "admin", Set.of(role1));
//        userService.add(user1);
//    }
//}
