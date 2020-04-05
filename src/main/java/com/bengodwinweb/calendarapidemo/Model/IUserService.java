package com.bengodwinweb.calendarapidemo.Model;

import com.bengodwinweb.calendarapidemo.exceptionHandling.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
}
