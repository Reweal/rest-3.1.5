package ru.javamentor.springbootmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;
import ru.javamentor.springbootmvc.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserServiceImpl userService;
    final SuccessUserHandler loginSuccessHandler;

    @Autowired
    public WebSecurityConfig(UserServiceImpl userService, SuccessUserHandler successUserHandler) {
        this.userService = userService;
        this.loginSuccessHandler = successUserHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
//                .antMatchers("/admin/**", "/", "/index").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(loginSuccessHandler).loginPage("/")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

//    @PostConstruct
//    public void init() {
//        Role role1 = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//
//        userService.addRole(role1);
//        userService.addRole(role2);
//
//        List<Role> roleAdmin = new ArrayList<>();
//        List<Role> roleUser = new ArrayList<>();
//
//        roleAdmin.add(role1);
//        roleUser.add(role2);
//
//        User user2 = new User(2,"Misha", "Kray", "22", "email@mail.ru", "user", "user", roleUser);
//        User user1 = new User(1,"Dasha", "Kiosse", "19", "daria@mail.ru", "admin", "admin", roleAdmin);
//
//        userService.add(user1);
//        userService.add(user2);
//    }

}