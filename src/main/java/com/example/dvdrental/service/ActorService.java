package com.example.dvdrental.service;

import com.example.dvdrental.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    List<Actor> getAllActors();

    Optional<Actor> getActorById(int id);

    Actor insertNewActor(String firstName, String lastName);

    void updateActor(Actor actor, String firstName, String lastName);

    void deleteActor(Actor actor);
}
