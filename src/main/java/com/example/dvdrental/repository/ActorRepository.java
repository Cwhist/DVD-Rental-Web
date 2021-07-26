package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query("SELECT actor FROM Actor actor WHERE actor.firstName = :name OR actor.lastName = :name")
    List<Actor> findByName(@Param("name") String name);

}
