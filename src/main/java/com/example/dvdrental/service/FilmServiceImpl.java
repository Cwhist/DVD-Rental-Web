package com.example.dvdrental.service;

import com.example.dvdrental.entity.Film;
import com.example.dvdrental.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Film> getAllFilms() { return filmRepository.findAll(); }

    @Override
    public Optional<Film> getFilmById(int id) { return filmRepository.findById(id); }

    @Override
    public List<Film> getFilmByTitle(String title) { return filmRepository.searchByTitleLike(title); }

    @Override
    public Film insertNewFilm(Film film) {
        film.setLanguageId(1);
        film.setReleaseYear(2021);
        return filmRepository.save(film);
    }

    @Override
    public void updateFilm(Film film) { filmRepository.save(film); }

    @Override
    public void deleteFilm(Film film) { filmRepository.delete(film); }
}
