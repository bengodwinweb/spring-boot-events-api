package com.bengodwinweb.calendarapidemo.conmponents;

import com.bengodwinweb.calendarapidemo.Model.Roles;
import com.bengodwinweb.calendarapidemo.Model.User;
import com.bengodwinweb.calendarapidemo.Model.UserRepository;
import com.bengodwinweb.calendarapidemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup || userService.emailExists("user@example.com")) return;

        System.out.println("Performing data setup");

        SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority(Roles.ADMIN.get());
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(Roles.USER.get());

        List<SimpleGrantedAuthority> adminRolesList = new ArrayList<>();
        adminRolesList.add(adminRole);
        adminRolesList.add(userRole);

        List<SimpleGrantedAuthority> userRolesList = new ArrayList<>();
        userRolesList.add(userRole);

        User user = new User();
        user.setFirstName("User");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail("user@example.com");
        user.setRoles(userRolesList);
        userRepository.save(user);

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Test");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEmail("admin@example.com");
        admin.setRoles(adminRolesList);
        userRepository.save(admin);

        alreadySetup = true;
    }
}
