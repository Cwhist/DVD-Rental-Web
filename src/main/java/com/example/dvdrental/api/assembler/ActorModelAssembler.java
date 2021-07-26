package com.example.dvdrental.api.assembler;

import com.example.dvdrental.api.controller.ActorController;
import com.example.dvdrental.api.controller.FilmController;
import com.example.dvdrental.api.representationmodel.ActorModel;
import com.example.dvdrental.api.representationmodel.FilmModel;
import com.example.dvdrental.entity.Actor;
import com.example.dvdrental.entity.Film;
import com.example.dvdrental.util.CollectionChecker;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ActorModelAssembler extends RepresentationModelAssemblerSupport<Actor, ActorModel> {

    public ActorModelAssembler() {
        super(ActorController.class, ActorModel.class);
    }

    @Override
    public ActorModel toModel(Actor entity) {

        ActorModel actorModel = instantiateModel(entity);

        actorModel.add( linkTo(methodOn(ActorController.class).retrieveActor(entity.getActorId()))
                        .withSelfRel());

        actorModel.setActorId(entity.getActorId());
        actorModel.setFirstName(entity.getFirstName());
        actorModel.setLastName(entity.getLastName());

        if(!CollectionChecker.isEmpty(entity.getFilms())) {
            actorModel.setFilms(toFilmModel(entity.getFilms()));
        }

        return actorModel;
    }

    @Override
    public CollectionModel<ActorModel> toCollectionModel(Iterable<? extends Actor> entities) {

        CollectionModel<ActorModel> actorModels = super.toCollectionModel(entities);
        actorModels.add(linkTo(methodOn(ActorController.class).retrieveAllActors()).withSelfRel());

        return actorModels;
    }

    private List<FilmModel> toFilmModel(List<Film> films) {

        if(films.isEmpty())
            return Collections.emptyList();

        return films.stream()
                .map(film -> FilmModel.builder()
                        .filmId(film.getFilmId())
                        .title(film.getTitle())
                        .description(film.getDescription())
                        .releaseYear(film.getReleaseYear())
                        .languageId(film.getLanguageId())
                        .rentalDuration(film.getRentalDuration())
                        .rentalRate(film.getRentalRate())
                        .length(film.getLength())
                        .replacementCost(film.getReplacementCost())
                        .specialFeatures(film.getSpecialFeatures())
                        .build()
                        .add(linkTo(methodOn(FilmController.class).retrieveFilm(film.getFilmId()))
                                .withRel("film")))
                .collect(Collectors.toList());

    }

}
