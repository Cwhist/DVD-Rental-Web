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
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class FilmModelAssembler extends RepresentationModelAssemblerSupport<Film, FilmModel> {

    public FilmModelAssembler() { super(FilmController.class, FilmModel.class); }

    @Override
    public FilmModel toModel(Film entity) {

        FilmModel filmModel = instantiateModel(entity);

        filmModel.add( linkTo(methodOn(FilmController.class).retrieveFilm(entity.getFilmId()))
                        .withSelfRel());

        filmModel.setFilmId(entity.getFilmId());
        filmModel.setTitle(entity.getTitle());
        filmModel.setDescription(entity.getDescription());
        filmModel.setReleaseYear(entity.getReleaseYear());
        filmModel.setLanguageId(entity.getLanguageId());
        filmModel.setRentalDuration(entity.getRentalDuration());
        filmModel.setRentalRate(entity.getRentalRate());
        filmModel.setLength(entity.getLength());
        filmModel.setReplacementCost(entity.getReplacementCost());
        filmModel.setSpecialFeatures(entity.getSpecialFeatures());

        if( !CollectionUtils.isEmpty(entity.getActors()) ) {
            filmModel.setActors(toActorModel(entity.getActors()));
        }

        return filmModel;
    }

    @Override
    public CollectionModel<FilmModel> toCollectionModel(Iterable<? extends Film> entities) {
        return super.toCollectionModel(entities);
    }

    private List<ActorModel> toActorModel(List<Actor> actors) {

        if (actors.isEmpty())
            return Collections.emptyList();

        return actors.stream()
                .map(actor -> ActorModel.builder()
                        .actorId(actor.getActorId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .build()
                        .add(linkTo(methodOn(ActorController.class).retrieveActor(actor.getActorId()))
                                    .withRel("actor")))
                .collect(Collectors.toList());
    }
}
