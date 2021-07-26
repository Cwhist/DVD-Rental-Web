package com.example.dvdrental.service;

import com.example.dvdrental.entity.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<Film> getAllFilms();

    Optional<Film> getFilmById(int id);

    List<Film> getFilmByTitle(String title);

    Film insertNewFilm(Film film);

    void updateFilm(Film film);

    void deleteFilm(Film film);

}
