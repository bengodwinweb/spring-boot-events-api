package com.bengodwinweb.calendarapidemo.controllers;

import com.bengodwinweb.calendarapidemo.Model.User;
import com.bengodwinweb.calendarapidemo.Model.UserDto;
import com.bengodwinweb.calendarapidemo.exceptionHandling.EmailExistsException;
import com.bengodwinweb.calendarapidemo.exceptionHandling.UserDtoValidationException;
import com.bengodwinweb.calendarapidemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/register")
    @ResponseBody
    public String showRegister() {
        System.out.println("get to register");
        return "register page";
    }

    @PostMapping("/users/register")
    @PreAuthorize("permitAll()")
    @ResponseBody
    public User registerUserAccount(@RequestBody @Valid UserDto accountDto, BindingResult result, WebRequest request, Errors errors) throws EmailExistsException, UserDtoValidationException {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        } else {
            System.out.println(errors);
            for (FieldError err : errors.getFieldErrors()) {
                System.out.println(err.getField());
                System.out.println(err.getDefaultMessage());
            }
            throw new UserDtoValidationException("Validation Error", errors);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        return registered;
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) throws EmailExistsException {
        User registered = null;
        registered = userService.registerNewUserAccount(accountDto);
        return registered;
    }
}
