package com.bengodwinweb.calendarapidemo.Model;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(@Param("email") String email);
}
