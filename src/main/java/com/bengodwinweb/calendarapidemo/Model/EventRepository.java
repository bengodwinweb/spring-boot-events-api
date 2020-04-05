package com.bengodwinweb.calendarapidemo.Model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findByTitle(@Param("title") String title);

}
