package com.example.dvdrental.api.representationmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "films", itemRelation = "film")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmModel extends RepresentationModel<FilmModel> {

    private int filmId;
    private String title;
    private String description;
    private int rentalDuration;
    private int length;
    private float replacementCost;
    private boolean rentable;
    private List<ActorModel> actors;

}
