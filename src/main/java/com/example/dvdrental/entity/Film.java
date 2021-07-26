package com.example.dvdrental.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int filmId;

    private String title;

    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "language_id")
    private int languageId;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private float rentalRate;

    private int length;

    @Column(name = "replacement_cost")
    private float replacementCost;

    @Column(name = "special_features")
    private String specialFeatures;

    //Many To Many Join
    // Film - Actor
    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Actor> actors;


}
