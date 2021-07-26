package com.example.dvdrental.service;

import com.example.dvdrental.entity.Actor;
import com.example.dvdrental.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<Actor> getActorById(int id) {
        return actorRepository.findById(id);
    }

    @Override
    public List<Actor> getActorByName(String name) {
        return actorRepository.searchByName(name);
    }

    @Override
    public Actor insertNewActor(String firstName, String lastName) {

        Actor actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        return actorRepository.save(actor);
    }

    @Override
    public void updateActor(Actor actor, String firstName, String lastName) {

        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Actor actor) {

        actorRepository.delete(actor);

    }

}
