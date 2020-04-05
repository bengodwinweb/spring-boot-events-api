package com.bengodwinweb.calendarapidemo.services;

import com.bengodwinweb.calendarapidemo.Model.*;
import com.bengodwinweb.calendarapidemo.exceptionHandling.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        System.out.println("email already exists = " + emailExists(accountDto.getEmail()));
        if (emailExists(accountDto.getEmail())) throw new EmailExistsException();

        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(Roles.USER.get());
        List<SimpleGrantedAuthority> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());

        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(userRoles);

        return userRepository.save(user);
    }

    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
