package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    // Search Actor By Name
    @Query("SELECT actor FROM Actor actor WHERE actor.firstName LIKE %:name% OR actor.lastName LIKE %:name%")
    List<Actor> searchByNameLike(@Param("name") String name);

    // Search Actor By first_name and last_name
    // Do same works when firstName and lastName's positions are reversed
    @Query("SELECT actor FROM Actor actor WHERE actor.firstName LIKE %:firstName% AND actor.lastName LIKE %:lastName%" +
            " OR actor.lastName LIKE %:firstName% AND actor.firstName LIKE %:lastName%")
    List<Actor> searchBySplitNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
